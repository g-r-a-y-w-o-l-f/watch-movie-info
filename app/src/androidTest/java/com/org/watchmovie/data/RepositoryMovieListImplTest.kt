package com.org.watchmovie.data

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.org.watchmovie.data.local.dao.listInfo.MoviesDataBase
import com.org.watchmovie.data.local.dao.listInfo.MoviesDataBase_Impl
import com.org.watchmovie.data.local.dao.movieInfo.DetailsDataBase
import com.org.watchmovie.data.local.dao.movieInfo.DetailsDataBase_Impl
import com.org.watchmovie.data.network.IMovieService
import com.org.watchmovie.data.network.models.RespMovieDTO
import com.org.watchmovie.data.network.models.ResponseMovieListDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

/**
 * Created by Serhii Polishchuk on 01.10.24
 */
class RepositoryMovieListImplTest : Application() {

    private lateinit var repository: RepositoryMovieListImpl
    private lateinit var testApis: IMovieService
    private lateinit var dbList: MoviesDataBase
    private lateinit var dbDetail: DetailsDataBase
    private lateinit var mockWebServer: MockWebServer
    private lateinit var testList: MoviesDataBase
    private lateinit var testDetail: DetailsDataBase

    private val testScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
//        testApis = RetrofitHelper.testApiInstance(mockWebServer.url("/").toString())
        testList = MoviesDataBase_Impl()
        testDetail = DetailsDataBase_Impl()
        repository = RepositoryMovieListImpl(testApis, dbList, dbDetail)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getMovieList() {
        val mockListMovie = ResponseMovieListDTO(page = 1,
            movies = listOf(),
            totalPages = 234,
            totalResults = 254698)
        val expectedResponse = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(Gson().toJson(mockListMovie))

        mockWebServer.enqueue(expectedResponse)

        testScope.launch {
            val actualResponse = repository.getMovieList(true, "popular", 1)
//            assertThat(actualResponse.httpCode).isEqualTo(HttpURLConnection.HTTP_OK)
//            assertThat(actualResponse.errorMessage).isNull()
//            assertThat(actualResponse.body).isEqualTo(mockListMovie)
        }
    }

    @Test
    fun getDetail() {
    }
}