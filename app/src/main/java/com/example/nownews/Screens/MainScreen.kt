@file:Suppress("DEPRECATION")

package com.example.nownews.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.nownews.DataModel.Article
import com.example.nownews.ViewModelStructure.NewsViewModel
import androidx.navigation.NavController

@Composable
fun MainScreen(viewModel: NewsViewModel = viewModel(), navController: NavController) {
    val newsList by viewModel.newsState.collectAsState()
    Scaffold(
        topBar = {
            ActionBar(){
                when(it){
                    "Log out"->{/*TODO*/}
                    "Settings Screen"->{/*TODO*/}
                    "Favourite Screen"->{/*TODO*/}
                }
            } },
        content = {innerPadding->
            Column(modifier = Modifier.padding(innerPadding)) {
                Spacer(modifier = Modifier.height(12.dp))
                NewsCategories(){
                    viewModel.fetchNews("us", "992786c2b3ce3b6f6afea6acf66d4420")
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (newsList?.articles?.isNotEmpty() == true) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                        items(newsList!!.articles) { article ->
                            NewsItem(article,navController)
                        }
                    }
                }
                else{
                    Text(text = "Non data")
                }

            }
        })
}

@Composable
fun NewsItem(data: Article,navController: NavController) {
    val imageUrl = data.image.toString()
    Card(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .padding(12.dp)
        .clickable{
            navController.navigate("newsViewScreen/${data.url}")
        }
    )
    {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(100.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 8.dp, bottom = 10.dp, top = 8.dp)
            ) {
                Text(text = data.title ?: "", fontSize = 16.sp, fontFamily = FontFamily.Serif)
                Spacer(modifier = Modifier.weight(1f))
                Text(text = data.url ?: "", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}


@Composable
fun ActionBar(onClicks:(String)-> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    val listDropDownItems = listOf<String>("Log out", "Settings Screen", "Favourite Screen")
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp, horizontal = 10.dp),verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Now News", fontFamily = FontFamily.Serif,
            fontSize = 25.sp, fontWeight = FontWeight.Bold,
            color = Color.Red,textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = {isExpanded = true}) {
            Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More", tint = Color.Red, modifier = Modifier.padding(end = 10.dp)) }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false },
            offset = DpOffset(220.dp, 0.dp),
            content = {
                listDropDownItems.forEach {item->
                    DropdownMenuItem(
                        text = {Text(text = item)},
                        onClick = {
                            isExpanded = false
                            onClicks(item)}
                    )
                }
            }
        )
    }
}



@Composable
fun NewsCategories(onlClick:(String) -> Unit) {
    val list  = listOf("GENERAL", "BUSINESS" ,"TECHNOLOGY", "ENTERTAINMENT", "SPORTS", "SCIENCE", "HEALTH")
    Row(modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState())) {
        list.forEach {category->
            Button(
                modifier = Modifier.padding(horizontal = 10.dp),
                onClick = { onlClick(category) },
                border = BorderStroke(1.dp, Color.Red),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Red))
            {
                Text(text = category)
            }
        }
    }
}