package com.org.watchmovie.data.local.dao.listInfo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.org.watchmovie.data.local.dao.movieInfo.IMovieInfoDAO

/**
 * Created by Serhii Polishchuk on 25.09.24
 */

@Database(
    entities = [MovieEntity::class],
    version = 1
)
abstract class MoviesDataBase: RoomDatabase() {
    abstract val movieDAO: IMovieDAO
}
