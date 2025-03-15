package com.example.nownews.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun LogIn() {
    Scaffold { innerPadding->
        Box(modifier = Modifier.padding(innerPadding)){
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {

            }
        }
    }
}