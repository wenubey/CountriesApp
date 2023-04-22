package com.wenubey.countriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.wenubey.countriesapp.presentation.CountriesScreen
import com.wenubey.countriesapp.presentation.CountriesViewModel
import com.wenubey.countriesapp.ui.theme.CountriesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountriesAppTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                CountriesScreen(
                    state = state,
                    // ::function is invoke the function it's like { viewModel.dismissCountryDialog() }
                    onSliderValueChange = viewModel::onSliderValueChange,
                    onSelectedContinentChane = viewModel::onMenuContinentSelected
                )
            }
        }
    }
}
