package com.example.myfilms.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    fun getNowPlayingFilms(
        @Query("page") position: Int
    ): Single<FilmResponse>

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val imageUrl = "https://image.tmdb.org/t/p/w500"
    }
}
