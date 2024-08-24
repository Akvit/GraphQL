package azhel.example.graphql.domain

interface CountriesUseCase {

    suspend fun getCountries(): List<SimpleCountry>
    suspend fun getCountry(code: String): DetailedCountry?

}