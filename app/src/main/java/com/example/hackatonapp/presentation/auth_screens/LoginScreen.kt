package com.example.hackatonapp.presentation.auth_screens

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hackatonapp.R
import com.example.hackatonapp.network.utils.Resource

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onBack : () -> Unit,
    viewModel: AuthViewModel,
    onNext : () -> Unit
) {
    var emailValue by remember { mutableStateOf("") }
    var passwordValue by remember { mutableStateOf("") }

    val state = viewModel.loginState.collectAsState()

    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(state.value) {
        if(state.value is Resource.Success){
            viewModel.sendToken()
            isLoading = false
            onNext()
        }
        if(state.value is Resource.Loading){
            isLoading = true
        }
        if(state.value is Resource.Error){
            isLoading = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            FloatingActionButton(
                onClick = {
                    onBack()
                },
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 32.dp),
                containerColor = Color(0xFFFCE000)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.vector10),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = emailValue,
                onValueChange = {
                    emailValue = it
                },
                placeholder = {
                    Text(
                        text = "Введите email",
                        color = Color(0xFF21201F),
                        fontSize = 16.sp
                    )
                },
                textStyle = TextStyle(
                    color = Color(0xFF21201F),
                    fontSize = 16.sp
                ),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F4F2),
                    unfocusedContainerColor = Color(0xFFF5F4F2),
                    cursorColor = Color(0xFFFCE000),
                    focusedTextColor = Color(0xFF262222),
                    unfocusedTextColor = Color(0xFF262222),
                    focusedIndicatorColor = Color(0xFFFCE000),
                    unfocusedIndicatorColor = Color(0xFFFCE000),
                )
            )
            OutlinedTextField(
                value = passwordValue,
                onValueChange = {
                    passwordValue = it
                },
                placeholder = {
                    Text(
                        text = "Введите пароль",
                        color = Color(0xFF21201F),
                        fontSize = 16.sp
                    )
                },
                textStyle = TextStyle(
                    color = Color(0xFF21201F),
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F4F2),
                    unfocusedContainerColor = Color(0xFFF5F4F2),
                    cursorColor = Color(0xFFFCE000),
                    focusedTextColor = Color(0xFF262222),
                    unfocusedTextColor = Color(0xFF262222),
                    focusedIndicatorColor = Color(0xFFFCE000),
                    unfocusedIndicatorColor = Color(0xFFFCE000),
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .background(color = Color(0xFFFCE000), RoundedCornerShape(16.dp)),
                onClick = {
                    viewModel.login(emailValue, passwordValue)
                }, enabled = !isLoading
            ) {
                if(isLoading){
                    CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(24.dp))
                }
                else{
                    Text(
                        text = "Войти",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    }
}