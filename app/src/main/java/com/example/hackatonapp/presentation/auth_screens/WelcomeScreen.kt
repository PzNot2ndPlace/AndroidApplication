package com.example.hackatonapp.presentation.auth_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackatonapp.R
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import kotlinx.coroutines.tasks.await

@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    onLogin : () -> Unit,
    onRegister : () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color(0xFFFCE000)),
        reverseLayout = true,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Column(
                modifier = Modifier.padding(top = 100.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextButton(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(color = Color.White, RoundedCornerShape(16.dp)),
                    onClick = {
                        onLogin()
                    }
                ) {
                    Text(
                        text = "Войти",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                TextButton(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).background(color = Color.Black, RoundedCornerShape(16.dp)),
                    onClick = {
                        onRegister()
                    }
                ) {
                    Text(
                        text = "Зарегистрироваться",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

        item {
            Text(
                text = "Ваш помощник c уведомлениями —\nбыстро, удобно, доступно.",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
        
        item {
            Text(
                text = "Уведомления",
                fontSize = 48.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(top = 92.dp)
            )
        }

        item {
            Image(
                painter = painterResource(R.drawable.welcome_image),
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 100.dp).aspectRatio(1f)
            )
        }
    }
}