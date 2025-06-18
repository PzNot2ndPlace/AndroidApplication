package com.example.hackatonapp.network

import android.util.Log
import com.example.hackatonapp.local.TokenDataStore
import com.example.hackatonapp.network.dto.LoginDto
import com.example.hackatonapp.network.dto.RegisterDto
import com.example.hackatonapp.network.utils.Resource
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepository @Inject constructor(
    private val api : Api,
    private val dataStore: TokenDataStore
) {
    fun login(loinDto: LoginDto) : Flow<Resource<String>> = flow{
        try {
            emit(Resource.Loading)
            delay(1000)
            val res = api.login(loinDto)
            dataStore.saveToken(res.token)
            emit(Resource.Success(res.token))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun register(registerDto: RegisterDto) : Flow<Resource<String>> = flow{
        try {
            emit(Resource.Loading)
            val res = api.register(registerDto)
            dataStore.saveToken(res.token)
            emit(Resource.Success(res.token))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun saveToken(fcmToken : String) : Flow<Resource<Unit>> = flow{
        try {
            emit(Resource.Loading)
            val res = api.saveFCBToken(fcmToken)
            emit(Resource.Success(res))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun testNotification(message : String) : Flow<Resource<Unit>> = flow{
        try {
            emit(Resource.Loading)
            val res = api.testNot(message)
            emit(Resource.Success(res))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}