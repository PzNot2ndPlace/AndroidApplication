package com.example.hackatonapp.presentation.auth_screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackatonapp.network.AuthRepository
import com.example.hackatonapp.network.dto.LoginDto
import com.example.hackatonapp.network.dto.RegisterDto
import com.example.hackatonapp.network.utils.Resource
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.ktx.messaging
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    var registerState = MutableStateFlow<Resource<String>>(Resource.Init)

    var loginState = MutableStateFlow<Resource<String>>(Resource.Init)

    fun sendToken() {
        viewModelScope.launch {
            val fbToken = Firebase.messaging.token.await()

            repository.saveToken(fbToken).collect()
        }
    }

    fun register(email: String, name: String, password: String) {
        viewModelScope.launch {
            repository.register(
                RegisterDto(
                    email, name, password
                )
            ).collect{
                registerState.value = it
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            repository.login(
                LoginDto(
                    email, password
                )
            ).collect{
                loginState.value = it
            }
        }
    }
}