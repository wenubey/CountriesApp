package com.wenubey.countriesapp.di

import com.apollographql.apollo3.ApolloClient
import com.wenubey.countriesapp.core.Constants.BASE_URL_GRAPHQL
import com.wenubey.countriesapp.core.Constants.BASE_URL_REST
import com.wenubey.countriesapp.data.remote.ApolloCountryClient
import com.wenubey.countriesapp.data.remote.CountriesApi
import com.wenubey.countriesapp.domain.CountryClient
import com.wenubey.countriesapp.domain.use_case.GetCountriesInContinentUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(BASE_URL_GRAPHQL)
            .build()
    }

    @Provides
    @Singleton
    fun providesCountryClient(apolloClient: ApolloClient, api: CountriesApi): CountryClient {
        return ApolloCountryClient(apolloClient, api)
    }


    @Provides
    @Singleton
    fun providesGetCountryInContinentUseCase(countryClient: CountryClient): GetCountriesInContinentUseCase {
        return GetCountriesInContinentUseCase(countryClient)
    }

    @Provides
    @Singleton
    fun provideCountriesApi(): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_REST)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }


}