package com.nikolas.app.components;

import com.nikolas.app.controllers.forms.FormDataContact;
import com.nikolas.app.controllers.forms.FormDataOrder;
import com.nikolas.app.models.Pie;
import com.nikolas.app.repositories.AreaRepository;
import com.nikolas.app.repositories.PieRepository;
import com.nikolas.app.services.MailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class EmailTemplates {

    @Autowired
    private MailService mailService;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private PieRepository pieRepository;

    @Async
    public void sendEmailToClientContactForm(FormDataContact formData) {
        String text =
                "Παραλάβαμε το μήνυμα σας με τα εξής στοιχεία: \n" +
                        "\tΟνοματεπώνυμο: " + formData.getFullname() + "\n" +
                        "\tE-mail: " + formData.getEmail() + "\n" +
                        "\tΤηλέφωνο: " + formData.getTel() + "\n" +
                        "\tΜήνυμα: " + formData.getMessage() + "\n\n" +
                        "και θα επικοινωνήσουμε μαζί σας σύντομα!";

        mailService.sendTextEmail(formData.getEmail(), "Ενημέρωση Επικοινωνίας", text);
    }

    @Async
    public void sendEmailToAdminContactForm(FormDataContact formData) throws IOException {
        ClassPathResource resource = new ClassPathResource("project.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        String adminEmail = properties.getProperty("mail.admin");

        String text =
                "Νέο μήνυμα από τη φόρμα επικοινωνίας της σελίδας μας: \n" +
                        "\tΟνοματεπώνυμο: " + formData.getFullname() + "\n" +
                        "\tE-mail: " + formData.getEmail() + "\n" +
                        "\tΤηλέφωνο: " + formData.getTel() + "\n" +
                        "\tΜήνυμα: " + formData.getMessage() + "\n";

        mailService.sendTextEmail(adminEmail, "Εισερχόμενο Μήνυμα από τη φόρμα επικοινωνίας", text);
    }

    @Async
    public void sendEmailToAdminOrderForm(FormDataOrder formDataOrder, Map<Integer, Integer> order) throws IOException {
        ClassPathResource resource = new ClassPathResource("project.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        String adminEmail = properties.getProperty("mail.admin");

        String text =
                "Νέα παραγγελία: \n" +
                        "\tΟνοματεπώνυμο: " + formDataOrder.getFullname() + "\n" +
                        "\tΔιεύθυνση: " + formDataOrder.getAddress() + "\n" +
                        "\tΠεριοχή: " + areaRepository.findAreaById(formDataOrder.getAreaId()).getDescription() + "\n" +
                        "\tE-mail: " + formDataOrder.getEmail() + "\n" +
                        "\tΤηλέφωνο: " + formDataOrder.getTel() + "\n" +
                        "\tΜήνυμα: " + formDataOrder.getComments() + "\n" +
                        "\tΠαραγγελία: \n";

        for (var pieId: order.keySet()) {
            text += "\t\t" + pieRepository.findPieById(pieId).getName() + ": " + order.get(pieId) + "\n";
        }

        text += "\tΠροσφορά: " + formDataOrder.isOffer() + "\n" +
                "\tΤρόπος Πληρωμής: " + formDataOrder.getPayment() + "\n";

        mailService.sendTextEmail(adminEmail, "Νέα Παραγγελία", text);
    }

    @Async
    public void sendEmailToClientOrderForm(FormDataOrder formDataOrder, Map<Integer, Integer> order) throws MessagingException {
        List<Pie> pies = (List<Pie>) pieRepository.findAll();

        double sum = 0.0;
        for (var pie: pies) {
            sum += order.get(pie.getId()) * pie.getPrice();
        }

        LocalDateTime timestamp = formDataOrder.getStamp();
        LocalDateTime timestampUntil = timestamp.plus(30, ChronoUnit.MINUTES);


        String text =
                "<header style=\"padding: 30px;text-align: center;font-size: 20px;font-weight: bold;\">Παραγγελία καθ΄οδόν</header>\n" +
                        "<table class=\"table-pies\" style=\"border-collapse: collapse;vertical-align: center;caption-side: bottom;margin: 0 auto;box-shadow: 0 0 4px 1px #483C46;width: 90%;\">\n" +
                        "  <thead>\n" +
                        "  <tr style=\"background-image: linear-gradient(to bottom, #483C46, #675664);font-size: 1em;margin-bottom: 10px;color: #BEEE62;\">\n" +
                        "    <th style=\"padding: 10px 20px;border: 0;\">Πίτα</th>\n" +
                        "    <th style=\"padding: 10px 20px;border: 0;\">Ποσότητα</th>\n" +
                        "    <th style=\"padding: 10px 20px;border: 0;\">Τιμή</th>\n" +
                        "  </tr>\n" +
                        "  </thead>\n" +
                        "  <tbody>\n";


        int i=0;
        for (var pieId: order.keySet()) {
            Pie pie = pieRepository.findPieById(pieId);
            text += (i%2==0? "  <tr>\n": "  <tr style=\"background-color: #ded8dd\">\n") +
                    "    <td style=\"text-align: center;border: 0;padding: 30px;font-weight: normal;\">" + pie.getName() + "</td>\n" +
                    "    <td style=\"text-align: center;border: 0;padding: 30px;font-weight: normal;\">" + order.get(pieId) + "</td>\n" +
                    "    <td style=\"text-align: center;border: 0;padding: 30px;font-weight: normal;\">" + String.format("%.2f", order.get(pieId) * pie.getPrice()) + "€</td>\n" +
                    "  </tr>\n";
            i++;
        }

        text += "  </tbody>\n" +
                "  <tfoot>\n" +
                "  <tr>\n" +
                "    <td colspan=\"3\" style=\"padding: 10px;font-weight: bold;font-size: 20px;\">Σύνολο: " + String.format("%.2f", sum) + "€\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  </tfoot>\n" +
                "</table>\n" +
                "<p style=\"padding: 30px;text-align: center;font-size: 20px;font-weight: bold;\">Εκτιμώμενη Ώρα Παράδοσης: " +
                timestampUntil.format(DateTimeFormatter.ofPattern("d/M/u (kk:mm:ss)")) +
                "</p>";

        mailService.sendHtmlEmail(formDataOrder.getEmail(), "Η Παραγγελία Σας!", text);
    }

}