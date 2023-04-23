package com.wenubey.countriesapp.presentation.components.country_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wenubey.countriesapp.R
import com.wenubey.countriesapp.core.nullCheck
import com.wenubey.countriesapp.core.toFormattedStringList
import com.wenubey.countriesapp.data.remote.CountryDto

@Composable
fun CountryItemCard(
    country: CountryDto
) {
    ElevatedCard(
        modifier = Modifier.padding(8.dp),
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
                text = country.name?.common.nullCheck(),
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
            CountryItemCardRow(
                imageVector = Icons.Filled.CurrencyExchange,
                contentDescription = stringResource(id = R.string.currencies_of_the_current_country),
                text = country.currencies?.toFormattedStringList().nullCheck()
            )
            CountryItemCardRow(
                imageVector = Icons.Filled.Public,
                contentDescription = stringResource(R.string.subregion_of_this_country),
                text = country.subregion.nullCheck()
            )

        }
    }
}




