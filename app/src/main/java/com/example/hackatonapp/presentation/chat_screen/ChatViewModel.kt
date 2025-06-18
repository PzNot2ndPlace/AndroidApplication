package com.example.hackatonapp.presentation.chat_screen

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackatonapp.network.NoteRepository
import com.example.hackatonapp.network.dto.AddNoteDto
import com.example.hackatonapp.network.dto.AddNoteResponse
import com.example.hackatonapp.network.dto.NoteDto
import com.example.hackatonapp.network.dto.NoteDtoItem
import com.example.hackatonapp.network.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: NoteRepository
) : ViewModel() {
    var notesState = MutableStateFlow<Resource<AddNoteResponse>>(Resource.Init)

    private val _messages = mutableStateListOf<ChatMessage>()
    val messages: List<ChatMessage> = _messages

    fun addUserMessage(text: String) {
        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
        _messages.add(ChatMessage.User(text, time))
    }

    fun addBotMessage(r : AddNoteResponse) {
        val time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
        _messages.add(ChatMessage.Bot(
            text = if (r.status == "error" && r.noteDto.text.isEmpty()) r.message else r.noteDto.text,
            time = time,
            category = r.noteDto.categoryType,
            triggers = r.noteDto.triggers
        ))
    }

    fun addNote(msg:String){
        viewModelScope.launch {
            addUserMessage(msg)

            val currentDateTime = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            val formattedDateTime = currentDateTime.format(formatter)

            repository.addNote(
                AddNoteDto(
                    current_time = formattedDateTime,
                    user_text = msg
                )
            ).collect{
                notesState.value = it

                if(it is Resource.Success){
                    addBotMessage(it.data)
                }
            }
        }
    }
}