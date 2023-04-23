package com.wenubey.countriesapp.core

import com.wenubey.countriesapp.data.remote.CurrencyDetailDto

fun Map<String,String>.getKeyByValue(value: String): String? {
    return this.entries.firstOrNull {
        it.value.contains(value, ignoreCase = true
        ) }?.key
}

fun String?.nullCheck(): String =  this ?: "No info found"

fun Map<String, CurrencyDetailDto>.currencyMapToString(): String {
    return this.map { (key, value) -> "$key: ${value.name} (${value.symbol})" }.joinToString()
}

fun Map<String, String>.toFormattedString(): String {
    return this.map { (key, value) -> "${key.uppercase()}: $value" }.joinToString()
}
