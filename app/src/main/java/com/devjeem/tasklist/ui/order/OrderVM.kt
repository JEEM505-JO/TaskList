package com.devjeem.tasklist.ui.order

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjeem.tasklist.repository.OrdersRepository
import com.devjeem.tasklist.ui.util.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderVM @Inject constructor(
    private val ordersRepository: OrdersRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _state: MutableStateFlow<OrderState> =
        MutableStateFlow(OrderState(setOrderDetailNav = ::setOrderNav))
    val state: StateFlow<OrderState> get() = _state

    init {
        getLicense()
    }

    private fun getLicense() {
        viewModelScope.launch {
            _state.update {
                it.copy(loading = true, smsProgress = "Consultando datos, por favor espere...")
            }
            val resp = ordersRepository.activateLicense()
            if (resp.isSuccessful) {
                Log.d("TAG", "getLicense: ${resp.body()?.data?.activation?.api_key}")
                getOrders(resp.body()?.data?.activation?.api_key!!)
                sharedPreferences.saveLogin(resp.body()?.data?.activation?.api_key!!)
            } else {
                Log.d("TAG", "getLicense: ${resp.errorBody()}")
            }
        }
    }

    private fun getOrders(apikey: String) {
        viewModelScope.launch {
            val orders = ordersRepository.getTodayOrders(apikey)
            if (orders.isSuccessful) {
                Log.d("TAG", "getOrders: ${orders.body()?.data?.guestOrders}")
                orders.body()?.data?.guestOrders.let { list ->
                    _state.update {
                        it.copy(
                            loading = false,
                            guest_orders = list!!,
                            apiKey = apikey,
                            smsProgress = "Estas son las ordenes del dia",
                            success = true
                        )
                    }
                }
            } else {
                _state.update {
                    it.copy(
                        loading = false,
                        smsProgress = "No se encontraron ordenes",
                        success = false
                    )
                }
                Log.d("TAG", "getOrders: ${orders.errorBody()}")
            }
        }
    }

    private fun setOrderNav(function: (String) -> Unit) {
        viewModelScope.launch {
            _state.update {
                it.copy(onOrderDetailNav = function)
            }
        }
    }

}