package com.wenubey.countriesapp.presentation.components.country_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wenubey.countriesapp.R
import com.wenubey.countriesapp.core.nullCheck

@Composable
fun CountryItemCardRow(
    imageVector: ImageVector,
    contentDescription: String,
    text: String,
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(2.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(imageVector = imageVector, contentDescription = contentDescription)
        Text(text = text, style = MaterialTheme.typography.bodySmall)
    }
}