package com.org.watchmovie.ui.compose

import android.annotation.SuppressLint
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.org.watchmovie.R
import com.org.watchmovie.ui.MovieState
import com.org.watchmovie.ui.ViewModelMovie

/**
 * Created by Serhii Polishchuk on 26.09.24
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp(modifier: Modifier, viewModelMovie: ViewModelMovie) {

    when(viewModelMovie.movieState){
        is MovieState.List -> {
            ScreenList(modifier = modifier,
                screenState = viewModelMovie.stateUiScreen,
                retryAction = { viewModelMovie.getMovies() },
                selectMovie = { movie ->
                    viewModelMovie.currentMovie = movie
                    viewModelMovie.getDetails()
                    viewModelMovie.movieState = MovieState.Detail
                })
        }
        is MovieState.Detail -> ScreenDetail(modifier = modifier,
            screenState = viewModelMovie.stateUiScreen,
            backAction = {
                viewModelMovie.getMovies()
                viewModelMovie.movieState = MovieState.List
            })
    }
}