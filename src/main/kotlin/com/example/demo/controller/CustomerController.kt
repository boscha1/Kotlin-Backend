package com.example.demo.controller

import com.example.demo.model.CustomerDTO
import com.example.demo.model.ErrorMessage
import com.example.demo.service.CustomerService
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class CustomerController(@Autowired val customerService: CustomerService) {
    suspend fun getCustomers(request: ServerRequest): ServerResponse {
        val customers = customerService.getCustomers()
        return ServerResponse.ok().json().bodyAndAwait(customers)
    }

    suspend fun saveCustomer(request: ServerRequest): ServerResponse {
        val newCustomer = try {
            request.bodyToMono<CustomerDTO>().awaitFirstOrNull()
        } catch (e: Exception) {
            println(e.message)
            null
        }
        return if (newCustomer == null) {
            ServerResponse.badRequest().json().bodyValueAndAwait(ErrorMessage("Invalid body"))
        } else {
            val customer = customerService.saveCustomer(newCustomer)
            if (customer == null)
                ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).json().bodyValueAndAwait(ErrorMessage("Internal error"))
            else ServerResponse.status(HttpStatus.CREATED).json().bodyValueAndAwait(customer)
        }
    }
}