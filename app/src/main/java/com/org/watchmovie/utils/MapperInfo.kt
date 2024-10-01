package com.org.watchmovie.utils

import com.google.gson.Gson
import com.org.watchmovie.data.local.dao.movieInfo.BelongsToCollectionEntity
import com.org.watchmovie.data.local.dao.movieInfo.MovieInfoEntity
import com.org.watchmovie.data.network.models.RespBelongsToCollectionDTO
import com.org.watchmovie.data.network.models.RespGenreDTO
import com.org.watchmovie.data.network.models.RespProductionCompanyDTO
import com.org.watchmovie.data.network.models.RespProductionCountryDTO
import com.org.watchmovie.data.network.models.RespSpokenLanguageDTO
import com.org.watchmovie.data.network.models.ResponseMovieInfoDTO
import com.org.watchmovie.domain.model.Belongs
import com.org.watchmovie.domain.model.Genre
import com.org.watchmovie.domain.model.MovieDetail
import com.org.watchmovie.domain.model.ProductionCompany
import com.org.watchmovie.domain.model.ProductionCountry
import com.org.watchmovie.domain.model.SpokenLanguage
import java.lang.StringBuilder

/**
 * Created by Serhii Polishchuk on 26.09.24
 */

fun ResponseMovieInfoDTO.mapToMovieInfoDAO() : MovieInfoEntity {
    return MovieInfoEntity(
        adult = adult,
        backdropPath = backdrop_path,
        belongsToCollection = belongs_to_collection?.mapToBelongsDAO() ?: BelongsToCollectionEntity(),
        budget = budget,
        genres = try { updateGenres(genres) } catch (_ : Exception) {""},
        homepage = homepage,
        id = id,
        imdbID = imdb_id,
        originCountry =  try{ origin_country.joinToString("_, _") ?: " , " } catch (_: Exception) { "_, _"} ,
        originalLanguage = original_language,
        originalTitle = original_title,
        overview = overview,
        popularity = popularity,
        posterPath = poster_path,
        productionCompanies = try{ updateCompany(production_companies) } catch (_: Exception) {""},
        productionCountries = try{ updateCountry(production_countries) } catch (_: Exception) {""},
        releaseDate = release_date,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = try{ updateSpokenLanguage(spoken_languages) } catch (_: Exception) {""},
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = vote_average,
        voteCount = vote_count
    )
}

fun RespBelongsToCollectionDTO.mapToBelongsDAO(): BelongsToCollectionEntity{
    return BelongsToCollectionEntity(
        id = id,
        backdropPath = backdrop_path,
        name = name,
        posterPath = poster_path
    )
}

private fun updateGenres(list: List<RespGenreDTO>) : String {
    val prepare = StringBuilder()
    list.map {
        if(prepare.length != 0)
            prepare.append(",")

        prepare.append(it.toJson())
    }
    return prepare.toString()
}

private fun updateSpokenLanguage(list: List<RespSpokenLanguageDTO>) : String {
    val prepare = StringBuilder()
    list.map {
        if(prepare.length != 0)
            prepare.append(",")

        prepare.append(it.toJson())
    }
    return prepare.toString()
}

private fun updateCompany(list: List<RespProductionCompanyDTO>) : String {
    val prepare = StringBuilder()
    list.map {
        if(prepare.length != 0)
            prepare.append(",")

        prepare.append(it.toJson())
    }
    return prepare.toString()
}

private fun updateCountry(list: List<RespProductionCountryDTO>) : String {
    val prepare = StringBuilder()
    list.map {
        if(prepare.length != 0)
            prepare.append(",")

        prepare.append(it.toJson())
    }
    return prepare.toString()
}

fun MovieInfoEntity.mapToDetailFromDAO(): MovieDetail {
    return MovieDetail(
        adult = adult,
        backdropPath = backdropPath,
        belongsToCollection = Belongs(
            id = belongsToCollection.id,
            name = belongsToCollection.name,
            posterPath = belongsToCollection.posterPath,
            backdropPath = belongsToCollection.backdropPath),
        budget = budget,
        genres = convertGenreWithString(genres), //  List<Genre>,
        homepage = homepage,
        id = id,
        imdbID = imdbID,
        originCountry = try {originCountry.split(",").map { it } } catch (_: Exception) { listOf() }, // : List<String>,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = convertCompanyWithString(productionCompanies), // : List<ProductionCompany>,
        productionCountries = convertCountryWithString(productionCountries), // : List<ProductionCountry>,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = convertSpockWithString(spokenLanguages), // : List<SpokenLanguage>,
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

private fun convertGenreWithString(genres: String): List<Genre> {
    return try {
        genres.split(",").map {
            Gson().fromJson(it, Genre::class.java)
        }
    } catch (_: Exception) {
        listOf()
    }
}

private fun convertCompanyWithString(company: String): List<ProductionCompany> {
    return try {
        company.split(",").map {
            Gson().fromJson(company, ProductionCompany::class.java)
        }
    } catch (_: Exception) {
        listOf()
    }
}

private fun convertCountryWithString(country: String): List<ProductionCountry> {
    return try {
        country.split(",").map {
            Gson().fromJson(country, ProductionCountry::class.java)
        }
    } catch (_: Exception) {
        listOf()
    }
}

private fun convertSpockWithString(spoken: String): List<SpokenLanguage> {
    return try {
        spoken.split(",").map {
            Gson().fromJson(spoken, SpokenLanguage::class.java)
        }
    } catch (_: Exception) {
        listOf()
    }
}



