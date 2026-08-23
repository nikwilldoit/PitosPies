package com.nikolas.app.components;

import com.nikolas.app.models.Pie;
import com.nikolas.app.models.User;
import com.nikolas.app.repositories.PieRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@SessionScope
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionData {
    @Autowired
    private PieRepository pieRepository;

    private Map<Integer, Integer> order = new HashMap<>();
    private User user = null;


    @PostConstruct
    public void initializeOrder() {
        List<Pie> pies = (List<Pie>) pieRepository.findAll();
        for (var pie: pies)
            order.put(pie.getId(), 0);
    }

    public void resetOrder() {
        for (var pieId: order.keySet())
            order.put(pieId, 0);
    }
}
