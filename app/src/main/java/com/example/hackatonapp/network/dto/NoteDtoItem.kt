package com.example.hackatonapp.network.dto

data class NoteDtoItem(
    val categoryType: String,
    val createdAt: String,
    val id: String,
    val text: String,
    val triggers: List<Trigger>,
    val updatedAt: String,
    val userId: String
)