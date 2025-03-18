package com.example.nownews.ViewModelStructure


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nownews.DataModel.Article
import com.example.nownews.DataModel.NewsResponse
import com.example.nownews.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel(): ViewModel() {
    private val _newsState = MutableStateFlow<NewsResponse?>(null)
    val newsState: StateFlow<NewsResponse?> = _newsState

    var isLoading = MutableStateFlow(false)


    init {
        fetchNews("general","pk", "992786c2b3ce3b6f6afea6acf66d4420" ,"")
    }

    fun fetchNews( category: String,country:String, api: String,query: String){
        viewModelScope.launch {
            isLoading.value = true
            try{
                val response = RetrofitInstance.api.getTopHeadlines(category,country,api,query)
                _newsState.value = response
                isLoading.value = false
//                Toast.makeText(context, "${response.status}", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
//                Toast.makeText(context, "${e.message}, ${e.localizedMessage}", Toast.LENGTH_SHORT).show(
            }
            finally {
                isLoading.value = false
            }
        }
    }
}