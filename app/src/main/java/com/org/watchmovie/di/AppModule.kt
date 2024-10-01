package com.org.watchmovie.di

import android.app.Application
import androidx.room.Room
import com.org.watchmovie.data.local.StaticData
import com.org.watchmovie.data.local.dao.listInfo.MoviesDataBase
import com.org.watchmovie.data.local.dao.movieInfo.DetailsDataBase
import com.org.watchmovie.data.network.IMovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Serhii Polishchuk on 26.09.24
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesMovieApi() : IMovieService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(StaticData.BASE_URL)
            .client(client)
            .build()
            .create(IMovieService::class.java)
    }

    @Provides
    @Singleton
    fun providesMovieDatabase(app: Application): MoviesDataBase {
        return Room.databaseBuilder(
            app,
            MoviesDataBase::class.java,
            "moviedb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesDetailDatabase(app: Application): DetailsDataBase {
        return Room.databaseBuilder(
            app,
            DetailsDataBase::class.java,
            "detaildb.db"
        ).build()
    }

}