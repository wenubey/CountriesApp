package com.wenubey.countriesapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wenubey.countriesapp.R
import com.wenubey.countriesapp.core.nullCheck
import com.wenubey.countriesapp.data.CountryDto
import com.wenubey.countriesapp.data.CurrencyDetail
import com.wenubey.countriesapp.data.Name
import com.wenubey.countriesapp.data.NativeName
import com.wenubey.countriesapp.presentation.components.country_detail.CountryItemCardRow

@Composable
fun CountryItemCard(
    country: CountryDto
) {

    ElevatedCard(
        modifier = Modifier.padding(2.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = country.name?.official.nullCheck(),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = country.name?.nativeName?.official.nullCheck(),
                style = MaterialTheme.typography.bodySmall
            )
            CountryItemCardRow(
                imageVector = Icons.Filled.Home,
                contentDescription = stringResource(R.string.capital_of_the_country),
                text = country.capital?.joinToString().nullCheck()
            )
            CountryItemCardRow(
                imageVector = Icons.Filled.Groups,
                contentDescription = stringResource(R.string.population_of_the_country),
                text = country.population?.toString().nullCheck()
            )
            Text(text = "Official Common Name")
        }
    }
}




