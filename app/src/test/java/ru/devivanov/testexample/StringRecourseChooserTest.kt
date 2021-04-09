package ru.devivanov.testexample

import android.content.Context
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class StringRecourseChooserTest {
    var context = mock<Context>()
    lateinit var favoritesView: FavoritesView
    lateinit var stringRecourseChooser: StringRecourseChooser

    @Before
    fun setUp() {
        context = mock {
            on { getString(R.string.favorites) }.thenReturn("Favorites")
        }
        favoritesView = mock()
        stringRecourseChooser = StringRecourseChooser(context, favoritesView)
    }

    @Test
    fun returnEnamOnString() {
        stringRecourseChooser.returnEnamOnString(R.string.favorites)
        verify(favoritesView).runFavoritesScreen()
    }
}