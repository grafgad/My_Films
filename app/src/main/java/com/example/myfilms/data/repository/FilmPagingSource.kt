package com.example.myfilms.data.repository

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.myfilms.data.ApiService
import com.example.myfilms.data.FilmResponse
import com.example.myfilms.model.Film
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class FilmPagingSource(
    private val apiService: ApiService,
    private val query: String?
) : RxPagingSource<Int, Film>() {

    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Film>> {
        val page = params.key ?: 1
        return if (query != null) {
            apiService.getNowPlayingFilms(page)
                .subscribeOn(Schedulers.io())
                .map { toLoadResult(it, page) }
                .onErrorReturn { LoadResult.Error(it) }
        } else {
            apiService.getNowPlayingFilms(page)
                .subscribeOn(Schedulers.io())
                .map { toLoadResult(it, page) }
                .onErrorReturn { LoadResult.Error(it) }
        }
    }

    private fun toLoadResult(
        film: FilmResponse,
        page: Int
    ): LoadResult<Int, Film> {
        return LoadResult.Page(
            data = film.results,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (film.results.isEmpty()) null else page + 1
        )
    }

}