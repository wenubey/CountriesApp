package com.wenubey.countriesapp.core

import com.wenubey.countriesapp.data.CurrencyDetail

fun Map<String,String>.getKeyByValue(value: String): String? {
    return this.entries.firstOrNull {
        it.value.contains(value, ignoreCase = true
        ) }?.key
}

fun String?.nullCheck(): String =  this ?: "No info found"

fun Map<String, CurrencyDetail>.toFormattedStringList(): String {
    return this.map { (key, value) -> "$key: ${value.name} (${value.symbol})" }.joinToString()
}
