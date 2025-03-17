package com.example.nownews.Screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsViewScreen(postUrl: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            WebView(it).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl(postUrl)
            }
        }
    )
}