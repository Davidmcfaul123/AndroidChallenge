package com.example.androidchallenge.data

import androidx.paging.PagingSource
import com.example.androidchallenge.api.PixabayApi
import retrofit2.HttpException
import java.io.IOException

private const val PIXABAY_STARTING_PAGE_INDEX = 1

class PixabayPagingSource(
    private val pixabayApi: PixabayApi,
    private val query: String,
    private val key: String = "23946342-33729d835008fd70e2e4e1fe6"
) : PagingSource<Int, PixabayPhoto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PixabayPhoto> {
        val position = params.key ?: PIXABAY_STARTING_PAGE_INDEX

        return try {


            val response = pixabayApi.searchPhotos(key, query, position, params.loadSize)
            val photos = response.hits
            LoadResult.Page(
                data = photos,
                prevKey = if(position == PIXABAY_STARTING_PAGE_INDEX) null else position -1,
                nextKey = if (photos.isEmpty()) null else position +1
            )
        } catch (exception: IOException){
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)


        }
    }
}