package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.detail.DetailScreen
import com.example.movieapp.screens.favorite.FavoriteScreen
import com.example.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name){
        composable(MovieScreens.HomeScreen.name){ HomeScreen(navController = navController)}    // navController als argument übergeben, damit man ihn in HomeScreen nutzen kann

        // url: www.domain.com/detailscreen/id=12
        composable(MovieScreens.DetailScreen.name + "/{movieId}",    // definition des pfades
                arguments = listOf(navArgument("movieId") { // definition des namens des arguments
                    type = NavType.StringType
                })
            ){ backStackEntry ->
                DetailScreen(navController = navController, backStackEntry.arguments?.getString("movieId")) // argument aus der navigation aus dem back stack herausholen
            }
        composable(MovieScreens.FavoriteScreen.name){ FavoriteScreen(navController = navController) }
        // add more routes and screen here (nav graph)
    }
}