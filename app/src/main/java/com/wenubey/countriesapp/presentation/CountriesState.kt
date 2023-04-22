package com.wenubey.countriesapp.presentation

import com.wenubey.countriesapp.domain.DetailedCountry
import com.wenubey.countriesapp.domain.SimpleCountry

data class CountriesState(
    val countries: List<SimpleCountry> = emptyList(),
    val isLoading: Boolean =false,
    val selectedCountry: DetailedCountry? = null,
    val searchQuery: String = "",
    val sliderValue: Float = 0f
)
