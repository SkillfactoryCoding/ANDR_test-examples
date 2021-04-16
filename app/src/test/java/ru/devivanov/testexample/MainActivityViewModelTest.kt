package ru.devivanov.testexample

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test

class MainActivityViewModelTest {
    @get: Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun postToLiveData() {
        val mainActivityViewModel = MainActivityViewModel()
        mainActivityViewModel.postToLiveData("some string")

        val observer = Observer<String> {
            assertEquals("some string", it)
        }
        try {
            mainActivityViewModel.liveData.observeForever(observer)
            mainActivityViewModel.postToLiveData("some string")
        } finally {
            mainActivityViewModel.liveData.removeObserver(observer)
        }
    }
}