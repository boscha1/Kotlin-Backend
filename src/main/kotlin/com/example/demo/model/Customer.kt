package com.example.demo.model

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("customer")
data class Customer(@Id val id: Int?,
                    val firstName: String,
                    val lastName: String)
