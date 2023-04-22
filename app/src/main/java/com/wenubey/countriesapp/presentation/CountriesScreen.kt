package com.wenubey.countriesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wenubey.countriesapp.R
import com.wenubey.countriesapp.core.components.ProgressBar
import com.wenubey.countriesapp.presentation.components.CountryDetail
import com.wenubey.countriesapp.presentation.components.CountryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    state: CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit,
    onSearchQueryChange: (query: String) -> Unit,
    onSliderValueChange: (sliderValue: Float) -> Unit,
) {

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        if (state.isLoading) {
            ProgressBar()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = state.searchQuery,
                    onValueChange = onSearchQueryChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text = stringResource(R.string.search)) },
                    placeholder = { Text(text = stringResource(R.string.enter_continent_name)) }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${state.sliderValue.toInt()}", fontSize = 24.sp)
                Slider(
                    value = state.sliderValue,
                    onValueChange = onSliderValueChange,
                    steps = 8,
                    valueRange = 2f..10f,//2 to 10 inclusive range
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.countries) { country ->
                        CountryItem(country = country,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSelectCountry(country.code) }
                                .padding(16.dp)
                        )
                    }
                }
            }

            if (state.selectedCountry != null) {
                CountryDetail(
                    country = state.selectedCountry,
                    onDismiss = onDismissCountryDialog,
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(5.dp)
                        )
                        .background(Color.White)
                        .padding(16.dp)
                )
            }
        }
    }
}