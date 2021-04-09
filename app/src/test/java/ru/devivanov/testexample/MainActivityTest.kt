package ru.devivanov.testexample

import android.widget.TextView
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = 21)
class MainActivityTest {
    @Test
    fun `textView should contain HelloWorld text`() {
        //Создаем активити для теста
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        //Ищем текстовое поле по id
        val textView = activity.findViewById<TextView>(R.id.text)
        //Получаем текст из ресурсов
        val resourceText = activity.getText(R.string.hello_world)
        //Проверяем соответсвует ли текст из ресурсов тексту, который сейчас в TextView
        Assert.assertThat(resourceText, CoreMatchers.`is`(textView.text))
    }
}