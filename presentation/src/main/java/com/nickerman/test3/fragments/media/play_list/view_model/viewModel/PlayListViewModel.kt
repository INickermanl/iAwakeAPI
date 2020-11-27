package com.nickerman.test3.fragments.media.play_list.view_model.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.dto.media_test.Program

class PlayListViewModel : ViewModel() {
    var playList = MutableLiveData<Program>()
    private var counter = MutableLiveData(0)
    val counterData: LiveData<Int>
        get() = counter

    fun updateCounter() {
        counter.value = counter.value?.plus(1)
    }
}