package com.org.watchmovie.data.network

import com.org.watchmovie.data.local.StaticData.API_KEY
import com.org.watchmovie.data.network.models.ResponseMovieInfoDTO
import com.org.watchmovie.data.network.models.ResponseMovieListDTO
import com.org.watchmovie.utils.Category.POPULAR
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Serhii Polishchuk on 25.09.24
 */

interface IMovieService {
    @GET("movie/{category}")
    suspend fun getListMovie(
        @Path("category") category: String = POPULAR,
        @Query("page") page: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "uk-UK"
    ) : ResponseMovieListDTO

    @GET("movie/{movieID}")
    suspend fun getMovieInfo(
        @Path("movieID") movieID: Int,
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = "uk-UK"
    ) : ResponseMovieInfoDTO


}