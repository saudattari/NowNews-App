package com.example.nownews.Screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import com.example.nownews.R
import com.example.nownews.ViewModelStructure.AuthViewModel

@Composable
fun SignUp(authViewModel: AuthViewModel = viewModel(),navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val firebaseUser by authViewModel.authUser.collectAsState()
    val isLoading = authViewModel.isLoading.value
    val error  = authViewModel.error.value
    var isFieldFill by remember { mutableStateOf("") }
    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Image(painter = rememberAsyncImagePainter(R.drawable.newss),
                    contentDescription = "New icon",
                    modifier = Modifier.size(100.dp))
                Text(text = "SignUp", color = Color.Red, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                TextInputField(label = "Name"){name = it}
                TextInputField(label = "Email"){email = it}
                TextInputField(label = "Password"){password = it}
                Button(onClick = { if(name.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()){
                    authViewModel.signUpUser(email,password)
                    isFieldFill = ""
                }else{isFieldFill = "Please fill all fields"} }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .padding(horizontal = 16.dp),
                    enabled = !isLoading) {
                    if (isLoading){
                        CircularProgressIndicator(color = Color.White, strokeWidth = 2.dp, strokeCap = StrokeCap.Round)
                    }else{
                        Text(text = "Sign Up", fontSize = 20.sp)
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                if(isFieldFill.isNotEmpty()){
                    Text(text = isFieldFill, color = Color.Red)
                }else{
                    Text(text = "")
                }
                if(error.isNotEmpty()){
                    Text(text = "Error: $error", color = Color.Red)
                }
                if (firebaseUser!=null){
                    Text(text = "Signup successfully as ${firebaseUser?.email}", color = Color(0xFF015201))
                    authViewModel.setErrorEmpty()
                    navController.navigate("mainScreen"){
                        popUpTo("logIn"){
                            inclusive = true
                        }
                        popUpTo("signUp"){
                            inclusive = true
                        }
                    }
                }
                Row(modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(text = "Already Have an Account:", modifier = Modifier, textAlign = TextAlign.Center)
                    TextButton(onClick = {navController.navigate("logIn");authViewModel.setErrorEmpty()}){
                        Text(text = "Login", color = Color.Gray, textDecoration = TextDecoration.Underline)
                    }
                }
            }
        }
    }
}