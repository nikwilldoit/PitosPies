package com.nikolas.app.config;

import com.nikolas.app.beans.Cart;
import com.nikolas.app.beans.SessionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.context.annotation.SessionScope;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

//    @Autowired
//    private DataSource dataSource;
//
//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource);
//    }

    @SessionScope
    @Bean
    public SessionBean sessionBean(){
        return new SessionBean();
    }

    @SessionScope
    @Bean
    public Cart cart(){
        return new Cart();
    }
}
