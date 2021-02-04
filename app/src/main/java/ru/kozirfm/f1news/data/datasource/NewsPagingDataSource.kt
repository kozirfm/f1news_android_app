package ru.kozirfm.f1news.data.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import ru.kozirfm.f1news.data.entites.Article
import ru.kozirfm.f1news.data.retrofit.RetrofitService
import java.io.IOException

class NewsPagingDataSource(private val api: RetrofitService) : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val currentLoadingPageKey = params.key ?: 1
        return try {
            val response = api.getArticlesPage(currentLoadingPageKey)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey.minus(1)
            val nextKey = if (response.count() < 20) null else currentLoadingPageKey.plus(1)
            LoadResult.Page(
                data = response,
                prevKey = prevKey,
                nextKey = nextKey,
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }
}