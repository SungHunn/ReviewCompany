package com.example.reviewcompany.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewcompany.domain.model.DomainArticle
import com.example.reviewcompany.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WritingViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel(){

    private var _writingState = MutableStateFlow<Boolean>(false)
    val writingState: StateFlow<Boolean> = _writingState.asStateFlow()


    fun writeArticle(domainArticle : DomainArticle) = viewModelScope.launch {
        viewModelScope.launch {
            try {
                _writingState.emit(firebaseRepository.insertArticle(domainArticle))
            } catch (e: Exception) {
                Log.e("Write", e.toString())
            }
        }
    }
}