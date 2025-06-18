package com.example.hackatonapp.network.dto

data class Trigger(
    val id: String,
    val isReady: Boolean,
    val location: String,
    val time: String,
    val triggerType: String
)