package com.devjeem.tasklist.ui.orderDetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.unit.dp
import com.devjeem.tasklist.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailScreen(state: OrderDetailState) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.iconColor),
                    titleContentColor = colorResource(id = R.color.black),
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.orderDetail),
                        color = colorResource(id = R.color.white),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { state.onNavOrderList() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                })
        }) { paddingValues ->

        if (state.success && state.order != null) {
            OrderContent(orderDetailState = state, paddingValues = paddingValues)
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Cargando...",
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    textAlign = Center,
                )
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    color = colorResource(id = R.color.iconColor),
                    strokeWidth = 3.dp
                )
            }
        }
        if (state.error) {
            Text(
                text = "Error al obtener los datos", modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textAlign = Center,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun OrderContent(orderDetailState: OrderDetailState, paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Text(
            text = "Identificador", modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = orderDetailState.order?.id.toString() ?: " ", modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = Center,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Estado",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = orderDetailState.order?.status ?: " ",
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Service Active",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = orderDetailState.order?.service_charge_active.toString(),
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Service amount",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = orderDetailState.order?.service_charge_amount.toString(),
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 25.dp, end = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Service value",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = orderDetailState.order?.service_charge_value.toString(),
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = "Total", modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = orderDetailState.order?.total.toString(), modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = Center,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Meta Information", modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = Center,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = orderDetailState.order?.meta?.apk_version ?: " ", modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = Center,
            fontWeight = FontWeight.Bold
        )

    }
}