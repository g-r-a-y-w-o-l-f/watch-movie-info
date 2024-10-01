package com.org.watchmovie.data.local.dao.listInfo

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Serhii Polishchuk on 25.09.24
 */

@Entity
data class MovieEntity (
    @PrimaryKey
    val id: Int,
    val category: String,

    val adult: Boolean,
    val backdropPath: String,
    val genreIds: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)
