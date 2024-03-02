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
class MainViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
) : ViewModel() {

    private val _firebaseList = MutableStateFlow<List<ArticleEntity>>(listOf())
    val firebaseList: StateFlow<List<ArticleEntity>> = _firebaseList.asStateFlow()

    private val articleList = mutableListOf<ArticleEntity>()

    fun getArticle() =
        viewModelScope.launch {
            try {
                _firebaseList.value = _firebaseList.value.toMutableList().also {
                    it.clear()
                }
                articleList.clear()

                val results = firebaseRepository.getArticle()
                for (result in results) {
                    val item = result.toObject(ArticleEntity::class.java)
                    articleList.add(item)

                }
                _firebaseList.emit(articleList)
                Log.e("ArticleViewModel", "${_firebaseList.value.size}")


            } catch (e: Exception) {
                Log.e("GetArticleErr", e.toString())
            }
        }

}