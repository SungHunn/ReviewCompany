package com.example.reviewcompany.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewcompany.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: FirebaseRepository,
) : ViewModel() {

    private var _loginState = MutableStateFlow<Boolean>(false)
    val loginState: StateFlow<Boolean> = _loginState.asStateFlow()


    fun login(email: String, password: String) = viewModelScope.launch {
        viewModelScope.launch {
            try {
                _loginState.emit(repository.logIn(email, password))
            } catch (e: Exception) {
                Log.e("LogIn", e.toString())
            }
        }
    }


}