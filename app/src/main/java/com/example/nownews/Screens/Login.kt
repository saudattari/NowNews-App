package com.example.nownews.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.nownews.R


@Preview(showBackground = true)
@Composable
fun Login() {
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            Column(modifier = Modifier.fillMaxSize().padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                Image(painter = rememberAsyncImagePainter(R.drawable.news),
                    contentDescription = "New icon",
                    modifier = Modifier.size(100.dp))
                Text(text = "Login", color = Color.Red, fontSize = 25.sp, fontWeight = FontWeight.Bold)
                TextInputField(label = "Email"){email = it}
                TextInputField(label = "Password"){password = it}
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    modifier = Modifier
                        .fillMaxWidth()
                    .height(58.dp)
                    .padding(horizontal = 16.dp)) {
                    Text(text = "Login", fontSize = 20.sp)
                }
            }
        }
    }
}

@Composable
fun TextInputField(label: String,onValueChange: (String) -> Unit) {
    var text by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = label, fontSize = 15.sp)
        OutlinedTextField(
            value = text, onValueChange = {
                text = it
                 onValueChange(text)},
            shape = RoundedCornerShape(10.dp)
        )
    }
}