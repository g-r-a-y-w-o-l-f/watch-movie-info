package com.org.watchmovie.ui

/**
 * Created by Serhii Polishchuk on 25.09.24
 */
sealed class Screen (val rout: String){
    object Main : Screen("main")
    object MovieList : Screen("movieList")
    object MovieInfo : Screen("movieInfo")
}