package com.example.demo.config

import com.example.demo.controller.CustomerController
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.web.reactive.function.server.coRouter

@Configuration
@EnableR2dbcRepositories
class AppConfiguration {
    @Bean
    fun customerRoute(customerController: CustomerController) = coRouter {
        GET("/customers", customerController::getCustomers)
        POST("/customers", customerController::saveCustomer)
    }
}