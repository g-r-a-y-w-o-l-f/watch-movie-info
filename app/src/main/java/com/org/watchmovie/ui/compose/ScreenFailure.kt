package com.org.watchmovie.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.org.watchmovie.R
import com.org.watchmovie.ui.theme.Typography

/**
 * Created by Serhii Polishchuk on 26.09.24
 */

@Composable
fun ScreenFailure(modifier: Modifier, message: String? = null, retryAction: () -> Unit ){

    Column (modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Image(modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Loading content..."
        )
        Text(
            text = message ?: stringResource(R.string.app_name),
            style = Typography.titleLarge.copy(color = Color.Red)
        )
        Button(onClick = retryAction){
            Text( text = "Retry",
                style = Typography.labelMedium.copy(Color.DarkGray))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewFailureScreen(){
    ScreenFailure(Modifier, "Error loading movies") {
    }
}
