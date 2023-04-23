package com.wenubey.countriesapp.data.remote

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.wenubey.CountriesInContinentQuery
import com.wenubey.countriesapp.domain.CountryClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ApolloCountryClient(
    private val apolloClient: ApolloClient,
    private val api: CountriesApi
) : CountryClient {

    override suspend fun getCountryInContinent(code: String): Flow<List<CountryDto>> = flow {
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
        emit(list)
    }.flowOn(Dispatchers.IO)


}


