package com.example.hackatonapp.presentation.chat_screen.ui_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackatonapp.R

@Composable
fun BaseMessage(
    modifier: Modifier = Modifier,
    author : String,
    text : String,
    time : String
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp, horizontal = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ){
            Text(
                text = author,
                fontSize = 16.sp
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_additional),
                contentDescription = null
            )
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = text,
            fontSize = 20.sp
        )

        Text(
            text = time,
            fontSize = 14.sp,
            color = Color(0xFF262222),
            modifier = modifier.padding(top = 4.dp).align(Alignment.End).wrapContentWidth(Alignment.End)
        )
    }
}