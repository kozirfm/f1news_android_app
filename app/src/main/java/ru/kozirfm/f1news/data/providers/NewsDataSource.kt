package ru.kozirfm.f1news.data.providers

import androidx.paging.PagingSource
import retrofit2.HttpException
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.retrofit.RetrofitService
import java.io.IOException

class NewsDataSource(private val api: RetrofitService) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val currentLoadingPageKey = params.key ?: 1
        return try {
            val response = api.getArticlesPage(currentLoadingPageKey)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey.minus(1)
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}