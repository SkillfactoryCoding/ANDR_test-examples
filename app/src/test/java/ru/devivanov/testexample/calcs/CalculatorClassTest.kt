package ru.devivanov.testexample.calcs

import org.hamcrest.CoreMatchers
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class CalculatorClassTest {
    lateinit var calculatorClass: CalculatorClass

    @Before
    fun setUp() {
        calculatorClass = CalculatorClass()
    }

    @Test
    fun `should sum two numbers`() {
        val result = calculatorClass.sum(2, 2)
        assertThat(4, CoreMatchers.equalTo(result))
    }
}