package com.example.reviewcompany.data.mapper

import com.example.reviewcompany.data.model.DataArticle
import com.example.reviewcompany.domain.model.DomainArticle

object Mapper {

    fun articleMapper(
        domainArticle: DomainArticle
    ) : DataArticle {
        return DataArticle(
            articleId = domainArticle.articleId,
            uid = domainArticle.uid,
            nickName = domainArticle.nickName,
            category = domainArticle.category,
            companyName = domainArticle.companyName,
            content = domainArticle.content
        )
    }
}