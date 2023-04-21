package com.wenubey.countriesapp.presentation

sealed class CountryListingEvent {
    object getCountries: CountryListingEvent()

    data class getCountriesInContinent(val query: String): CountryListingEvent()

    data class getCountriesRandomGivenNumber(val number: Int): CountryListingEvent()
}