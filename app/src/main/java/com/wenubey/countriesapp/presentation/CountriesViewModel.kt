package com.wenubey.countriesapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.countriesapp.core.ContinentMap
import com.wenubey.countriesapp.core.getKeyByValue
import com.wenubey.countriesapp.domain.use_case.GetCountriesInContinentUseCase
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
    private val getCountriesInContinentUseCase: GetCountriesInContinentUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    private var slideJob: Job? = null
    private var selectJob: Job? = null
    fun onSliderValueChange(sliderValue: Float) {
        _state.update { it.copy(sliderValue = sliderValue) }
        slideJob?.cancel()
        slideJob = viewModelScope.launch {
            delay(500L)
            getCountriesInContinent(state.value.selectedContinent, state.value.sliderValue.toInt())
        }
    }

    fun onMenuContinentSelected(continent: String) {
        _state.update { it.copy(selectedContinent = continent) }
        selectJob?.cancel()
        selectJob = viewModelScope.launch {

        }
    }

    private fun getCountriesInContinent(code: String, numCountries: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    countries = getCountriesInContinentUseCase.execute(
                        ContinentMap.continentMap.getKeyByValue(
                            code,
                        ) ?: "",
                        numCountries = numCountries,
                    ),
                    isLoading = false
                )
            }
        }
    }
}
