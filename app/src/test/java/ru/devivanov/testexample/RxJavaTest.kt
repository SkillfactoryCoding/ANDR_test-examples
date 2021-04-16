package ru.devivanov.testexample

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.*
import java.util.concurrent.TimeUnit

@RunWith(JUnit4::class)
class RxJavaTest {
    @Test
    fun traditionalRxJavaTest() {
        val letters = listOf("a", "b", "c")
        val result = mutableListOf<String>()

        val observableNumbers = Observable.range(1, 3)

        val observableLetters = Observable.fromIterable(letters)
            .zipWith(
                observableNumbers,
                { string, index ->
                    string + index
                }
            )
            .observeOn(Schedulers.computation())
            .subscribe {
                result.add(it)
            }

        //Проверяем, что не null
        Assert.assertThat(result, CoreMatchers.notNullValue())
        //Проверяем, что список нужного размера
        Assert.assertThat(result.size, CoreMatchers.`is`(3))
        //Проверяем, что список содержит определенные элементы
        Assert.assertThat(result, CoreMatchers.hasItems("a1", "b2", "c3"))
    }

    @Test
    fun testSubscriberRxJavaTest() {
        val letters = listOf("a", "b", "c")

        val observableNumbers = Observable.range(1, 3)
        val observableLetters = Observable.fromIterable(letters)
            .zipWith(
                observableNumbers,
                { string, index ->
                    string + index
                }
            )

        val testSubscriber = observableLetters.test()

        //Проверяем, что поток завершился
        testSubscriber.assertComplete()
        //Проверяем, что не было ошибок
        testSubscriber.assertNoErrors()
        //Проверяем количесвто сгененрированных элементов
        testSubscriber.assertValueCount(3)
        //Проверяем, что второе значение было "b2"
        testSubscriber.assertValueAt(1, "b2")
    }

    @Test
    fun timeBasedTest() {
        val letters = listOf("a", "b", "c")
        val testScheduler = TestScheduler()

        val observableNumbers = Observable.interval(1, TimeUnit.SECONDS, testScheduler)
        val observableLetters = Observable.fromIterable(letters)
            .zipWith(
                observableNumbers,
                { string, index ->
                    println("onNext $index")
                    string + index
                }
            )
            .subscribeOn(testScheduler)
        println("before subcribition")

        val subscriber = observableLetters.test()
        testScheduler.advanceTimeBy(3, TimeUnit.SECONDS)

        subscriber.assertValueCount(3)
    }
}