package com.example.nownews.Screens

import android.R
import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsViewScreen(postUrl: String) {
    val decodedUrl = postUrl.let { URLDecoder.decode(it, StandardCharsets.UTF_8.toString()) } ?: ""
    Scaffold { innerPadding->
        Box(modifier = Modifier.padding(innerPadding)){
            Column(modifier = Modifier.fillMaxSize().padding(top = 10.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) { Text(text = "Now News", fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, color = Color.Red, fontSize = 22.sp, textAlign = TextAlign.Center) }
                Spacer(modifier = Modifier.height(12.dp))
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = {
                        WebView(it).apply {
                            settings.javaScriptEnabled = true
                            webViewClient = WebViewClient()
                            loadUrl(decodedUrl)
                        }
                    }
                )
            }
        }

    }
}