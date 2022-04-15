package com.example.movieapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "onCreate called")

        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // start screen is base destination of the back stack
                    MyApp{
                        MovieNavigation()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart called")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart called")
    }
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume called")
    }
    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause called")
    }
    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "onDestroy called")
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    content()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieAppTheme {
        MyApp{
            MovieNavigation()
        }
    }
}