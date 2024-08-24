package azhel.example.graphql.domain

class CountriesUseCaseImpl(
    private val countryClient: CountryClient
): CountriesUseCase {

    override suspend fun getCountries() = countryClient.getCountries()
        .sortedBy { it.name }

    override suspend fun getCountry(code: String) = countryClient.getCountry(code)
}
