package com.wenubey.countriesapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.wenubey.countriesapp.core.ContinentMap

@Composable
fun CountrySelectRow(
    onSelectedContinentChange: (continent: String) -> Unit,
    selectedContinent: MutableState<String>
) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(ContinentMap.continentMap.values.toList()) { continent ->
            OutlinedButton(
                onClick = {
                    onSelectedContinentChange(continent)
                    selectedContinent.value = continent
                },
                modifier = Modifier.padding(2.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (continent == selectedContinent.value) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = continent, style = TextStyle.Default.copy(color = if (continent == selectedContinent.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary))
            }
        }
    }
}