package com.org.watchmovie.domain.repository

import com.org.watchmovie.domain.model.Movie
import com.org.watchmovie.domain.model.MovieDetail
import com.org.watchmovie.ui.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Serhii Polishchuk on 25.09.24
 */
interface IRepositoryMovieList {

    suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>>

    suspend fun getDetail(movieID: Int): Flow<Resource<MovieDetail>>
}