package com.example.demo.service

import com.example.demo.model.CustomerDTO
import com.example.demo.model.toModel
import com.example.demo.repository.CustomerRepository
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CustomerService(@Autowired private val customerRepository: CustomerRepository) {

    suspend fun getCustomers() = customerRepository.findAll().asFlow()

    suspend fun saveCustomer(customer: CustomerDTO) = customerRepository.save(customer.toModel()).awaitFirstOrNull()
}