package com.org.watchmovie.domain.model

data class MovieDetail(
    val adult: Boolean,
    val backdropPath: String,
    val belongsToCollection: Belongs,
    val budget: Int,
    val genres: List<Genre>,
    val homepage: String,
    val id: Int,
    val imdbID: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val productionCompanies: List<ProductionCompany>,
    val productionCountries: List<ProductionCountry>,
    val releaseDate: String,
    val revenue: Int,
    val runtime: Int,
    val spokenLanguages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

data class Belongs(
    val backdropPath: String,
    val id: Int,
    val name: String,
    val posterPath: String
)

data class Genre(
    val id: Int,
    val name: String
)

data class ProductionCountry(
    val iso_3166_1: String,
    val name: String
)

data class ProductionCompany(
    val id: Int,
    val logoPath: String,
    val name: String,
    val originCountry: String
)

data class SpokenLanguage(
    val englishName: String,
    val iso_639_1: String,
    val name: String
)