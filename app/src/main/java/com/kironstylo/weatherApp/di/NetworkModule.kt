package com.kironstylo.weatherApp.di

import com.kironstylo.weatherApp.weatherFeature.data.remote.forecast.WeatherApiClient
import com.kironstylo.weatherApp.searchCityFeature.data.remote.GeoApiClient
import com.kironstylo.weatherApp.weatherFeature.data.remote.timezone.TimeApiClient
import com.kironstylo.weatherApp.weatherFeature.domain.repository.TimeRepository
import com.kironstylo.weatherApp.weatherFeature.data.repository.TimeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class) // Scope: Activity
object NetworkModule {

    // Provide Retrofit
    @Provides
    @Singleton
    @Named("GeoApi")
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://geocoding-api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("WeatherApi")
    fun providesRetrofit2(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    @Named("TimeApi")
    fun providesRetrofit3(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://timeapi.io/api/TimeZone/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGeoApiClient(@Named("GeoApi")retrofit: Retrofit): GeoApiClient {
        return retrofit.create(GeoApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherApiClient(@Named("WeatherApi")retrofit: Retrofit): WeatherApiClient {
        return retrofit.create(WeatherApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideTimeApiClient(@Named("TimeApi")retrofit: Retrofit): TimeApiClient {
        return retrofit.create(TimeApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideTimeRepository(timeApiClient: TimeApiClient): TimeRepository {
        return TimeRepositoryImpl(timeApiClient)
    }
}