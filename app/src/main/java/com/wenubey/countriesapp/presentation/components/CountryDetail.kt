package com.wenubey.countriesapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.wenubey.countriesapp.R
import com.wenubey.countriesapp.domain.DetailedCountry

@Composable
fun CountryDetail(
    country: DetailedCountry,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    // if country.languages contains more than one language it look like that for example English, Polish, Spanish
    val joinedLanguages = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = modifier) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = country.emoji,
                    fontSize = 32.sp
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = country.name,
                    fontSize = 24.sp
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = R.string.continent, country.continent))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Currency: " + country.currency)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Capital: " + country.capital)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Language(s): $joinedLanguages")
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}