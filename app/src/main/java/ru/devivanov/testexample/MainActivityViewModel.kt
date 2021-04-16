package ru.devivanov.testexample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val liveData = MutableLiveData<String>()

    fun postToLiveData(string: String) {
        liveData.postValue(string)
    }
}