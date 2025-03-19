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
import com.example.nownews.ViewModelStructure.AuthViewModel
import com.example.nownews.ViewModelStructure.NewsViewModel

@Composable
fun NavigationController(navController: NavHostController) {
    val viewModel: NewsViewModel = viewModel()
    val authViewModel:AuthViewModel = viewModel()
    NavHost(navController = navController,startDestination = "splashScreen") {
        composable("logIn") {
            Login(authViewModel, navController)
        }
        composable("splashScreen") {
            SplashScreen(navController)
        }
        composable("signUp") {
            SignUp(authViewModel,navController)
        }
        composable("mainScreen") {
            MainScreen(viewModel, navController)
        }
        composable ("newsViewScreen/{url}"){
            val url = it.arguments?.getString("url") ?:""
            NewsViewScreen(url)
        }
    }
}