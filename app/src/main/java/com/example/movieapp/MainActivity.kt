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

    // Android Lifecycle
    // mit control und o kann man sich alle override Funktionen von ComponentActivity anzeigen lassen

    // Activity launched -> entry point, called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        // bundle Objekt bildet vorherigen Zustand ab, was vorher in der Activity enthalten war
        // damit kann vorheriger Zustand wieder hergestellt werden, nur bei erstem Aufruf von onCreate ist es null
        super.onCreate(savedInstanceState)  // Vererbung von ComponentActivity
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
                        // Use your created navigation composable in MainActivity. This will start
                        // the application with previously defined startDestination in NavHost
                        MovieNavigation()
                    }
                }
            }
        }
    }
    // Called when the activity is becoming visible to the user.
    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "onStart called")
    }
    // Called after your activity has been stopped, prior to it being started again.
    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "onRestart called")
    }
    // Called when the activity will start interacting with the user. -> Activity is running
    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "onResume called")
    }
    // Called when the system is about to start resuming a previous activity. This is typically used to
    // commit unsaved changes to persistent data, stop animations and other things that may be consuming CPU.
    // -> Another Activity comes into the foreground
    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "onPause called")
    }
    // Called when the activity is no longer visible to the user.
    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "onStop called")
    }
    // The final call you receive before your activity is destroyed.
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