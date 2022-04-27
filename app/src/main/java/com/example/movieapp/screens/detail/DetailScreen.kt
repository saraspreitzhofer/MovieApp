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
import com.example.movieapp.viewmodels.FavoritesViewModel
import com.example.movieapp.widgets.FavoriteIcon
import com.example.movieapp.widgets.HorizontalScrollableImageView
import com.example.movieapp.widgets.MovieRow

// @Preview(showBackground = true)
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "tt0499549",
    viewModel: FavoritesViewModel
){
    val movie = filterMovie(movieId = movieId)
    /* LD 3
    Add a TopAppBar to your DetailScreen. It should show the selected movie’s title and an ArrowBack
    icon. When the ArrowBack icon is clicked, the app should navigate to the previous screen.*/
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
        MainContent(movie = movie, favoritesViewModel = viewModel)
    }
}

@Composable
fun MainContent(movie: Movie, favoritesViewModel: FavoritesViewModel){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            /* LD 3
            (Re)Use your MovieRow composable inside DetailScreen, to show selected movie’s information*/
            MovieRow(movie = movie){
                /*LD 4
                Integrate the FavoriteIcon in MovieRow composable.
                In the HomeScreen and DetailScreen MovieRows shall be displayed with FavoriteIcon.*/
                FavoriteIcon(
                    movie = movie,
                    isFavorite = favoritesViewModel.isFavorite(movie)
                ) { favMovie ->
                    if(favoritesViewModel.isFavorite(favMovie)){
                        favoritesViewModel.removeMovie(favMovie)
                    } else {
                        favoritesViewModel.addMovie(favMovie)
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Movie Images", style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))
            /* LD 3
            Coil: Extend the DetailScreen with a LazyRow that shows all movie images inside scrollable Card composables*/
            HorizontalScrollableImageView(movie = movie)
        }
    }
}

fun filterMovie(movieId: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]   // erstes Element der 1-elementigen Liste (ID ist unique)
    // return getMovies().filter { it.id == movieId }[0]   // auch möglich
}