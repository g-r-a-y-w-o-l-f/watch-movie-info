package com.org.watchmovie.domain.model

import androidx.room.PrimaryKey

/**
 * Created by Serhii Polishchuk on 25.09.24
 */
data class Movie(
    val id: Int,
    val category: String,
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?
)
