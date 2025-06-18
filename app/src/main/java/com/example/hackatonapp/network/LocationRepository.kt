package com.example.hackatonapp.network

import com.example.hackatonapp.network.dto.AddLocationDto
import com.example.hackatonapp.network.dto.LocationDto
import com.example.hackatonapp.network.utils.Resource
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocationRepository @Inject constructor(
    private val api : Api
) {
    fun sendUserLocation(coords : String) : Flow<Resource<Unit>> = flow{
        try {
            emit(Resource.Loading)
            val res = api.postCurrentLocation(coords)
            emit(Resource.Success(res))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun addLocation(dto : AddLocationDto): Flow<Resource<Unit>> = flow{
        try {
            emit(Resource.Loading)
            val res = api.addLocation(dto)
            emit(Resource.Success(res))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getLocations() : Flow<Resource<LocationDto>> = flow{
        try {
            emit(Resource.Loading)
            val res = api.getLocations()
            emit(Resource.Success(res))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}