package com.example.hackatonapp.presentation.tts_utils

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class TtsManager(context: Context) : TextToSpeech.OnInitListener {
    private var tts : TextToSpeech? = null
    private var isInitialized = false

    init {
        tts = TextToSpeech(context.applicationContext, this)
    }

    override fun onInit(p0: Int) {
        if(p0 == TextToSpeech.SUCCESS){
            val result = tts?.setLanguage(Locale("ru"))
            isInitialized = result != TextToSpeech.LANG_MISSING_DATA && result != TextToSpeech.LANG_NOT_SUPPORTED
        }
    }

    fun speak(text:String){
        if(isInitialized){
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    fun stop(){
        tts?.stop()
    }
}