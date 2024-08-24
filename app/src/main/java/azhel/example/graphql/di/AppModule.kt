package azhel.example.graphql.di

import azhel.example.graphql.data.CountryClientImpl
import azhel.example.graphql.domain.CountriesUseCase
import azhel.example.graphql.domain.CountriesUseCaseImpl
import azhel.example.graphql.domain.CountryClient
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return CountryClientImpl(apolloClient)
    }

    @Provides
    @Singleton
    fun provideCountriesUseCase(countryClient: CountryClient): CountriesUseCase {
        return CountriesUseCaseImpl(countryClient)
    }

}
