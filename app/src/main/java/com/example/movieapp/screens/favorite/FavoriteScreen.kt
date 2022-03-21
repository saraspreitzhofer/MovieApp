package com.example.movieapp.screens.favorite


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Preview(showBackground = true)
@Composable
fun FavoriteScreen( navController: NavController = rememberNavController() ){
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
        MainContent()
    }
}

@Composable
fun MainContent(){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column {
            Text(text = "My Favorites", style = MaterialTheme.typography.h5)
        }
    }
}