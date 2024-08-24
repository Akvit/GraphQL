package azhel.example.graphql.data

import azhel.example.graphql.domain.DetailedCountry
import azhel.example.graphql.domain.SimpleCountry
import com.plcoding.CountriesQuery
import com.plcoding.CountryQuery

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        languages = languages.map { it.name },
        continent = continent.name,
        name = name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code,
        emoji = emoji,
        capital = capital ?: "No capital",
        name = name
    )
}
