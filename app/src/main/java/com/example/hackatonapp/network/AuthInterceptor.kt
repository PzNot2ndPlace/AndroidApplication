package com.example.hackatonapp.network

import com.example.hackatonapp.local.TokenDataStore
import jakarta.inject.Inject
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()

        val requiresAuth = listEndpoints.any { uri.contains(it) }

        if (!requiresAuth) {
            return chain.proceed(chain.request())
        }

        val token = runBlocking { tokenDataStore.tokenFlow.firstOrNull() }

        return if (!token.isNullOrEmpty()) {
            val authenticatedRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(authenticatedRequest)
        } else {
            chain.proceed(chain.request())
        }
    }
}

val listEndpoints = listOf(
    "notification", "note", "location"
)