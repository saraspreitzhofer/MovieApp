package com.example.movieapp.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.detail.DetailScreen
import com.example.movieapp.screens.favorite.FavoriteScreen
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.viewmodels.FavoritesViewModel

/*LD 3
  controller: central API for navigation instructions, stateful
  host: hosts each navigation graph item and swaps out each destination (composable) when user navigates to another destination
  graph: holds information about destinations (composables) and maps out all of the destination and its information*/

@Composable
fun MovieNavigation(){
    val navController = rememberNavController() // create navController instance
    val favoritesViewModel: FavoritesViewModel = viewModel()    // Initialize a ViewModel inside a composable
    // Initialize and access ViewModel inside MainActivity (outside of composable scope, eg setContent):
    // val favoritesViewModel: FavoritesViewModel by viewModels()

    NavHost(navController = navController,  // pass the navController instance to the NavHost
        startDestination = MovieScreens.HomeScreen.name){   // pass a start destination
        composable(MovieScreens.HomeScreen.name){   // route
            HomeScreen(navController = navController, // navController als argument übergeben, damit man ihn in HomeScreen nutzen kann
                viewModel = favoritesViewModel)}

        // url: www.domain.com/detailscreen/id=12
        composable(MovieScreens.DetailScreen.name + "/{movieId}",    // definition des pfades mit placeholder
                arguments = listOf(navArgument("movieId") { // definition des namens des arguments
                    type = NavType.StringType   // default ist string
                })
            ){ backStackEntry ->    // Extract NavArguments from the BackStackEntry to pass them into composables (argument aus der navigation aus dem back stack herausholen)
                DetailScreen(navController = navController,
                    movieId = backStackEntry.arguments?.getString("movieId"),
                    viewModel = favoritesViewModel)
            }
        /*LD 3
        Create a FavoritesScreen composable and add it to your MovieNavigation.*/
        composable(MovieScreens.FavoriteScreen.name){
            FavoriteScreen(navController = navController,
                viewModel = favoritesViewModel) }

// add more routes and screen here (nav graph)
}
}