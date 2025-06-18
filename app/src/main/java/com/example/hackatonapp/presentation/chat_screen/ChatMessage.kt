package com.example.hackatonapp.presentation.chat_screen

import com.example.hackatonapp.network.dto.Trigger

sealed class ChatMessage {
    data class User(val text: String, val time: String) : ChatMessage()
    data class Bot(val text: String, val time: String, val category : String, val triggers : List<Trigger>) : ChatMessage()
}
