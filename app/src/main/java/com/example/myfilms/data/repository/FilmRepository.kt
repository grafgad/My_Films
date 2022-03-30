package com.example.myfilms.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.rxjava3.flowable
import com.example.myfilms.data.ApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@ExperimentalCoroutinesApi
class FilmRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getNowPlayingFilms() =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 60,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { FilmPagingSource(apiService, null) }
        ).flowable


}