package com.wenubey.countriesapp.navigation

import com.wenubey.countriesapp.core.Constants.COUNTRIES_SCREEN

sealed class Screen(val route: String) {
    object CountriesScreen: Screen(COUNTRIES_SCREEN)
}
