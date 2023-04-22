package com.wenubey.countriesapp.data

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.wenubey.CountriesInContinentQuery
import com.wenubey.CountriesQuery
import com.wenubey.CountriesRandomGivenNumberQuery
import com.wenubey.CountryQuery
import com.wenubey.countriesapp.domain.CountryClient
import com.wenubey.countriesapp.domain.DetailedCountry
import com.wenubey.countriesapp.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient,
    private val api: CountriesApi
) : CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() } // mapping to the domain modal
            ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient
            .query(CountryQuery(code))
            .execute()
            .data
            ?.country
            ?.toDetailCountry()
    }


    override suspend fun getCountryInContinent(code: String): List<SimpleCountry> {
        return apolloClient
            .query(CountriesInContinentQuery(code))
            .execute()
            .data
            ?.continent
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?: emptyList()
    }

    override suspend fun getCountriesRandomGivenNumber(numCountries: Int): List<CountryDto> {
        val graphQLResponse = apolloClient
            .query(CountriesRandomGivenNumberQuery())
            .execute()
            .data
            ?.countries
            ?.shuffled()
            ?.take(numCountries)
            ?.map { it.toCountryName() }
            ?: emptyList()
        graphQLResponse.forEach {
            Log.i("TAG", "graphQL name: $it")
        }

        val list = mutableListOf<CountryDto>()
        graphQLResponse.forEach { countryName ->
            try {
                val countries = api.getCountries(countryName)
                list.addAll(countries)
            } catch (e: Exception) {
                Log.e("TAG", "ApolloCountryClientCallError: $countryName", e)
            }
        }
        list.forEach {
                Log.i("TAG", "list items: ${it.name.common}")
        }
        return list
    }
}