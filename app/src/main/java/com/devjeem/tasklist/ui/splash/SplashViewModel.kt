package com.aes.medidoraes.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : ViewModel() {
    private val _uiState: MutableStateFlow<SplashUIState> = MutableStateFlow(
        SplashUIState(
            setListTaskNav = ::setListTaskNav,
            onSuccess = ::onSuccess
        )
    )

    val uiState : StateFlow<SplashUIState> = _uiState.asStateFlow()



    private fun onSuccess(){
        viewModelScope.launch {
            _uiState.update {
                it.copy(success = true)
            }
        }
    }





    private fun setListTaskNav(onListTaskNav: () -> Unit) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(onListTaskNav = onListTaskNav)
            }
        }
    }
}