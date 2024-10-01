package com.org.watchmovie.utils

import com.org.watchmovie.data.local.dao.listInfo.MovieEntity
import com.org.watchmovie.data.network.models.RespMovieDTO
import com.org.watchmovie.domain.model.Movie

/**
 * Created by Serhii Polishchuk on 26.09.24
 */

fun RespMovieDTO.mapToMovieDAO(category: String): MovieEntity {
    return MovieEntity(
        id = id ?: -1,
        category = category,
        adult = adult ?: false,
        backdropPath = backdrop_path ?: "",
        genreIds = try { genre_ids?.joinToString(",") ?: "-1, -2" } catch (_: Exception){ "-1, -2" },
        originalLanguage = original_language ?: "",
        originalTitle = original_title ?: "",
        overview = overview ?: "",
        popularity = popularity ?: .0,
        posterPath = poster_path ?: "",
        releaseDate = release_date ?: "",
        title = title ?: "",
        video = video ?: false,
        voteAverage = vote_average ?: .0,
        voteCount = vote_count ?: 0
    )
}

fun MovieEntity.mapToMovie(category: String): Movie {
    return Movie(
        category = category,
        adult  = adult,
        backdropPath = backdropPath,
        genreIds = try { genreIds.split(",").map { it.toInt() } }
        catch (_ : Exception) { listOf( -1, -2) },
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        id = id
    )
}