package com.wenubey.countriesapp.data

import CountryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {

    @GET("name/{countryName}")
    suspend fun getCountries(
        @Path("countryName") countryName: String
    ): List<CountryDto>
}