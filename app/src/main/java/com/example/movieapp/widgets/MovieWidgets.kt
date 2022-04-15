package com.example.movieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.viewmodels.FavoritesViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MovieRow(movie: Movie = getMovies()[0],
             onItemClick: (String) -> Unit = {},    // callback function to display detail screen
             onFavoriteItemClick: (Movie) -> Unit = {},
             content: @Composable () -> Unit = {}    //callback function to display favorite icon
             ){
    var arrowClicked by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .heightIn(150.dp, 500.dp)
        .padding(10.dp)
        .clickable { onItemClick(movie.id) },
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        elevation = 10.dp
    ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(modifier = Modifier
                .size(120.dp)
                .padding(5.dp)
                //shape = RectangleShape
                //elevation = 5.dp
            ) {
                /* deprecated:
                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                        builder = {transformations(CircleCropTransformation())}
                    ),
                    contentDescription = "Movie Poster",
                    modifier = Modifier.size(128.dp))*/
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .build(),
                    //placeholder = painterResource(R.drawable.placeholder),
                    contentDescription = "Movie Poster",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.clip(CircleShape)
                )
            }
            Column (modifier = Modifier
                .width(200.dp)
                .padding(start = 10.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)
                AnimatedVisibility(visible = arrowClicked) {
                    Column (modifier = Modifier.padding(5.dp)){
                        Text(text = "Plot: ${movie.plot}", style = MaterialTheme.typography.caption)
                        Divider()
                        Text(text = "Genre: ${movie.genre}", style = MaterialTheme.typography.caption)
                        Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.caption)
                    }
                }
                Card(modifier = Modifier.clickable(onClick = { arrowClicked = !arrowClicked })) {
                    if (arrowClicked) Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Arrow Up")
                    else Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Arrow Down")
                }
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
                horizontalAlignment = Alignment.End){
                // Favorite Icon
                content()
            }
        }
    }
}

@Composable
fun HorizontalScrollableImageView(movie: Movie = getMovies()[0]){
    LazyRow {
        items(movie.images){ image -> 
            Card (
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 4.dp){
                /* deprecated:
                Image(
                    painter = rememberImagePainter(data = image),
                    contentDescription = "movie image") */
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(image)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Composable
fun FavoriteIcon(movie: Movie,
                 isFavorite: Boolean = false,
                 onFavoriteItemClick: (Movie) -> Unit = {}) {
    IconButton(onClick = { onFavoriteItemClick(movie)}) {
        if (isFavorite) {
            // favoritesViewModel.addMovie(movie)
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                tint = Color.Cyan
            )
        }
        else {
            // favoritesViewModel.removeMovie(movie)
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite Icon",
                tint = Color.Cyan
            )
        }
    }

}
