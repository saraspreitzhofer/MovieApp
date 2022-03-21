package com.example.movieapp.screens.detail


import android.text.Layout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.widgets.HorizontalScrollableImageView
import com.example.movieapp.widgets.MovieRow

@Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "tt0499549"
){
    val movie = filterMovie(movieId = movieId)
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
                    Text(text = movie.title, style = MaterialTheme.typography.h6)
                }
            }
        }
    ) {
        MainContent(movie = movie)
    }
}

@Composable
fun MainContent(movie: Movie){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            MovieRow(movie = movie)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Movie Images", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            HorizontalScrollableImageView(movie = movie)
        }
    }
}

fun filterMovie(movieId: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]   // erstes Element der 1-elementigen Liste (ID ist unique)
    // return getMovies().filter { it.id == movieId }[0]   // auch möglich
}