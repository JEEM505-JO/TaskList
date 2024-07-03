package com.devjeem.tasklist.ui.orderDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjeem.tasklist.network.model.orderDetail.Order
import com.devjeem.tasklist.repository.OrdersRepository
import com.devjeem.tasklist.ui.util.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderDetailVM @Inject constructor(
    private val repository: OrdersRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _state: MutableStateFlow<OrderDetailState> =
        MutableStateFlow(
            OrderDetailState(
                initData = ::initData,
                setOnNavOrderList = ::setOnNavOrderList
            )
        )

    private fun setOnNavOrderList(function: () -> Unit) {
        viewModelScope.launch {
            _state.update {
                it.copy(onNavOrderList = function)
            }
        }
    }

    val state: StateFlow<OrderDetailState> = _state.asStateFlow()


    private fun initData(id: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true)
            }
            try {
                val apiKey = sharedPreferences.getLogin()
                val request = repository.getOrder(Order(orderId = id), apiKey!!)
                if (request.isSuccessful) {
                    _state.update {
                        it.copy(order = request.body()?.data, loading = false, success = true)
                    }
                } else {
                    _state.update {
                        it.copy(error = false, loading = false)
                    }
                }
            } catch (
                e: Exception
            ) {
                e.printStackTrace()
            }
        }
    }
}