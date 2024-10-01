package com.org.watchmovie.data.local.dao.movieInfo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.org.watchmovie.data.local.dao.movieInfo.IMovieInfoDAO

/**
 * Created by Serhii Polishchuk on 25.09.24
 */

@Database(
    entities = [MovieInfoEntity::class],
    version = 1
)

abstract class DetailsDataBase: RoomDatabase() {
    abstract val detailsDAO: IMovieInfoDAO
}