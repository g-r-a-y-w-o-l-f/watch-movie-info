package com.org.watchmovie.data.local.dao.listInfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


/**
 * Created by Serhii Polishchuk on 25.09.24
 */

@Dao
interface IMovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieItem(
        mediaItem: MovieEntity
    )

    @Update
    suspend fun updateMovieItem(movieItem: MovieEntity)

    @Query(
        """
            DELETE FROM movieentity 
            WHERE category = :category
        """
    )
    suspend fun deleteMediaByCategory(category: String)


    @Query("SELECT * FROM movieEntity WHERE id = :id")
    suspend fun getMovieByID(id: Int): MovieEntity

    @Query(
        """
            SELECT * 
            FROM movieEntity 
            WHERE category = :category
        """
    )
    suspend fun getMovieListByCategory(category: String): List<MovieEntity>

}