package com.example.reviewcompany.data


data class ArticleEntity(
    val articleId: String = "",
    val uid: String = "",
    val nickName: String = "",
    val category: String = "",
    val companyName: String = "",
    val content: String= ""
) : java.io.Serializable
