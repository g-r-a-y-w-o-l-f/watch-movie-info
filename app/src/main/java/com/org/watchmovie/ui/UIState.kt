package com.org.watchmovie.ui

import com.org.watchmovie.domain.model.Movie
import com.org.watchmovie.domain.model.MovieDetail

/**
 * Created by Serhii Polishchuk on 26.09.24
 */
interface UIState {
    data class Success(val data: List<Movie>): UIState
    data class Failure(val error: String): UIState
    data class Loading(val state: Boolean): UIState
    data class Details(val data: MovieDetail?): UIState
}