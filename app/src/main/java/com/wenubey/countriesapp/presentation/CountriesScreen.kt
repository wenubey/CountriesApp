package com.wenubey.countriesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wenubey.countriesapp.presentation.components.CountryDetail
import com.wenubey.countriesapp.presentation.components.CountryItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    state: CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit,
    onSearchQueryChange: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(value = state.searchQuery, onValueChange = onSearchQueryChange, modifier = Modifier.fillMaxWidth())
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