package com.org.watchmovie.ui.compose

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.org.watchmovie.R
import com.org.watchmovie.data.local.StaticData
import com.org.watchmovie.domain.model.Movie
import com.org.watchmovie.ui.theme.Typography
import kotlinx.coroutines.flow.asFlow

/**
 * Created by Serhii Polishchuk on 26.09.24
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowMovieGridList(movies: List<Movie>, modifier: Modifier, actionSelect: (Movie) -> Unit){
    Scaffold(modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text (text = stringResource(R.string.app_name)) }) } ){
        Surface(modifier = Modifier.fillMaxSize().padding(it),
            color = MaterialTheme.colorScheme.background) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(150.dp),
                contentPadding = PaddingValues(4.dp)
            ) {
                itemsIndexed(movies) { _, movie ->
                    ShowItemMovie(movie, modifier, actionSelect)
                }
            }
        }
    }
}

@Composable
fun ShowItemMovie(movie: Movie, modifier: Modifier, actionSelect: (Movie) -> Unit ) {
    Card(modifier = modifier
        .padding(4.dp)
        .height(360.dp)
        .fillMaxWidth(),
        onClick = { actionSelect(movie) } ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(StaticData.BASE_IMAGE_URL_W500 + movie.posterPath)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.baseline_info_movies_24),
                    contentDescription = "Loading image...",
                    error = painterResource(R.drawable.ic_error),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(4.dp))
            Text(
                modifier = Modifier.background(Color.Transparent).padding(horizontal = 4.dp),
                text = movie.title ?: "",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = Typography.titleMedium,
            )
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(4.dp))
            movie.overview?.let { over ->
                Text(
                    modifier = Modifier.background(Color.Transparent).padding(horizontal = 4.dp),
                    text = over.ifBlank { stringResource(R.string.no_overview_name) } ,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.bodyMedium,
                )
            }
        }
    }
}