package com.example.nownews.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nownews.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding).background(Color.White)
                .fillMaxSize().background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(R.drawable.newss), contentDescription = "New Image")
                Text(
                    text = "Now News",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Red,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }
            LaunchedEffect(Unit) {
                delay(2000)
                navController.navigate("logIn"){
                    popUpTo("splashScreen"){
                        inclusive = true
                    }
                }
            }

        }

    }
}