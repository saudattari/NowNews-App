package com.example.nownews.ViewModelStructure

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow

class AuthViewModel: ViewModel() {
    private val _authUser = MutableStateFlow<com.google.firebase.auth.FirebaseUser?>(null)
    val authUser = _authUser
    private var _isLoading = mutableStateOf(false)
    val isLoading = _isLoading

    private var _error = mutableStateOf("")
    val error = _error

    fun loginUser(email: String, password: String){
        _isLoading.value = true
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                _isLoading.value = false
                if (it.isSuccessful){
                _authUser.value = it.result?.user
                }
                else{
                    _error.value = it.exception?.message.toString()
                }
            }
    }

    fun signUpUser(email: String, password: String){
        _isLoading.value = true
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener {
                _isLoading.value = false
                if(it.isSuccessful){
                    _authUser.value = it.result?.user
                }
                else{
                    _error.value = it.exception?.message.toString()
                }
            }
    }

    fun setErrorEmpty(){
        _error.value = ""
    }
    fun logOut(){
        FirebaseAuth.getInstance().signOut()
        _authUser.value = null
    }
}