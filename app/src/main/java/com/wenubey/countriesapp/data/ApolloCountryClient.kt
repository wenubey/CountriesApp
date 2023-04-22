package com.wenubey.countriesapp.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.wenubey.CountriesInContinentQuery
import com.wenubey.countriesapp.domain.CountryClient

class ApolloCountryClient(
    private val apolloClient: ApolloClient,
    private val api: CountriesApi
) : CountryClient {

    override suspend fun getCountryInContinent(code: String, numCountries: Int): List<CountryDto> {
        val graphQlResponse =  apolloClient
            .query(CountriesInContinentQuery(code))
            .execute()
            .data
            ?.continent
            ?.countries
            ?.shuffled()
            ?.take(numCountries)
            ?.map { it.toCountryName() }
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
            Log.i("TAG", "from list: ${it.name.common}")
        }
        return list
    }
}


