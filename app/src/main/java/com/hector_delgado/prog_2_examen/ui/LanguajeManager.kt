package com.hector_delgado.prog_2_examen.ui

import android.content.Context
import android.content.res.Configuration
import java.util.*

object LanguageManager {

    fun setLocale(context: Context, languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }
}
