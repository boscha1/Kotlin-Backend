package com.example.demo.model

data class CustomerDTO(val firstName: String, val lastName: String)

data class ErrorMessage(val message: String)

fun CustomerDTO.toModel(withId: Int? = null) = Customer(withId, this.firstName, this.lastName)