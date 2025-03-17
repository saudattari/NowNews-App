package com.example.nownews.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nownews.Screens.Login
import com.example.nownews.Screens.MainScreen
import com.example.nownews.Screens.SignUp
import com.example.nownews.Screens.SplashScreen

@Composable
fun NavigationController(navController: NavHostController) {
    NavHost(navController = navController,startDestination = "splashScreen") {
        composable("logIn") {
            Login()
        }
        composable("splashScreen") {
            SplashScreen(navController)
        }
        composable("signUp") {
            SignUp()
        }
        composable("mainScreen") {
            MainScreen()
        }
    }
}