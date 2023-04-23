package com.wenubey.countriesapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wenubey.countriesapp.presentation.CountriesScreen
import com.wenubey.countriesapp.presentation.CountriesViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    NavHost(navController = navHostController, startDestination = Screen.CountriesScreen.route) {
        composable(route = Screen.CountriesScreen.route) {
            val state by viewModel.state.collectAsStateWithLifecycle()
            CountriesScreen(
                state = state,
                onSelectedContinentChange = viewModel::onSelectedContinentChange,
                getCountriesInContinent = viewModel::getCountriesInContinent,
                onSelectedNumberChange = viewModel::onMenuNumberSelected
            )
        }
    }
}