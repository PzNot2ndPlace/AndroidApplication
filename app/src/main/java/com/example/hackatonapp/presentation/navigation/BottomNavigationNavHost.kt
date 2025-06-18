package com.example.hackatonapp.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.hackatonapp.presentation.chat_screen.Chat
import com.example.hackatonapp.presentation.chat_screen.ChatScreen
import com.example.hackatonapp.presentation.chat_screen.ChatViewModel
import com.example.hackatonapp.presentation.feed_screen.Feed
import com.example.hackatonapp.presentation.feed_screen.FeedScreen
import com.example.hackatonapp.presentation.settings_screen.Map
import com.example.hackatonapp.presentation.settings_screen.MapScreen
import com.example.hackatonapp.presentation.settings_screen.MapViewModel
import com.example.hackatonapp.presentation.settings_screen.MyLocations
import com.example.hackatonapp.presentation.settings_screen.MyLocationsScreen

@Composable
fun BottomNavigationNavHost() {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<ChatViewModel>()
    val viewModelMap = hiltViewModel<MapViewModel>()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding->
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            graph = navController.createGraph(startDestination = Chat){
                composable<Chat> {
                    ChatScreen(modifier = Modifier.fillMaxSize(), innerPadding, viewModel)
                }

                composable<Feed> {
                    FeedScreen()
                }

                composable<MyLocations> {
                    MyLocationsScreen(
                        onAddClick = {
                            navController.navigate(Map)
                        }, viewModel = viewModelMap
                    )
                }

                composable<Map> {
                    MapScreen(
                        viewModel = viewModelMap
                    )
                }
            }
        )
    }
}