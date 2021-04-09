package ru.devivanov.testexample

import android.content.Context


class StringRecourseChooser(private val context: Context, private val favoritesView: FavoritesView) {
    fun returnEnamOnString(id: Int): StringEnams {
        val string = context.getString(id).toLowerCase()
        return when (string) {
            "home" -> StringEnams.HOME
            "favorites" -> {
                favoritesView.runFavoritesScreen()
                StringEnams.FAVORITES
            }
            "settings" -> StringEnams.SETTINGS
            else -> StringEnams.UNKNOWN
        }
    }
}

enum class StringEnams {
   HOME,
   FAVORITES,
   SETTINGS,
   UNKNOWN
}
