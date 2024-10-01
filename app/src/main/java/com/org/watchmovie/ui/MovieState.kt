package com.org.watchmovie.ui

import com.org.watchmovie.domain.model.Movie

/**
 * Created by Serhii Polishchuk on 26.09.24
 */
interface MovieState {
    object List: MovieState
    object Detail: MovieState
}