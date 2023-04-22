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

    fun onSliderValueChange(sliderValue: Float) {

    }

    fun onMenuContinentSelected(continent: String) {
        _state.update { it.copy(selectedContinent = continent) }
    }

    fun onMenuNumberSelected(number: Int) {
        _state.update { it.copy(selectedNumber = number) }
    }

    fun getCountriesInContinent() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val continent = ContinentMap.continentMap.getKeyByValue(state.value.selectedContinent) ?: ""
            val selectedNumber = state.value.selectedNumber

            val countries = if (selectedNumber > 0) {
                getCountriesInContinentUseCase.execute(continent).shuffled().take(selectedNumber)
            } else {
                getCountriesInContinentUseCase.execute(continent)
            }

            _state.update {
                it.copy(countries = countries, isLoading = false, selectedNumber = 0)
            }
        }
    }
}
