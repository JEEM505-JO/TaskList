package com.devjeem.tasklist.ui.imageAsync

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListImageVM @Inject constructor() : ViewModel() {
    private val _state: MutableStateFlow<StateListImage> = MutableStateFlow(StateListImage())
    val state: StateFlow<StateListImage> get() = _state

    init {
        randomImage()
    }

    private fun randomImage() {
        viewModelScope.launch {
            val urls = listOf(
                "https://goobar.b-cdn.net/wp-content/uploads/2021/03/android_category_thumbnail.png",
                "https://www.simplilearn.com/ice9/free_resources_article_thumb/Android_Developer_Job_Description.jpg",
                "https://www.seekpng.com/png/detail/22-228402_android-developer-sticker-android-developer-laptop-android-dev.png",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmF36HLVXLJYkEL_PIu7CIp2PmWR-o5wBKOg&s",
                "https://sesitdigital.com/wp-content/uploads/2020/11/Imagen1.jpg",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRtDteuaOHPr7vwYWShcXXJIveGcMBBlKAAQQ&s",
                "https://s7g10.scene7.com/is/image/ktm/supermoto-action-21-01:Small?wid=1050&hei=1050&dpr=off"
            )
            val ramdon = urls.random()
            _state.update {
                it.copy(listUrl = urls)
            }
        }
    }
}