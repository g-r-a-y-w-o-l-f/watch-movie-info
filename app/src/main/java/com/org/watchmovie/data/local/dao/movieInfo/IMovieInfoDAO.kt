package com.org.watchmovie.data.local.dao.movieInfo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/**
 * Created by Serhii Polishchuk on 25.09.24
 */

@Dao
interface IMovieInfoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetail(movie: MovieInfoEntity)

    @Update
    suspend fun updateDetail(movie: MovieInfoEntity)

    @Query(
        """
            DELETE FROM movieinfoentity 
            WHERE id = :id
        """
    )
    suspend fun deleteDetailByID(id: Int)


    @Query("SELECT * FROM movieinfoentity WHERE id = :id")
    suspend fun getDetailByID(id: Int): MovieInfoEntity

}