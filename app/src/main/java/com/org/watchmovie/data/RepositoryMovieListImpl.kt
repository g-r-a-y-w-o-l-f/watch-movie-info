package com.org.watchmovie.data

import android.util.Log
import com.org.watchmovie.data.local.dao.listInfo.MoviesDataBase
import com.org.watchmovie.data.local.dao.movieInfo.DetailsDataBase
import com.org.watchmovie.data.network.IMovieService
import com.org.watchmovie.domain.model.Movie
import com.org.watchmovie.domain.model.MovieDetail
import com.org.watchmovie.domain.repository.IRepositoryMovieList
import com.org.watchmovie.ui.Resource
import com.org.watchmovie.utils.mapToDetailFromDAO
import com.org.watchmovie.utils.mapToMovie
import com.org.watchmovie.utils.mapToMovieDAO
import com.org.watchmovie.utils.mapToMovieInfoDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Serhii Polishchuk on 25.09.24
 */

class RepositoryMovieListImpl @Inject constructor(
    private val movieAPI: IMovieService,
    movieDB: MoviesDataBase,
    detailsDB: DetailsDataBase
): IRepositoryMovieList {

    private val movieDAO = movieDB.movieDAO
    private val detailDAO = detailsDB.detailsDAO

    override suspend fun getMovieList(
        forceFetchFromRemote: Boolean,
        category: String,
        page: Int
    ): Flow<Resource<List<Movie>>> {
        return flow {
            emit(Resource.Loading(true))

            val localResult = movieDAO.getMovieListByCategory(category)

            val checkResult = localResult.isNotEmpty() && !forceFetchFromRemote

            if(checkResult){
                emit(Resource.Success(data = localResult.map { movieEntity ->
                    movieEntity.mapToMovie(category)
                }))

                emit(Resource.Loading(false))
                return@flow
            }

            val movieNetwork = try {
                movieAPI.getListMovie(category, page)
            } catch (io: IOException ) {
                Log.e("IMPL ERROR", "${io.stackTrace}")
                emit(Resource.Failure(message = "Error loading movies"))
                return@flow
            } catch (http: HttpException) {
                Log.e("IMPL ERROR", "${http.stackTrace}")
                emit(Resource.Failure(message = "Error network connection"))
                return@flow
            }

            val movieForEntity = movieNetwork.movies.let {
                it.map { forEntity ->
                    forEntity.mapToMovieDAO(category)
                }
            }

            movieDAO.insertMovieList(movieForEntity)

            emit(Resource.Success(
                movieForEntity.map {
                    it.mapToMovie(category) }
            ))
            emit(Resource.Loading(false))

        }
    }

    override suspend fun getDetail(movieID: Int): Flow<Resource<MovieDetail>> {
        return flow {

            emit(Resource.Loading(true))

            val movieEntity = detailDAO.getDetailByID(movieID)

            if (movieEntity != null) {
                emit(Resource.Success(movieEntity.mapToDetailFromDAO()))
                emit(Resource.Loading(false))
                return@flow
            }

            val detailMovieNetwork = try {
                movieAPI.getMovieInfo(movieID)
            } catch (io: IOException ) {
                Log.e("IMPL ERROR", "${io.stackTrace}")
                emit(Resource.Failure(message = "Error loading movies"))
                return@flow
            } catch (http: HttpException) {
                Log.e("IMPL ERROR", "${http.stackTrace}")
                emit(Resource.Failure(message = "Error network connection"))
                return@flow
            }

            val detailsForEntity = detailMovieNetwork.mapToMovieInfoDAO()
            detailDAO.insertDetail(detailsForEntity)

            emit(Resource.Success(detailsForEntity.mapToDetailFromDAO()))
            emit(Resource.Loading(false))

        }
    }

}