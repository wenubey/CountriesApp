package com.wenubey.countriesapp.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wenubey.countriesapp.core.Constants.SELECTED_CONTINENT_KEY
import com.wenubey.countriesapp.core.Constants.SELECTED_NUMBER_KEY
import com.wenubey.countriesapp.core.ContinentMap
import com.wenubey.countriesapp.core.getKeyByValue
import com.wenubey.countriesapp.domain.use_case.GetCountriesInContinentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val getCountriesInContinentUseCase: GetCountriesInContinentUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState(
        selectedContinent = savedStateHandle[SELECTED_CONTINENT_KEY] ?: "Africa",
        selectedNumber = savedStateHandle[SELECTED_NUMBER_KEY] ?: 0
    ))
    val state = _state.asStateFlow()

    fun onSelectedContinentChange(continent: String) {
        _state.update { it.copy(selectedContinent = continent) }
        savedStateHandle[SELECTED_CONTINENT_KEY] = continent
    }

    fun onMenuNumberSelected(number: Int) {
        _state.update { it.copy(selectedNumber = number) }
        savedStateHandle[SELECTED_NUMBER_KEY] = number
    }

    fun getCountriesInContinent() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val continent = ContinentMap.continentMap.getKeyByValue(state.value.selectedContinent) ?: "Africa"
            val selectedNumber = state.value.selectedNumber

            getCountriesInContinentUseCase.execute(continent).collect { countryList ->
                val countries = if (selectedNumber > 0) {
                    countryList.shuffled().take(selectedNumber)
                } else {
                    countryList
                }
                _state.update {
                    it.copy(countries = countries, isLoading = false)
                }
            }


        }
    }
}
