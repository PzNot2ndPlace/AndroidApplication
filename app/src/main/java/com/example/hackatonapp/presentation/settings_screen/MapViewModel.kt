package com.example.hackatonapp.presentation.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hackatonapp.network.LocationRepository
import com.example.hackatonapp.network.dto.AddNoteResponse
import com.example.hackatonapp.network.dto.LocationDto
import com.example.hackatonapp.network.utils.Resource
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: LocationRepository
) : ViewModel() {
    var state = MutableStateFlow<Resource<LocationDto>>(Resource.Init)

    private val _selectedPosition = MutableStateFlow<LatLng?>(null)
    val selectedPosition: StateFlow<LatLng?> = _selectedPosition

    init {
        getLocations()
    }

    fun setSelectedPosition(latLng: LatLng) {
        _selectedPosition.value = latLng
    }

    fun getLocations(){
        viewModelScope.launch {
            repository.getLocations().collect{
                state.value = it
            }
        }
    }
}