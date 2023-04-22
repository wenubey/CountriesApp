package com.wenubey.countriesapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wenubey.countriesapp.core.ContinentMap
import com.wenubey.countriesapp.core.components.ProgressBar
import com.wenubey.countriesapp.presentation.components.CountrySelectRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    state: CountriesState,
    onSelectedContinentChange: (continent: String) -> Unit,
    onUserSelected: () -> Unit,
    onSelectedNumberChange: (number: Int) -> Unit
) {
    val selectedContinent = remember { mutableStateOf("") }
    val selectedNumber = remember {
        mutableStateOf(0)
    }

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
                CountrySelectRow(
                    onSelectedContinentChange = onSelectedContinentChange,
                    selectedContinent = selectedContinent
                )
                Spacer(modifier = Modifier.height(8.dp))
                LazyRow {
                    for (index in 2..10) {
                        item {
                            Button(
                                onClick = {
                                    selectedNumber.value = index
                                    onSelectedNumberChange(index)
                                },
                                modifier = Modifier.padding(2.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (index == selectedNumber.value) Color.Green else Color.Blue
                                )
                            ) {
                                Text(text = index.toString())
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                    Button(onClick = onUserSelected) {
                        Text(text = "Apply")
                    }
                    Button(onClick = { onSelectedNumberChange(0) }) {
                        Text(text = "Reset the number")
                    }
                }
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