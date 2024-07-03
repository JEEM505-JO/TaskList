package com.devjeem.tasklist.ui.order

import android.widget.ProgressBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign.Companion.Center
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.ImageRequest
import com.devjeem.tasklist.R
import com.devjeem.tasklist.network.model.orders.GuestOrder

@Composable
fun OrderScreen(orderState: OrderState) {
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
        AnimatedVisibility(visible = orderState.loading) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),
                    color = colorResource(id = R.color.iconColor),
                    strokeWidth = 3.dp
                )
            }

        }
        Text(
            text = orderState.smsProgress,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp)
        )
        AnimatedVisibility(visible = orderState.success) {
            LazyColumn {
                items(orderState.guest_orders) {
                    ItemOrder(it, orderState)
                }
            }
        }
    }
}

@Composable
fun ItemOrder(guestOrder: GuestOrder, state: OrderState) {
    Card(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .clickable {
                state.onOrderDetailNav(guestOrder.id.toString())
            }
            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp),
        shape = RoundedCornerShape(15.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Fecha de creacion", modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textAlign = Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = guestOrder.createdAt, modifier = Modifier
                    .fillMaxWidth(),
                textAlign = Center,
                fontWeight = FontWeight.Light
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
            ) {
                Text(
                    text = "ID: ",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterStart)
                )

                Text(
                    text = guestOrder.id.toString(), modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 40.dp),
                    fontWeight = FontWeight.Light
                )
                Text(
                    text = "Empleado", modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 70.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = guestOrder.employee.firstName,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    fontWeight = FontWeight.Light
                )
            }
            Text(
                text = "TOTAL", modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                textAlign = Center,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = guestOrder.total.toString(), modifier = Modifier
                    .fillMaxWidth(),
                textAlign = Center,
                fontWeight = FontWeight.Light
            )
        }
    }
}
