package ru.kozirfm.f1news.data.model

import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.entites.News

class ArticleMapper {
    fun mapArticlesToNews(article: Article): News {
        return when {
            article.images != null -> News.NewsWithImage(
                id = article.id,
                date = article.date,
                title = article.title,
                link = article.link,
                text = article.text,
                images = article.images
            )
            else -> News.NewsSimple(
                id = article.id,
                date = article.date,
                title = article.title,
                link = article.link,
                text = article.text
            )
        }
    }
}