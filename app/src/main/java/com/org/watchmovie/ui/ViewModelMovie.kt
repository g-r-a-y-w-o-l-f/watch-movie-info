package com.org.watchmovie.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.org.watchmovie.domain.model.Movie
import com.org.watchmovie.domain.repository.IRepositoryMovieList
import com.org.watchmovie.utils.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Serhii Polishchuk on 26.09.24
 */
@HiltViewModel
class ViewModelMovie @Inject constructor(private val repository: IRepositoryMovieList ): ViewModel() {

    var stateUiScreen: UIState by mutableStateOf(UIState.Loading(false))
        private set

    var movieState: MovieState by mutableStateOf(MovieState.List)

    var currentMovie: Movie? = null

    init {
        getMovies()
    }


    fun getMovies(){
        viewModelScope.launch {
            stateUiScreen = UIState.Loading(true)
            try {
                repository.getMovieList(false, Category.POPULAR, 1).collect {
                    when(it){
                        is Resource.Success -> {
                            stateUiScreen = UIState.Success(it.data ?: listOf())
                        }
                        is Resource.Loading -> Unit
                        is Resource.Failure -> Unit
                    }
                }
            } catch (ex: Exception) {
                UIState.Failure(ex.message ?: "")
            }
        }
    }

    fun getDetails(){
        currentMovie?.let { movie ->
            viewModelScope.launch {
                stateUiScreen = UIState.Loading(true)
                try {
                    repository.getDetail(movieID = movie.id).collect {
                        when (it) {
                            is Resource.Success -> {
                                stateUiScreen = UIState.Details(data = it.data)
                            }

                            is Resource.Failure -> stateUiScreen = UIState.Failure("No data")
                            is Resource.Loading -> Unit
                        }
                    }
                } catch (ex: HttpException) {
                    stateUiScreen = UIState.Failure(ex.message.toString())
                } catch (ex: IOException) {
                    stateUiScreen = UIState.Failure(ex.message.toString())
                } catch (ex: Exception) {
                    stateUiScreen = UIState.Failure(ex.message.toString())
                }

            }
        }
    }

    fun moveToList(){
        movieState = MovieState.List
        getMovies()
    }

}