package ru.devivanov.testexample

import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.coroutines.CoroutineContext

@RunWith(JUnit4::class)
class CoroutinesTest {
    private val customMainThread = newSingleThreadContext("MyUIThread")

    @Before
    fun setUp() {
        Dispatchers.setMain(customMainThread)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        customMainThread.close()
    }

    @Test
    fun testSomeUI() {
        runBlocking {
            launch(Dispatchers.Main) {
                println(Thread.currentThread().name)
            }
        }
    }

    @Test
    fun testDelay() {
        runBlockingTest {
            pauseDispatcher {
                delayFunc(this)

            }
        }
    }

    suspend fun delayFunc(coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            println("start")
            delay(10_000)
            println("end")
        }
    }
}