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

    private val _firebaseId = MutableStateFlow<List<String>>(listOf())
    val firebaseId: StateFlow<List<String>> = _firebaseId.asStateFlow()

    private val articleList = mutableListOf<ArticleEntity>()
    private val idList = mutableListOf<String>()

    fun getArticle() =
        viewModelScope.launch {
            try {
                _firebaseList.value = _firebaseList.value.toMutableList().also {
                    it.clear()
                }

                _firebaseId.value = _firebaseId.value.toMutableList().also {
                    it.clear()
                }


                articleList.clear()
                idList.clear()

                val results = firebaseRepository.getArticle()
                for (result in results) {
                    val item = result.toObject(ArticleEntity::class.java)

                    articleList.add(item)
                    idList.add(result.id)

                }
                _firebaseList.emit(articleList)
                _firebaseId.emit(idList)


            } catch (e: Exception) {
                Log.e("GetArticleErr", e.toString())
            }
        }

}