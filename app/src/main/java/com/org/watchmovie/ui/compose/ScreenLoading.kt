package com.org.watchmovie.ui.compose

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

/**
 * Created by Serhii Polishchuk on 26.09.24
 */

@Composable
fun ScreenLoading(modifier: Modifier){
    Box (modifier = modifier.fillMaxSize()){

        val progressValue = 0.75f
        val infiniteTransition = rememberInfiniteTransition()

        val progressAnimationValue by infiniteTransition.animateFloat(
            initialValue = 0.0f,
            targetValue = progressValue,animationSpec = infiniteRepeatable(animation = tween(900)),
            label = ""
        )

        CircularProgressIndicator(modifier = Modifier
            .size(200.dp)
            .align(Alignment.Center),
            strokeWidth = 10.dp,
            trackColor = Color.Cyan,
            strokeCap = StrokeCap.Round,
            progress = { progressAnimationValue }
        )
    }
}

