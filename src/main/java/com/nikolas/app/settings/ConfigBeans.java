package com.nikolas.app.settings;

import com.nikolas.app.beans.Counter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigBeans {
    @Bean
    public Counter totalVisitsCounter() {
        return new Counter("totalVisitsCounter");
    }
}
