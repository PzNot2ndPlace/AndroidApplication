package com.example.hackatonapp.presentation.settings_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapScreen(
    viewModel : MapViewModel
) {
    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(55.751244, 37.618423), 10f)
    }

    val selectedPosition by viewModel.selectedPosition.collectAsState()

    Box(Modifier.fillMaxSize().padding(bottom = 86.dp)) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = { latLng ->
                viewModel.setSelectedPosition(latLng)
            }
        ) {
            selectedPosition?.let {
                Marker(
                    state = MarkerState(position = it),
                    title = "Выбрано",
                    snippet = "${it.latitude}, ${it.longitude}"
                )
            }
        }

        selectedPosition?.let {
            Text(
                text = "Вы выбрали: ${it.latitude}, ${it.longitude}",
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .background(Color.Black)
                    .padding(8.dp),
                fontSize = 14.sp
            )
        }
    }
}