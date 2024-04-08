package com.example.reviewcompany.domain.model


data class DomainArticle(
    val articleId: String = "",
    val uid: String = "",
    val nickName: String = "",
    val category: String = "",
    val companyName: String = "",
    val content: String = "",
) : java.io.Serializable
