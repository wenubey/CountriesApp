package com.wenubey.countriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.countriesapp.domain.DetailedCountry
import com.wenubey.countriesapp.domain.GetCountriesUseCase
import com.wenubey.countriesapp.domain.GetCountryUseCase
import com.wenubey.countriesapp.domain.SimpleCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
): ViewModel() {
    // this for we want to change state on viewModel not ui
    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { it.copy(countries = getCountriesUseCase.execute(), isLoading = false) }
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
