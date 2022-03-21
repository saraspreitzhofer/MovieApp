package com.example.movieapp.screens.home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.widgets.MovieRow

@Composable
fun HomeScreen(navController: NavController = rememberNavController()){
    var showMenu by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                backgroundColor = Color.Cyan,
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
                    }
                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = {
                            navController.navigate(route = MovieScreens.FavoriteScreen.name)
                        }) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "Favorites",
                                    modifier = Modifier.padding(3.dp)
                                )
                                Text(
                                    text = "Favorites",
                                    modifier = Modifier
                                        .padding(3.dp)
                                        .width(100.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
    ) {
        mainContent(navController = navController)
    }
}

@Composable
fun mainContent(navController: NavController, movieList: List<Movie> = getMovies()){
    LazyColumn {
        // item { Text(text = "sth") }     // add a single composable to LazyColumn
        items(items = movieList) { movie ->     // add a list of composables to LazyColumn
            MovieRow(movie = movie){ movieId -> // render MovieRow composable for each item
                navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
            } }
        // itemsIndexed(movieList){index, movie -> MovieRow(movie)}    // add a list of composables with index
    }
}