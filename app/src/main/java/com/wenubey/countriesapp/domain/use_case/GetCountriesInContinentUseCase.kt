package com.wenubey.countriesapp.domain.use_case

import com.wenubey.countriesapp.data.remote.CountryDto
import com.wenubey.countriesapp.domain.CountryClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetCountriesInContinentUseCase(
    private val countryClient: CountryClient
) {

    fun execute(code: String): Flow<List<CountryDto>> = flow {
        countryClient.getCountryInContinent(code).collect { countryList ->
            emit(countryList.sortedBy { it.name?.common })
        }
    }.flowOn(Dispatchers.IO)


}