package com.example.splashappcloud.nav

sealed class Screens(
    val route: String
) {
    object MainScreens: Screens(route = "main_screens")

    object GetDataScreen: Screens(route = "get_data_screen")

    object AddDataScreen: Screens(route = "add_data_screen")
}
