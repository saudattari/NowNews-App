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
    private var _newsState = MutableStateFlow<NewsResponse?>(null)
    val newsState: StateFlow<NewsResponse?> = _newsState


    init {
        fetchNews("us", "992786c2b3ce3b6f6afea6acf66d4420" )
    }

    fun fetchNews( country:String, api: String){
        viewModelScope.launch {
            try{
                val response = RetrofitInstance.api.getTopHeadlines("general",country,api)
                _newsState.value = response
//                Toast.makeText(context, "${response.status}", Toast.LENGTH_SHORT).show()
            }catch (e: Exception){
//                Toast.makeText(context, "${e.message}, ${e.localizedMessage}", Toast.LENGTH_SHORT).show(
            }
        }
    }
}