package com.wenubey.countriesapp.data

import com.apollographql.apollo3.ApolloClient
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
            ?.map { it.toSimpleCountry() } // mapping the domain modal
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
}