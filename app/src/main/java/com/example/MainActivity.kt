package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.ui.components.CivicBackground
import com.example.ui.theme.*
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.propertycheck.PropertyCheckScreen
import com.example.ui.screens.redflags.RedFlagsScreen
import com.example.ui.screens.guides.GuidesScreen
import com.example.ui.screens.resources.ResourcesScreen

enum class AppScreen {
    HOME,
    PROPERTY_CHECK,
    RED_FLAGS,
    GUIDES,
    RESOURCES
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeforeYouBuyTheme {
                MainScreenContent()
            }
        }
    }
}

@Composable
fun MainScreenContent() {
    var currentScreen by rememberSaveable { mutableStateOf(AppScreen.HOME) }

    // Intercept physical phone back clicks to navigate back to the HOME screen tab before exiting
    if (currentScreen != AppScreen.HOME) {
        BackHandler {
            currentScreen = AppScreen.HOME
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                modifier = Modifier.navigationBarsPadding(),
                containerColor = SurfaceCharcoal,
                tonalElevation = 0.dp
            ) {
                NavigationBarItem(
                    selected = currentScreen == AppScreen.HOME,
                    onClick = { currentScreen = AppScreen.HOME },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home navigation tab") },
                    label = { Text("Home") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SoftGold,
                        selectedTextColor = SoftGold,
                        unselectedIconColor = LightText.copy(alpha = 0.4f),
                        unselectedTextColor = LightText.copy(alpha = 0.4f),
                        indicatorColor = MutedGreen.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.testTag("nav_home")
                )
                NavigationBarItem(
                    selected = currentScreen == AppScreen.PROPERTY_CHECK,
                    onClick = { currentScreen = AppScreen.PROPERTY_CHECK },
                    icon = { Icon(Icons.Default.Assignment, contentDescription = "Screener guidance questionnaire navigation tab") },
                    label = { Text("Screener") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SoftGold,
                        selectedTextColor = SoftGold,
                        unselectedIconColor = LightText.copy(alpha = 0.4f),
                        unselectedTextColor = LightText.copy(alpha = 0.4f),
                        indicatorColor = MutedGreen.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.testTag("nav_check")
                )
                NavigationBarItem(
                    selected = currentScreen == AppScreen.RED_FLAGS,
                    onClick = { currentScreen = AppScreen.RED_FLAGS },
                    icon = { Icon(Icons.Default.Warning, contentDescription = "Red flags list library navigation tab") },
                    label = { Text("Red Flags") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SoftGold,
                        selectedTextColor = SoftGold,
                        unselectedIconColor = LightText.copy(alpha = 0.4f),
                        unselectedTextColor = LightText.copy(alpha = 0.4f),
                        indicatorColor = MutedGreen.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.testTag("nav_redflags")
                )
                NavigationBarItem(
                    selected = currentScreen == AppScreen.GUIDES,
                    onClick = { currentScreen = AppScreen.GUIDES },
                    icon = { Icon(Icons.Default.MenuBook, contentDescription = "Document and regional guidance directories navigation tab") },
                    label = { Text("Guides") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SoftGold,
                        selectedTextColor = SoftGold,
                        unselectedIconColor = LightText.copy(alpha = 0.4f),
                        unselectedTextColor = LightText.copy(alpha = 0.4f),
                        indicatorColor = MutedGreen.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.testTag("nav_guides")
                )
                NavigationBarItem(
                    selected = currentScreen == AppScreen.RESOURCES,
                    onClick = { currentScreen = AppScreen.RESOURCES },
                    icon = { Icon(Icons.Default.AccountBalance, contentDescription = "Government resources and reading archive navigation tab") },
                    label = { Text("Resources") },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = SoftGold,
                        selectedTextColor = SoftGold,
                        unselectedIconColor = LightText.copy(alpha = 0.4f),
                        unselectedTextColor = LightText.copy(alpha = 0.4f),
                        indicatorColor = MutedGreen.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier.testTag("nav_resources")
                )
            }
        }
    ) { innerPadding ->
        CivicBackground {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (currentScreen) {
                    AppScreen.HOME -> {
                        HomeScreen(
                            onNavigateToCheck = { currentScreen = AppScreen.PROPERTY_CHECK },
                            onNavigateToRedFlags = { currentScreen = AppScreen.RED_FLAGS }
                        )
                    }
                    AppScreen.PROPERTY_CHECK -> {
                        PropertyCheckScreen()
                    }
                    AppScreen.RED_FLAGS -> {
                        RedFlagsScreen()
                    }
                    AppScreen.GUIDES -> {
                        GuidesScreen()
                    }
                    AppScreen.RESOURCES -> {
                        ResourcesScreen()
                    }
                }
            }
        }
    }
}
