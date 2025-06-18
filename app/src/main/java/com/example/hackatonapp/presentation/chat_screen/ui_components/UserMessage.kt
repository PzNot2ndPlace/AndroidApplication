package com.example.hackatonapp.presentation.chat_screen.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun UserMessage(
    modifier: Modifier = Modifier,
    author : String,
    text : String,
    time : String
) {
    BaseMessage(
        modifier = modifier.background(Color(0xFFF7F4DA), shape = RoundedCornerShape(16.dp,16.dp,4.dp,16.dp)),
        author, text, time
    )
}