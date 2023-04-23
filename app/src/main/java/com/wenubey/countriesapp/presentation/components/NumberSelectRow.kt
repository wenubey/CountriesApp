package com.wenubey.countriesapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun NumberSelectRow(
    onSelectedNumberChange: (number: Int) -> Unit,
    selectedNumber: MutableState<Int>
) {
    LazyRow {
        for (index in 2..10) {
            item {
                OutlinedButton(
                    onClick = {
                        selectedNumber.value = index
                        onSelectedNumberChange(index)
                    },
                    modifier = Modifier.padding(2.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (index == selectedNumber.value) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary,

                    )
                ) {
                    Text(text = index.toString(), style = TextStyle.Default.copy(color = if (index == selectedNumber.value) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onPrimary))
                }
            }
        }
    }
}