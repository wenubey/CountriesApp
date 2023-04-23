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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wenubey.countriesapp.R
import com.wenubey.countriesapp.core.components.ProgressBar
import com.wenubey.countriesapp.presentation.components.CountrySelectRow
import com.wenubey.countriesapp.presentation.components.NumberSelectRow
import com.wenubey.countriesapp.presentation.components.country_detail.CountryItemCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesScreen(
    state: CountriesState,
    onSelectedContinentChange: (continent: String) -> Unit,
    getCountriesInContinent: () -> Unit,
    onSelectedNumberChange: (number: Int) -> Unit,

    ) {
    val selectedContinent = rememberSaveable { mutableStateOf("Africa") }
    val selectedNumber = rememberSaveable {
        mutableStateOf(0)
    }

    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                modifier = Modifier.clip(RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp))
            )
        }
    ) { paddingValues ->
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
                NumberSelectRow(
                    onSelectedNumberChange = onSelectedNumberChange,
                    selectedNumber = selectedNumber
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(onClick = getCountriesInContinent) {
                        Text(text = stringResource(R.string.apply))
                    }
                    Button(onClick = {
                        onSelectedNumberChange(0)
                        selectedNumber.value = 0
                    }) {
                        Text(text = stringResource(R.string.reset_the_number))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.countries) { country ->
                        CountryItemCard(country = country)
                    }
                }
            }
        }
    }
}