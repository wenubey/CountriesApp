package com.wenubey.countriesapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wenubey.countriesapp.core.ContinentMap
import com.wenubey.countriesapp.core.components.ProgressBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    state: CountriesState,
    onSliderValueChange: (sliderValue: Float) -> Unit,
    onSelectedContinentChane: (continent: String) -> Unit
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
                LazyRow(modifier = Modifier.fillMaxWidth()) {
                    items(ContinentMap.continentMap.values.toList()) { continent ->
                        Button(onClick = { onSelectedContinentChane(continent)  }, modifier = Modifier.padding(2.dp)) {
                            Text(text = continent)
                        }
                    }
                }
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
                    items(state.countries) {
                        Text(text = it.name.common)
                    }
                }
            }
        }
    }
}