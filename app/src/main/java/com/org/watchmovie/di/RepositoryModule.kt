package com.org.watchmovie.di

import com.org.watchmovie.data.RepositoryMovieListImpl
import com.org.watchmovie.domain.repository.IRepositoryMovieList
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Serhii Polishchuk on 27.09.24
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepositoryListMovie(
        mediaRepositoryImpl: RepositoryMovieListImpl
    ): IRepositoryMovieList

//    @Binds
//    @Singleton
//    abstract fun bindRepositoryMovieDetails(
//        RepositoryMovieDetailsImpl: RepositoryMovieDetailsImpl
//    ): IRepositoryMovieDetails

}