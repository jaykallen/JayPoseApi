package com.example.jayposeapi2

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route + "/{id}/{title}/{author}/{status}/{fee}/{lastEdited}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                    defaultValue = "1"
                    nullable = false
                }
            )
        ) { entry ->
            DetailScreen(
                navController = navController,
                id = entry.arguments?.getString("id") ?:"",
                title = entry.arguments?.getString("title") ?:"",
                author = entry.arguments?.getString("author") ?:"",
                status = entry.arguments?.getString("status") ?:"",
                fee = entry.arguments?.getString("fee") ?:"",
                lastEdited = entry.arguments?.getString("lastEdited") ?:""
            )
        }
    }
}