package com.example.hackatonapp.presentation.settings_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.hackatonapp.R
import com.example.hackatonapp.network.dto.LocationDto
import com.example.hackatonapp.network.utils.Resource

@Composable
fun MyLocationsScreen(modifier: Modifier = Modifier, onAddClick : ()->Unit, viewModel: MapViewModel) {
    val state = viewModel.state.collectAsState()

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            if(state.value is Resource.Success){
                val items = (state.value as Resource.Success<LocationDto>).data
                items(items){
                    LocationItem(name = it.name, coord = it.coords)
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            if(state.value is Resource.Loading){
                item{
                    CircularProgressIndicator(
                        color = Color.Black,
                        modifier = Modifier.size(100.dp)
                    )
                }
            }
        }

        FloatingActionButton(
            onClick = {
                onAddClick()
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 86.dp, end = 16.dp),
            containerColor = Color(0xFFFCE000)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}