package com.example.reviewcompany.presentation.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class SignUpViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel() {

    private var _signUpState = MutableStateFlow<Boolean>(false)
    val registerState: StateFlow<Boolean> = _signUpState.asStateFlow()


    fun createAccount(email: String, password: String) = viewModelScope.launch {
        viewModelScope.launch {
            try {
                _signUpState.emit(repository.signUp(email, password))
            } catch (e: Exception) {
                Log.e("CreateAccountErr", e.toString())
            }
        }
    }

}