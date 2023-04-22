package com.wenubey.countriesapp.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.wenubey.CountriesInContinentQuery
import com.wenubey.countriesapp.core.nullCheck
import com.wenubey.countriesapp.domain.CountryClient

class ApolloCountryClient(
    private val apolloClient: ApolloClient,
    private val api: CountriesApi
) : CountryClient {

    override suspend fun getCountryInContinent(code: String): List<CountryDto> {
        val graphQlResponse =  apolloClient
            .query(CountriesInContinentQuery(code))
            .execute()
            .data
            ?.continent
            ?.countries
            ?.map { it.name }
            ?: emptyList()
        val list = mutableListOf<CountryDto>()
        graphQlResponse.forEach { countryName ->
            try {
                val countries = api.getCountries(countryName)
                list.addAll(countries)
            }catch (e: Exception) {
                Log.e("TAG", "ApolloCountryClientCallError: $countryName", e)
            }
        }
        list.forEach {
            if (it.name?.nativeName?.official == null) {
                Log.i("TAG", "native official name: it is null")
            } else {
                Log.i("TAG", "native official name: ${it.name.nativeName.official}")
            }

        }
        return list
    }
}


