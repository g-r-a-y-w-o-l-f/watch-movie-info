package com.org.watchmovie

import android.os.Bundle
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.org.watchmovie.domain.repository.IRepositoryMovieList
import com.org.watchmovie.ui.UIState
import com.org.watchmovie.ui.ViewModelMovie
import com.org.watchmovie.ui.compose.MovieApp
import com.org.watchmovie.ui.theme.WatchMovieTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            WatchMovieTheme {
                val viewModelMovie: ViewModelMovie = hiltViewModel<ViewModelMovie>()
                MovieApp(Modifier.fillMaxSize(), viewModelMovie)

                onBackPressedDispatcher.addCallback(this){
                    when(viewModelMovie.stateUiScreen){
                        is UIState.Details -> viewModelMovie.moveToList()
                        else -> Unit
                    }
                }
            }
        }
    }
}

