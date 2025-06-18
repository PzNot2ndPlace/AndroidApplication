package com.example.hackatonapp.presentation.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackatonapp.R

@Composable
fun LocationItem(
    modifier: Modifier = Modifier,
    name: String,
    coord: String
) {
    Row(
        modifier = Modifier.fillMaxWidth().background(Color(0xFFE6ECF0), RoundedCornerShape(8.dp)).shadow(2.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_pinned_phrase),
            contentDescription = null,
            modifier = Modifier.padding(start = 16.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text(
                text = name,
                fontSize = 20.sp
            )
            
            Text(
                text = coord,
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
    }
}