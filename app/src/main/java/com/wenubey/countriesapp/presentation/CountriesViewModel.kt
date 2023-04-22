package com.wenubey.countriesapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.countriesapp.core.ContinentMap
import com.wenubey.countriesapp.core.getKeyByValue
import com.wenubey.countriesapp.domain.use_case.GetCountriesInContinentUseCase
import com.wenubey.countriesapp.domain.use_case.GetCountriesRandomGivenNumberUseCase
import com.wenubey.countriesapp.domain.use_case.GetCountriesUseCase
import com.wenubey.countriesapp.domain.use_case.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase,
    private val getCountriesInContinentUseCase: GetCountriesInContinentUseCase,
    private val getCountriesRandomGivenNumberUseCase: GetCountriesRandomGivenNumberUseCase
) : ViewModel() {
    // this for we want to change state on viewModel not ui
    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    private var searchJob: Job? = null
    private var slideJob: Job? = null

    init {
        getCountries()
    }

    fun onSearchQueryChange(code: String) {
        _state.update { it.copy(searchQuery = code) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            // Wait  0.5 second for user input
            delay(500L)
            getCountriesInContinent(code)
            if (_state.value.searchQuery.isBlank()) {
                getCountries()
            }
        }
    }

    fun onSliderValueChange(sliderValue: Float) {
        _state.update { it.copy(sliderValue = sliderValue) }
        slideJob?.cancel()
        slideJob = viewModelScope.launch {
            delay(500L)
            getCountriesRandomGivenNumber(sliderValue.toInt())
        }
    }

    private fun getCountries() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { it.copy(countries = getCountriesUseCase.execute(), isLoading = false) }
        }
    }


    private fun getCountriesRandomGivenNumber(numCountries: Int) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val list = getCountriesRandomGivenNumberUseCase.execute(numCountries)
            list.forEach {
                Log.i("TAG", "${it.name}")
            }
            //_state.update { it.copy(countries = getCountriesRandomGivenNumberUseCase.execute(numCountries), isLoading = false) }
        }
    }

    private fun getCountriesInContinent(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    countries = getCountriesInContinentUseCase.execute(
                        ContinentMap.continentMap.getKeyByValue(
                            code,
                        ) ?: ""
                    ),
                    isLoading = false
                )
            }
        }
    }

    fun selectCountry(code: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = getCountryUseCase.execute(code)
                )
            }
        }
    }

    // when dismiss selected country we remove the last country
    fun dismissCountryDialog() {
        _state.update { it.copy(selectedCountry = null) }
    }

}
