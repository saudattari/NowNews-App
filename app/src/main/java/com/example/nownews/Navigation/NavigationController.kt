package com.example.nownews.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.nownews.Screens.Login
import com.example.nownews.Screens.MainScreen
import com.example.nownews.Screens.NewsViewScreen
import com.example.nownews.Screens.SignUp
import com.example.nownews.Screens.SplashScreen
import com.example.nownews.ViewModelStructure.NewsViewModel

@Composable
fun NavigationController(navController: NavHostController) {
    val viewModel: NewsViewModel = viewModel()
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
            MainScreen(viewModel, navController)
        }
        composable ("newsViewScreen/{url}"){
            val url = it.arguments?.getString("url") ?: "https://cdn2.iconfinder.com/data/icons/symbol-blue-set-3/100/Untitled-1-94-1024.png"
            NewsViewScreen(url)
        }
    }
}