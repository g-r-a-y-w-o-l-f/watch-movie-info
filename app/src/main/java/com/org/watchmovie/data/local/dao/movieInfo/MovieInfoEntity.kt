package com.org.watchmovie.data.local.dao.movieInfo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieInfoEntity(
    val adult: Boolean,
    val backdropPath: String,
    @Embedded("belong")
    val belongsToCollection: BelongsToCollectionEntity,
    val budget: Int,
    val genres: String,
    val homepage: String,
    @PrimaryKey
    val id: Int,
    val imdbID: String,
    val originCountry: String,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: String,
    val productionCountries: String,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: String,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class BelongsToCollectionEntity(
    val backdropPath: String = "",
    @PrimaryKey
    val id: Int = -1,
    val name: String = "",
    val posterPath: String = ""
)
