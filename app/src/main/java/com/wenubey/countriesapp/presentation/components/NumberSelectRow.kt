package com.wenubey.countriesapp.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NumberSelectRow(
    onSelectedNumberChange: (number: Int) -> Unit,
    selectedNumber: MutableState<Int>
) {
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
}