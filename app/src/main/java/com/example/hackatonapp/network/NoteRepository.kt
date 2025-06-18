package com.example.hackatonapp.network

import android.util.Log
import com.example.hackatonapp.network.dto.AddNoteDto
import com.example.hackatonapp.network.dto.AddNoteResponse
import com.example.hackatonapp.network.dto.NoteDto
import com.example.hackatonapp.network.dto.NoteDtoItem
import com.example.hackatonapp.network.utils.Resource
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NoteRepository @Inject constructor(
    private val api : Api
) {
    fun addNote(addNoteDto: AddNoteDto) : Flow<Resource<AddNoteResponse>> = flow{
        try {
            emit(Resource.Loading)
            val res = api.addNote("http://45.141.78.50:5678/webhook/note", addNoteDto)
            emit(Resource.Success(res))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }

    fun getNotes() : Flow<Resource<NoteDto>> = flow{
        try {
            emit(Resource.Loading)
            val res = api.getMyNotes()
            emit(Resource.Success(res))
        }catch (e : Exception){
            emit(Resource.Error(e.message.toString()))
        }
    }
}