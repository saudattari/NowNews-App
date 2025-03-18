package com.example.nownews.Screens

import android.widget.Toast
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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.example.nownews.DataModel.Article
import com.example.nownews.ViewModelStructure.NewsViewModel
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import com.example.nownews.DataModel.NewsResponse
import com.example.nownews.R
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Locale

@Composable
fun MainScreen(viewModel: NewsViewModel = viewModel(), navController: NavController) {
    val newsList by viewModel.newsState.collectAsState()
    val context = LocalContext.current
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
                NewsCategories(onClick = { post->
                    Toast.makeText(context, "category :$post", Toast.LENGTH_SHORT).show()
                    viewModel.fetchNews(post.lowercase(Locale.ROOT), "pk", "992786c2b3ce3b6f6afea6acf66d4420" ,"")
                },viewModel)
                Spacer(modifier = Modifier.height(10.dp))
                if (newsList?.articles?.isNotEmpty() == true) {
                    newsList?.articles?.let { articles ->
                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                            items(articles) { article ->
                                NewsItem(article, navController)
                            }
                        }
                    }
                }
                else{
                    if (viewModel.isLoading.collectAsState().value){
                        CircularProgressIndicator(strokeWidth = 2.dp,strokeCap = StrokeCap.Round, modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.Red)
                    }
                }

            }
        })
}



@Composable
fun NewsItem(data: Article,navController: NavController) {
    val imageUrl = data.image ?:"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUrgu4a7W_OM8LmAuN7Prk8dzWXm7PVB_FmA&s"

    val painter = if(data.image != null){rememberAsyncImagePainter(data.image)}else{R.drawable.news}
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clickable {
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
                Text(text = data.source?.name ?: "", fontSize = 15.sp, fontWeight = FontWeight.Bold, maxLines = 2)
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
fun NewsCategories(onClick: (String) -> Unit, viewModel: NewsViewModel) {
    var SerachQuery by remember { mutableStateOf("") }
    var isExpanded by remember { mutableStateOf(false) }

    val list  = listOf("GENERAL", "BUSINESS" ,"TECHNOLOGY", "ENTERTAINMENT", "SPORTS", "SCIENCE", "HEALTH","WORLD")
    Row(modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState())) {
        if(isExpanded){
            OutlinedTextField(
                value = SerachQuery,
                onValueChange = {
                    SerachQuery = it
                    if(it.length > 2){
                        viewModel.fetchNews("general", "pk", "992786c2b3ce3b6f6afea6acf66d4420",it)
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Red, focusedLabelColor = Color.Red),
                label = {Text(text = "Search News...")},
                trailingIcon = {IconButton(onClick = {viewModel.fetchNews("general", "pk", "992786c2b3ce3b6f6afea6acf66d4420",SerachQuery)
                    isExpanded = false}) { Icon(Icons.Default.Search, contentDescription = "Search")}},
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .height(48.dp),

            )
        }
        else{
            IconButton(onClick = { isExpanded = true }) {
                Icon(Icons.Default.Search, contentDescription = "Search Icon", modifier = Modifier.size(25.dp))
            }
        }
//        if(!isExpanded){
//            IconButton(onClick = { isExpanded = true }) {
//                Icon(Icons.Default.Search, contentDescription = "Search Icon", modifier = Modifier.size(25.dp))
//            }
//        }
        list.forEach {category->
            Button(
                modifier = Modifier.padding(horizontal = 10.dp),
                onClick = {
                    onClick(category)
                },
                border = BorderStroke(1.dp, Color.Red),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = Color.Red))
            {
                Text(text = category)
            }
        }
    }
}
