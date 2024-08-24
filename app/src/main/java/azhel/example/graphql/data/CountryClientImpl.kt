package azhel.example.graphql.data

import azhel.example.graphql.domain.CountryClient
import com.apollographql.apollo3.ApolloClient
import com.plcoding.CountriesQuery
import com.plcoding.CountryQuery

class CountryClientImpl(
    private val apolloClient: ApolloClient
): CountryClient {

    override suspend fun getCountries() = apolloClient.query(CountriesQuery())
        .execute()
        .data
        ?.countries
        ?.map {
            it.toSimpleCountry()
        } ?: emptyList()

    override suspend fun getCountry(code: String) = apolloClient.query(CountryQuery(code))
        .execute()
        .data
        ?.country
        ?.toDetailedCountry()
}