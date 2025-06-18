package com.example.hackatonapp.presentation.navigation

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.hackatonapp.R
import com.example.hackatonapp.presentation.chat_screen.Chat
import com.example.hackatonapp.presentation.feed_screen.Feed
import com.example.hackatonapp.presentation.navigation.components.NavItem
import com.example.hackatonapp.presentation.settings_screen.MyLocations

@Composable
fun BottomNavBar(
    navController: NavController
) {
    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    val navItems = listOf(
        NavItem(
            route = Chat,
            icon = R.drawable.ic_home_selected,
            altIcon = R.drawable.ic_home_not_chosen
        ),
        NavItem(
            route = Feed,
            icon = R.drawable.ic_phrase_selected,
            altIcon = R.drawable.ic_phrase_selected
        ),
        NavItem(
            route = MyLocations,
            icon = R.drawable.ic_settings,
            altIcon = R.drawable.ic_settings
        )
    )

    NavigationBar(
        containerColor = Color.White, contentColor = Color.Transparent
    ) {
        navItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedNavigationIndex.intValue,
                onClick = {
                    navController.navigate(item.route)
                    selectedNavigationIndex.intValue = index
                },
                icon = {
                    Icon(
                        imageVector = if(index == selectedNavigationIndex.intValue) ImageVector.vectorResource(item.icon) else ImageVector.vectorResource(item.altIcon),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                )
            )
        }
    }
}

@Preview
@Composable
private fun dasdada() {
    BottomNavigationNavHost()
}