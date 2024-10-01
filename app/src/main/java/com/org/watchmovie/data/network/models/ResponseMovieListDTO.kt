package com.org.watchmovie.data.network.models

import com.google.gson.annotations.SerializedName

data class ResponseMovieListDTO(
    val page: Int,
    @SerializedName("results")
    val movies: List<RespMovieDTO>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)