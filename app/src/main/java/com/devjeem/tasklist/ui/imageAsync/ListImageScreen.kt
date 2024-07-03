package com.devjeem.tasklist.ui.imageAsync

import android.graphics.drawable.Drawable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.devjeem.tasklist.R
import com.google.accompanist.imageloading.rememberDrawablePainter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun ListImageScreen(stateListImage: StateListImage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 10.dp, end = 20.dp)
    ) {
        Text(
            text = stringResource(id = R.string.welcome),
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.size(10.dp))
        AnimatedVisibility(visible = stateListImage.listUrl.isEmpty()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    color = colorResource(id = R.color.iconColor),
                    strokeWidth = 3.dp
                )
            }

        }
        LazyColumn {
            items(stateListImage.listUrl) {
                ImageCard(imageUrl = it, title = it)
            }
        }

    }
}

@Composable
fun GlideImage2(url: String) {
    // Holding image data with MutableState
    var image by remember { mutableStateOf<Drawable?>(null) }
    // Fetching the Context inside Compose using LocalContext.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // If there is an image, display it
        image?.let {
            Image(
                painter = rememberDrawablePainter(it),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp, 300.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
        }

    }
}

@Composable
fun GlideImage1(
    imageUrl: String,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val context = LocalContext.current

    LaunchedEffect(imageUrl) {
        withContext(Dispatchers.IO) {
            try {
                val bitmap = Glide.with(context)
                    .asBitmap()
                    .load(imageUrl)
                    .submit()
                    .get()
                imageBitmap = bitmap.asImageBitmap()
                isLoading = false
            } catch (e: Exception) {
                isLoading = false
                // Manejo de errores
            }
        }
    }
    Box(modifier = modifier) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            imageBitmap?.let { img ->
                Image(
                    bitmap = img,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = contentScale
                )
            }
        }
    }
}


@Composable
fun ImageCard(imageUrl: String, title: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(5.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            GlideImage1(
                imageUrl = imageUrl,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Text(
                text = title,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}