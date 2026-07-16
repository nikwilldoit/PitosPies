package com.nikolas.app.services;

import com.nikolas.app.beans.Counter;
import com.nikolas.app.settings.ConfigBeans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class VisitsMetricsService {

    @Autowired
    private Counter totalVisitsCounter;

    private int[] piesCounters = new int[4];

    public void increaseCounters(Model model, int pageVisits) {
        totalVisitsCounter.increase();
        model.addAttribute("pageVisits",  pageVisits);
    }

    public void increasePieCounter(Model model, int pie) {
        totalVisitsCounter.increase();
        model.addAttribute("pageVisits", ++piesCounters[pie-1]);
    }
}
