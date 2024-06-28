package com.aes.medidoraes.ui.splash

data class SplashUIState(
    val onListTaskNav : () -> Unit = {},
    val setListTaskNav : (() -> Unit) -> Unit = {},
    var success : Boolean = false,
    val onSuccess : () -> Unit = {}
)
