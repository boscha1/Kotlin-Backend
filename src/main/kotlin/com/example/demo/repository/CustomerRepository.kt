package com.example.demo.repository

import com.example.demo.model.Customer
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface CustomerRepository: ReactiveCrudRepository<Customer, Int>