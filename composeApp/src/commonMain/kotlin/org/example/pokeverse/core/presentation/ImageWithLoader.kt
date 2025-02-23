package org.example.pokeverse.core.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ImageWithLoader(
    model: Any?,
    fallbackResource: DrawableResource,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    colorFilter: ColorFilter? = null,
    contentScale: ContentScale = ContentScale.Crop,
    loadingUi: @Composable (() -> Unit)? = { CircularProgressIndicator() },
) {
    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }

    val painter = rememberAsyncImagePainter(
        model = model,
        onError = {
            it.result.throwable.printStackTrace()
            imageLoadResult = Result.failure(it.result.throwable)
        },
        onSuccess = {
            imageLoadResult =
                if (it.painter.intrinsicSize.width > 1 && it.painter.intrinsicSize.height > 1) {
                    Result.success(it.painter)
                } else {
                    Result.failure(Throwable("Invalid image size"))
                }
        }
    )
    when (val result = imageLoadResult) {
        null -> {
            Box(modifier, contentAlignment = Alignment.Center) {
                if (loadingUi != null) {
                    loadingUi()
                }
            }
        }

        else -> {
            Image(
                painter = if (result.isSuccess) painter else painterResource(fallbackResource),
                contentDescription = contentDescription,
                modifier = modifier,
                contentScale = if (result.isSuccess) {
                    contentScale
                } else {
                    ContentScale.Fit
                },
                colorFilter = colorFilter
            )
        }
    }
}