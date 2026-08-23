package com.nikolas.app.controllers;

import com.nikolas.app.components.EmailTemplates;
import com.nikolas.app.components.SessionData;
import com.nikolas.app.controllers.dtos.PreviousOrder;
import com.nikolas.app.controllers.forms.FormDataContact;
import com.nikolas.app.controllers.forms.FormDataOrder;
import com.nikolas.app.models.Area;
import com.nikolas.app.models.Order;
import com.nikolas.app.models.OrderItem;
import com.nikolas.app.models.Pie;
import com.nikolas.app.repositories.AreaRepository;
import com.nikolas.app.repositories.OrderItemRepository;
import com.nikolas.app.repositories.OrderRepository;
import com.nikolas.app.repositories.PieRepository;
import com.nikolas.app.services.VisitsMetricsService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/buy")
public class BuyController {

    @Autowired
    private VisitsMetricsService visitsMetricsService;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private PieRepository pieRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private SessionData sessionData;

    @Autowired
    private EmailTemplates emailTemplates;

    private int pageVisits = 0;

    @GetMapping
    public String handleRequest(Model model, @RequestParam(name="orderId", required=false) String orderId) {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        List<Area> areas = (List<Area>) areaRepository.findAll();
        List<Pie> pies = (List<Pie>) pieRepository.findAll();
        model.addAttribute("areas", areas);
        model.addAttribute("pies", pies);

        FormDataOrder formDataOrder;
        formDataOrder = new FormDataOrder();
        if (sessionData.getUser()!=null) {
            formDataOrder = new FormDataOrder();
            formDataOrder.setFullname(sessionData.getUser().getFullname());
            formDataOrder.setEmail(sessionData.getUser().getEmail());
            formDataOrder.setTel(sessionData.getUser().getTel());

            List<Order> top5Orders = orderRepository.findTopFiveUserOrderIds(sessionData.getUser().getId());

            model.addAttribute("top5Orders", top5Orders);

            // convert data to transfer
            List<PreviousOrder> previousOrders = new ArrayList<>();
            for (var order: top5Orders) {
                PreviousOrder previousOrder = new PreviousOrder(order.getStamp(), order.getId(), order.getOrderItem(), pies);
                previousOrders.add(previousOrder);
            }
            model.addAttribute("previousOrders", previousOrders);
        }
        formDataOrder.setOrder(sessionData.getOrder());

        if (orderId!=null) {
            Order previousOrder = orderRepository.findOrderById(Integer.parseInt(orderId));
            for (var item: previousOrder.getOrderItem()) {
                int pieId = item.getPieId();
                int quantity = item.getQuantity();

                formDataOrder.getOrder().put(pieId, quantity);
            }
        }


        model.addAttribute("formDataOrder", formDataOrder);

        return "buy";
    }

    @PostMapping
    public String handleRequest(Model model, @Valid @ModelAttribute("formDataOrder") FormDataOrder formDataOrder,
                                BindingResult bindingResult) throws MessagingException, IOException {
        visitsMetricsService.increaseCounters(model, ++pageVisits);

        // session data needs updating with form changes
        sessionData.setOrder(formDataOrder.getOrder());

        // get the current timestamp
        formDataOrder.setStamp(LocalDateTime.now());

        // success: No errors
        if (!bindingResult.hasErrors()) {
            model.addAttribute("success", true);

            //apostolh ston client
            emailTemplates.sendEmailToClientOrderForm(formDataOrder, sessionData.getOrder());

            //apostolh ston admin tou pitospies
            emailTemplates.sendEmailToAdminOrderForm(formDataOrder, sessionData.getOrder());
            Order order = new Order(formDataOrder, sessionData.getOrder());
            if (sessionData.getUser()!=null)
                order.setUserId(sessionData.getUser().getId());
            orderRepository.save(order);
        }

        // send the necessary data
        List<Area> areas = (List<Area>) areaRepository.findAll();
        List<Pie> pies = (List<Pie>) pieRepository.findAll();
        model.addAttribute("areas", areas);
        model.addAttribute("pies", pies);

        return "buy";
    }

}