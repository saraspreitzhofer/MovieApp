package com.example.movieapp.screens.favorite


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.viewmodels.FavoritesViewModel
import com.example.movieapp.widgets.MovieRow

@Composable
fun FavoriteScreen( navController: NavController = rememberNavController(),
                    viewModel: FavoritesViewModel
){
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Cyan, elevation = 3.dp){
                Row {
                    Icon(imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Up Button",
                        modifier = Modifier.clickable {
                            navController.popBackStack()    // go back to last screen
                        })
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Favorites", style = MaterialTheme.typography.h6)
                }
            }
        }
    ) {
        MainContent(movieList = viewModel.favoriteMovies, navController = navController)
    }
}

@Composable
fun MainContent(movieList: List<Movie>,
                navController: NavController){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        LazyColumn {
            items(items = movieList){ movie ->
                Log.i("FavIcon", "display movie ${movie.title} in fav screen")
                MovieRow(movie = movie,
                    onItemClick = {movieId ->
                        navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")}) }
        }

        /*Column {
            // MovieRow(movieList[0])
            // MovieRow(movieList[1])
            movieList.forEach {movie -> MovieRow(movie)}
        }*/
    }
}