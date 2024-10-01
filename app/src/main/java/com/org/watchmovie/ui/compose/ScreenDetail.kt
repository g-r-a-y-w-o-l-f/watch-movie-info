package com.org.watchmovie.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.org.watchmovie.R
import com.org.watchmovie.data.local.StaticData
import com.org.watchmovie.ui.UIState
import com.org.watchmovie.ui.theme.Purple40
import com.org.watchmovie.ui.theme.Typography
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

/**
 * Created by Serhii Polishchuk on 26.09.24
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenDetail(modifier: Modifier, screenState: UIState, backAction: () -> Unit ){

    when(screenState){
        is UIState.Failure -> Unit
        is UIState.Loading -> Unit
        is UIState.Details -> {
            val data = screenState.data
                    Scaffold(modifier = modifier.fillMaxSize(),
                        topBar = { TopAppBar(
                            navigationIcon = {
                                Image(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .width(56.dp)
                                        .padding(16.dp)
                                        .clickable(
                                            enabled = true,
                                            onClick = backAction
                                        ),
                                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                                    colorFilter = ColorFilter.tint(Purple40),
                                    contentDescription = "backButton...",)
                                Spacer(
                                    Modifier
                                        .fillMaxHeight()
                                        .width(16.dp)) },
                            title = { Text (text =  data?.title ?: data?.originalTitle ?: "") } ) }
                    ){
                    Surface(modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                        color = MaterialTheme.colorScheme.background) {

                    Column (modifier = modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp),
                    ){
                        AsyncImage( modifier = Modifier.height(250.dp),
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(StaticData.BASE_IMAGE_URL_W500 + data?.backdropPath)
                                .crossfade(true)
                                .build(),
                            placeholder = painterResource(R.drawable.baseline_info_movies_24),
                            contentDescription = "Loading image...",
                            error = painterResource(R.drawable.ic_error),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(8.dp))
                        Text(
                            modifier = Modifier.background(Color.Transparent),
                            text = data?.title ?: "",
                            style = Typography.titleLarge,
                        )
                        data?.tagline?.let { tag ->
                            if(tag.isNotBlank()) {
                                Spacer(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(8.dp)
                                )
                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent)
                                        .align(AbsoluteAlignment.Right),
                                    text = tag,
                                    style = Typography.bodyMedium,
                                )
                            }
                        }
                        data?.overview?.let { overView ->
                            Spacer(
                                Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                            )
                            Text(
                                modifier = Modifier.background(Color.Transparent),
                                text = overView.ifBlank { stringResource(R.string.no_overview_name) },
                                style = Typography.bodyMedium,
                            )
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(8.dp))
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(28.dp),
                            verticalAlignment = Alignment.CenterVertically){
                            Text(
                                modifier = Modifier.background(Color.Transparent),
                                text = stringResource(R.string.status_name),
                                style = Typography.titleMedium,
                            )
                            Spacer(
                                Modifier
                                    .fillMaxHeight()
                                    .width(8.dp))
                            Text(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .align(Alignment.CenterVertically),
                                text = data?.status ?: "",
                                style = Typography.bodyMedium,
                            )
                            Spacer(
                                Modifier
                                    .fillMaxHeight()
                                    .width(8.dp))
                            data?.releaseDate?.let{ publish ->
                                Text(
                                    modifier = Modifier
                                        .background(Color.Transparent)
                                        .align(Alignment.CenterVertically),
                                    text = publish,
                                    style = Typography.bodyMedium,
                                )
                            }
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(8.dp))

                        Row(
                            Modifier
                                .fillMaxWidth()
                                .height(28.dp),
                            verticalAlignment = Alignment.CenterVertically){
                            Text(
                                modifier = Modifier.background(Color.Transparent),
                                text = stringResource(R.string.vote_name),
                                style = Typography.titleMedium,
                            )
                            Spacer(
                                Modifier
                                    .fillMaxHeight()
                                    .width(8.dp))
                            Text(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .align(Alignment.CenterVertically),
                                text = "${data?.voteCount ?: "0"}",
                                style = Typography.bodyMedium,
                            )
                            Text(
                                modifier = Modifier
                                    .background(Color.Transparent)
                                    .align(Alignment.CenterVertically),
                                text = " / ${data?.voteAverage ?: "0.0"}",
                                style = Typography.bodyMedium,
                            )
                        }
                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(8.dp))
                        data?.revenue?.let{revenu ->
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .height(28.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier.background(Color.Transparent),
                                    text = stringResource(R.string.revenu_name),
                                    style = Typography.titleMedium.copy(Color.Black),
                                )
                                Spacer(
                                    Modifier
                                        .fillMaxHeight()
                                        .width(4.dp))
                                Text(
                                    modifier = Modifier.background(Color.Transparent),
                                    text = "$revenu $",
                                    style = Typography.bodyMedium,
                                )

                            }
                            Spacer(
                                Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                            )
                        }
                        data?.runtime?.let{ runtime ->
                            Row(
                                Modifier
                                    .fillMaxWidth()
                                    .height(28.dp), verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    modifier = Modifier.background(Color.Transparent),
                                    text = stringResource(R.string.runtime_name),
                                    style = Typography.titleMedium,
                                )
                                Spacer(
                                    Modifier
                                        .fillMaxHeight()
                                        .width(4.dp))
                                Text(
                                    modifier = Modifier.background(Color.Transparent),
                                    text = "$runtime хв",
                                    style = Typography.bodyMedium,
                                )

                            }
                            Spacer(
                                Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

