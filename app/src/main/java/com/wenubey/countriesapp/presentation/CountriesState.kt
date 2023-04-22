package com.wenubey.countriesapp.presentation

import com.wenubey.countriesapp.data.CountryDto

data class CountriesState(
    val countries: List<CountryDto> = emptyList(),
    val isLoading: Boolean =false,
    val selectedContinent: String = "",
    val sliderValue: Float = 0f
)
