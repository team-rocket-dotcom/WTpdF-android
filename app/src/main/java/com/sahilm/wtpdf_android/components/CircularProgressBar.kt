package com.sahilm.wtpdf_android.components

import android.R
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sahilm.wtpdf_android.ui.theme.backgroundDark
import com.sahilm.wtpdf_android.ui.theme.onPrimaryLight
import com.sahilm.wtpdf_android.ui.theme.primaryDark
import com.sahilm.wtpdf_android.ui.theme.primaryLight
import com.sahilm.wtpdf_android.ui.theme.secondaryDark
import com.sahilm.wtpdf_android.ui.theme.secondaryLight
import com.sahilm.wtpdf_android.ui.theme.tertiaryDark
import com.sahilm.wtpdf_android.ui.theme.tertiaryLight
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun CircularProgressBar(
    progress: Float = 0f,
    startAngle: Float = 270f,
    size: Dp = 96.dp,
    strokeWidth: Dp = 12.dp,
    progressArcColor1: Color = primaryLight,
    progressArcColor2: Color = secondaryDark,
    animationOn: Boolean = false,
    animationDuration: Int = 100,
    repeatOn: Boolean = false
) {
    val currentProgress = remember { mutableFloatStateOf(0f) }
    val animatedProgress by animateFloatAsState(
        targetValue = currentProgress.floatValue,
        animationSpec = if (animationOn) tween(animationDuration) else tween(0),
        label = "Progress Animation"
    )
    val rotation = remember { mutableFloatStateOf(0f) }

    LaunchedEffect(animationOn, progress) {
        if (animationOn) {
            progressFlow(progress).collect { value ->
                currentProgress.floatValue = value
            }
        } else {
            currentProgress.floatValue = progress
        }
    }

    LaunchedEffect(repeatOn) {
        if (repeatOn) {
            while (true) {
                rotation.floatValue = (rotation.floatValue + 1f)
                delay(10)
            }
        }
    }

    Canvas(modifier = Modifier.size(size)) {
        val strokeWidthPx = strokeWidth.toPx()
        val arcSize = size.toPx() - strokeWidthPx
        val gradientBrush = Brush.verticalGradient(
            colors = listOf(progressArcColor1, progressArcColor2, progressArcColor1)
        )

        withTransform({
            rotate(
                degrees = if (repeatOn) startAngle + rotation.floatValue else startAngle,
                pivot = center
            )
        }) {
            drawArc(
                brush = gradientBrush,
                startAngle = 0f,
                sweepAngle = animatedProgress * 360,
                useCenter = false,
                topLeft = Offset(strokeWidthPx / 2, strokeWidthPx / 2),
                size = Size(arcSize, arcSize),
                style = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
            )
        }
    }
}

@Composable
@Preview
fun CircularProgressBarPreview() {
    CircularProgressBar(progress = 0.85f)
}

fun progressFlow(
    targetProgress: Float = 1f,
    step: Float = 0.01f,
    delayTime: Long = 1L
) : Flow<Float> {
    return flow {
        var progress = 0f
        while (progress <= targetProgress) {
            emit(progress)
            progress += step
            delay(delayTime)
        }
    }
}