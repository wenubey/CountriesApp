package com.wenubey.countriesapp.domain


// We create for abstraction if we want to change graphql api to rest api
//    we just change the implementation not the change the rest of the project
interface CountryClient {

    suspend fun getCountries(): List<SimpleCountry>

    suspend fun getCountry(code: String): DetailedCountry?

    suspend fun getCountryInContinent(code: String): List<SimpleCountry>

    suspend fun getCountriesRandomGivenNumber(numCountries: Int): List<SimpleCountry>
}