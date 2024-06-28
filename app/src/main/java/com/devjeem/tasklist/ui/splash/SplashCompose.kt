package com.aes.medidoraes.ui.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devjeem.tasklist.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(state: SplashUIState) {

    SplashScreenContent(state = state)

    LaunchedEffect(key1 = state.success, block = {
        if (state.success){
            state.onListTaskNav()
        }
    })
}

@Composable
fun SplashScreenContent(state : SplashUIState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.task_logo),
            contentDescription = "Logo splash",
            modifier = Modifier.size(150.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp, end = 35.dp)
        ) {
            var enabled by remember { mutableStateOf(true) }
            var progress by remember { mutableFloatStateOf(0.0f) }
            val animatedProgress by animateFloatAsState(
                targetValue = progress, label = "",
            )

            LaunchedEffect(enabled) {
                while ((progress <= 1) && enabled) {
                    progress += 0.005f
                    delay(5)
                }
                if (!enabled){
                    state.onSuccess()
                }
            }

            if (progress >= 1f) {
                enabled = false
            }

            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.LightGray),
                color = colorResource(id = R.color.yellow),
                progress = { animatedProgress }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 200.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            val context = LocalContext.current
            val version = context.packageManager.getPackageInfo(
                context.packageName,
                0
            ).versionName
            Text(
                text = version, fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}