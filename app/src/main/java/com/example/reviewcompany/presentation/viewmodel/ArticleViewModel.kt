package com.example.reviewcompany.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewcompany.data.ArticleEntity
import com.example.reviewcompany.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    private val repository: FirebaseRepository
) : ViewModel(){

    private var _deleteState = MutableStateFlow<Boolean>(false)
    val deleteState : StateFlow<Boolean> = _deleteState.asStateFlow()

    fun deleteArticle(articleEntity: ArticleEntity?) = viewModelScope.launch {

        try {
            _deleteState.emit(repository.deleteArticle(articleEntity = articleEntity))
        } catch (e: Exception) {
            Log.e("Write", e.toString())
        }
    }

}