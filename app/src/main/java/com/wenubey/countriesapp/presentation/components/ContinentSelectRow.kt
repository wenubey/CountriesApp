package com.wenubey.countriesapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wenubey.countriesapp.core.ContinentMap

@Composable
fun CountrySelectRow(
    onSelectedContinentChange: (continent: String) -> Unit,
    selectedContinent: MutableState<String>
) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(ContinentMap.continentMap.values.toList()) { continent ->
            Button(
                onClick = {
                    onSelectedContinentChange(continent)
                    selectedContinent.value = continent
                },
                modifier = Modifier.padding(2.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (continent == selectedContinent.value) Color.Green else Color.Blue
                )
            ) {
                Text(text = continent)
            }
        }
    }
}