package com.example.bestrickandmorty.domain.common.base

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.bestrickandmorty.data.common.response.RickAndMortyResponse
import java.io.IOException


abstract class BasePagingSource<ValueDto : Any, Value : Any>(
    private val task: suspend (position: Int) -> RickAndMortyResponse<ValueDto>,
    private val metaData: (data: List<ValueDto>) -> List<Value>
) : PagingSource<Int, Value>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Value> {
        val position = params.key ?: 1
        return try {
            val response = task(position)
            val next = response.info.next
            var nextPageNumber: Int? = null
            if (next != null) {
                val uri = Uri.parse(response.info.next)
                val nextPageQuery = uri.getQueryParameter("page")
                nextPageNumber = nextPageQuery?.toInt()
            }
            LoadResult.Page(
                data = metaData(response.results),
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Value>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}