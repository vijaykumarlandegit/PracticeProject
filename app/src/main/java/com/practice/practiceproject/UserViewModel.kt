package com.practice.practiceproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

   // private val _count = MutableLiveData<Int>()
    private val _count = MutableLiveData(0)
    val count : LiveData<Int> get() = _count

    fun increaseCounter() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                _count.value = _count.value?.plus(1)
            }
        }
    }
}