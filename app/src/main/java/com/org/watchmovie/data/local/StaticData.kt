package com.org.watchmovie.data.local

/**
 * Created by Serhii Polishchuk on 25.09.24
 */
object StaticData {
    // API-KEY for "TheMovieDB"
    const val API_KEY = "830df737e0eddb7c5dd34cfd203cf585"

    //Bearer token for "TheMovieDb"
    const val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI4MzBkZjczN2UwZWRkYjdjNWRkMzRjZmQyMDNjZjU4NSIsIm5iZiI6MTcyNzI0OTk0OS40OTQzNDEsInN1YiI6IjY2ZjNiYzU0MGVjYTE3ZGExYjBkZDIyMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.zMNOIz_3OJVFY14cGLvlOXKlFsoEGDevGKWLAcNpK4E"

    //Requests endpoint configurations
    private const val VERSION = 3
    const val BASE_URL = "https://api.themoviedb.org/$VERSION/"
    const val BASE_IMAGE_URL_ORIGINAL = "https://image.tmdb.org/t/p/original"
    const val BASE_IMAGE_URL_W500 = "https://image.tmdb.org/t/p/w500"
}
