package com.example.hackatonapp.presentation.chat_screen

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.PermissionChecker.checkCallingOrSelfPermission
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hackatonapp.R
import com.example.hackatonapp.presentation.chat_screen.ui_components.ResponseMessage
import com.example.hackatonapp.presentation.chat_screen.ui_components.UserMessage
import com.example.hackatonapp.presentation.tts_utils.STTManager
import com.example.hackatonapp.presentation.tts_utils.TtsManager

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    paddingValuesBottom: PaddingValues,
    viewModel : ChatViewModel
) {
    val context = LocalContext.current
    var textValue by remember { mutableStateOf("") }

    val chatState = viewModel.notesState.collectAsState()

    var isRecording by remember { mutableStateOf(false) }

    val speechRecognizerManager = remember(context) {
        STTManager(
            context = context,
            onResult = { resultText -> textValue = resultText },
            onError = { error -> Log.e("SpeechRecognizer", error) }
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            speechRecognizerManager.destroy()
        }
    }

    val tts = TtsManager(context)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValuesBottom.calculateBottomPadding())
            .background(color = Color(0xFFE6ECF0))
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            reverseLayout = true
        ) {

            item {
                Spacer(modifier = Modifier.height(86.dp))
            }

            items(viewModel.messages.reversed()) { message ->
                when (message) {
                    is ChatMessage.User -> {
                        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.End) {
                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(
                                onClick = {
                                    tts.speak(message.text)
                                },
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .background(color = Color(0xFFF1F1F1), shape = CircleShape)
                                    .align(Alignment.Bottom)
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_speaker),
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }

                            UserMessage(
                                modifier = Modifier.fillMaxWidth(0.8f),
                                author = "Пользователь",
                                text = message.text,
                                time = message.time
                            )
                        }
                    }

                    is ChatMessage.Bot -> {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            ResponseMessage(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .padding(top = 16.dp),
                                author = "ИИ ассистент",
                                text = message.text,
                                category = message.category,
                                trigger = message.triggers
                            )
                            Spacer(modifier = Modifier.weight(1f))

                            IconButton(
                                onClick = {
                                    tts.speak(message.text)
                                },
                                modifier = Modifier
                                    .padding(horizontal = 8.dp)
                                    .background(color = Color(0xFFF1F1F1), shape = CircleShape)
                                    .align(Alignment.Bottom)
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_speaker),
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(color = Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_burger),
                contentDescription = null,
                modifier = Modifier.padding(start = 16.dp, end = 6.dp, top = 8.5.dp)
            )
            TextField(
                value = textValue,
                onValueChange = {
                    textValue = it
                },
                singleLine = true,
                modifier = Modifier.padding(start = 12.dp, end = 8.dp, top = 8.5.dp).weight(1f),
                placeholder = {
                    Text(
                        text = "Сообщение...",
                        color = Color(0xFF21201F),
                        fontSize = 20.sp
                    )
                },
                textStyle = TextStyle(
                    color = Color(0xFF21201F),
                    fontSize = 20.sp
                ),
                shape = CircleShape,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF5F4F2),
                    unfocusedContainerColor = Color(0xFFF5F4F2),
                    cursorColor = Color(0xFF262222),
                    focusedTextColor = Color(0xFF262222),
                    unfocusedTextColor = Color(0xFF262222),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .padding(end = 6.dp)
                    .background(color = Color(0xFFFCE000), shape = CircleShape)
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                if (textValue.isEmpty()){
                                    isRecording = true
                                    speechRecognizerManager.startListening()
                                }
                                awaitRelease()
                                isRecording = false
                                speechRecognizerManager.stopListening()
                            },
                            onDoubleTap = {
                                if (textValue.isNotEmpty()){
                                    viewModel.addNote(textValue)
                                    textValue = ""
                                }
                            }
                        )
                    }
            ) {
                Icon(
                    imageVector = if (textValue.isNotEmpty() && !isRecording)
                        Icons.AutoMirrored.Filled.Send
                    else
                        ImageVector.vectorResource(R.drawable.ic_micro),
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(start = 2.dp)
                )
            }
        }
    }
}