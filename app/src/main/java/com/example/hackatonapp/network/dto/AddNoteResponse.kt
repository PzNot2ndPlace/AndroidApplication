package com.example.hackatonapp.network.dto

data class AddNoteResponse(
    val message: String,
    val noteDto: NoteDtoItem,
    val status: String
)