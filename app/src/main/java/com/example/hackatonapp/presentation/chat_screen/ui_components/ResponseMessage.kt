package com.example.hackatonapp.presentation.chat_screen.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackatonapp.R
import com.example.hackatonapp.network.dto.Trigger
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ResponseMessage(
    modifier: Modifier = Modifier,
    author : String,
    text : String,
    category: String,
    trigger: List<Trigger>
) {
    Column(
        modifier = modifier
            .background(Color.White, shape = RoundedCornerShape(16.dp, 16.dp, 16.dp, 4.dp))
            .padding(vertical = 8.dp, horizontal = 12.dp)
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
            Text(
                text = category,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.background(color = Color(0xFFAED6F1), RoundedCornerShape(4.dp)).padding(4.dp)
            )
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = text,
            fontSize = 20.sp
        )

        Column(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(trigger.size){ index ->
                if(trigger[index].triggerType == "TIME"){
                    Text(
                        text = "\uD83D\uDD52  Время " + convertAndShiftTime(trigger[index].time),
                        fontSize = 16.sp
                    )
                }
                else{
                    Text(
                        text = "\uD83D\uDCCD  Локация " + trigger[index].location,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


fun convertAndShiftTime(isoString: String): String {
    val inputTime = ZonedDateTime.parse(isoString)
    val shiftedTime = inputTime.plusHours(7)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
    return shiftedTime.format(formatter)
}

@Preview
@Composable
private fun dsasddsdsa() {
    ResponseMessage(
        author = "AI",
        text = "dsadad sdfadf f dafas fd asafsd",
        category = "DADAD",
        trigger = listOf(
            Trigger(
                id = "1",
                isReady = true,
                location = "dasdsasd",
                time = "12:11",
                triggerType = "Time"
            )
        )
    )
}