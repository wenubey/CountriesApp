package com.wenubey.countriesapp.data

import com.apollographql.apollo3.ApolloClient
import com.wenubey.CountriesInContinentQuery
import com.wenubey.CountriesQuery
import com.wenubey.CountryQuery
import com.wenubey.countriesapp.domain.CountryClient
import com.wenubey.countriesapp.domain.DetailedCountry
import com.wenubey.countriesapp.domain.SimpleCountry

class ApolloCountryClient(
    private val apolloClient: ApolloClient
): CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() } // mapping to the domain modal
            ?: emptyList() // if its null we return empty list
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

    override suspend fun getCountriesRandomGivenNumber(numCountries: Int): List<SimpleCountry> {
        return apolloClient
            .query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toSimpleCountry() }
            ?.shuffled() // pick random countries
            ?.take(numCountries)
            ?: emptyList()

    }
}