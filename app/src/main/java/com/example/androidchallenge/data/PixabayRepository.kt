package com.example.androidchallenge.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.androidchallenge.api.PixabayApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PixabayRepository @Inject constructor (private val pixabayApi: PixabayApi) {

    fun getSearchResults(query: String,) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PixabayPagingSource(pixabayApi, query)}
        ).liveData
}