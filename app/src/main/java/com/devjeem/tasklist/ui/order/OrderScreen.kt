package com.devjeem.tasklist.ui.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devjeem.tasklist.R

@Composable
fun OrderScreen(){
    Column(modifier = Modifier.fillMaxWidth().padding(start = 20.dp, top = 10.dp, end = 20.dp)){
        Text(
            text = stringResource(id = R.string.welcome),
            fontSize = 30.sp,
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            text = stringResource(id = R.string.dev),
            fontSize = 20.sp,
        )
    }
}