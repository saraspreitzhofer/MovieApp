package com.example.movieapp.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Movie

class FavoritesViewModel : ViewModel() {
    private val _favoriteMovies = mutableStateListOf<Movie>()
    val favoriteMovies: List<Movie>
        get() = _favoriteMovies

    // Add a movie to favorites
    fun addMovie(movie: Movie){
        if (!isFavorite(movie = movie)) {
            _favoriteMovies.add(movie)
        }
    }

    // Remove a movie from favorites
    fun removeMovie(movie: Movie){
        _favoriteMovies.remove(movie)
    }

    // Get all movies from favorites list
    fun getAllMovies(): List<Movie>{
        return _favoriteMovies
    }

    // Check if a movie is already a favorite
    fun isFavorite(movie: Movie): Boolean {
        // return _favoriteMovies.contains(movie)
        return _favoriteMovies.any {m -> m.id == movie.id}
    }
}