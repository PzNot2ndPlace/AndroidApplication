package com.example.hackatonapp.network

import com.example.hackatonapp.network.dto.AddLocationDto
import com.example.hackatonapp.network.dto.AddNoteDto
import com.example.hackatonapp.network.dto.AddNoteResponse
import com.example.hackatonapp.network.dto.LocationDto
import com.example.hackatonapp.network.dto.LoginDto
import com.example.hackatonapp.network.dto.NoteDto
import com.example.hackatonapp.network.dto.NoteDtoItem
import com.example.hackatonapp.network.dto.RegisterDto
import com.example.hackatonapp.network.dto.TokenDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {
    @POST("auth/register")
    suspend fun register(@Body registerDto: RegisterDto) : TokenDto

    @POST("auth/login")
    suspend fun login(@Body loginDto: LoginDto) : TokenDto

    @POST("notification/token/save")
    suspend fun saveFCBToken(@Query("token") token : String)

    @POST("notification/test")
    suspend fun testNot(@Query("message") message : String)

    @POST
    suspend fun addNote(@Url url : String, @Body addNoteDto: AddNoteDto) : AddNoteResponse

    @GET("note/my")
    suspend fun getMyNotes() : NoteDto

    @POST("location/coords")
    suspend fun postCurrentLocation(@Query("coords") coords : String)

    @POST("location")
    suspend fun addLocation(@Body addLocationDto: AddLocationDto)

    @GET("location")
    suspend fun getLocations() : LocationDto
}