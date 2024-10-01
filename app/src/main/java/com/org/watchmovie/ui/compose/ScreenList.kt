package com.org.watchmovie.ui.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.org.watchmovie.domain.model.Movie
import com.org.watchmovie.ui.Resource
import com.org.watchmovie.ui.UIState

/**
 * Created by Serhii Polishchuk on 26.09.24
 */
@Composable
fun ScreenList(
    modifier: Modifier,
    screenState: UIState,
    retryAction: () -> Unit,
    selectMovie: (Movie) -> Unit
) {
    when(screenState){
        is UIState.Loading -> ScreenLoading(modifier)
        is UIState.Failure -> ScreenFailure(modifier, screenState.error,  retryAction)
        is UIState.Success -> ShowMovieGridList(screenState.data, modifier, selectMovie)
    }

}