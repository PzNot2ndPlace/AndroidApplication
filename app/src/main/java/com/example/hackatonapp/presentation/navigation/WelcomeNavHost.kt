package com.example.hackatonapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.example.hackatonapp.presentation.auth_screens.AuthViewModel
import com.example.hackatonapp.presentation.auth_screens.Login
import com.example.hackatonapp.presentation.auth_screens.LoginScreen
import com.example.hackatonapp.presentation.auth_screens.Register
import com.example.hackatonapp.presentation.auth_screens.Welcome
import com.example.hackatonapp.presentation.auth_screens.WelcomeScreen

@Composable
fun WelcomeNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val viewModel = hiltViewModel<AuthViewModel>()

    NavHost(
        navController = navController,
        graph = navController.createGraph(Welcome){
            composable<Welcome> {
                WelcomeScreen(
                    onLogin = {
                        navController.navigate(Login)
                    },
                    onRegister = {
                        navController.navigate(Register)
                    }
                )
            }

            composable<Login> {
                LoginScreen(
                    modifier = Modifier,
                    onBack = {
                        navController.popBackStack()
                    },
                    viewModel = viewModel,
                    onNext = {
                        navController.navigate(BottomNavHost)
                    }
                )
            }

            composable<Register> {

            }

            composable<BottomNavHost> {
                BottomNavigationNavHost()
            }
        }
    )
}