package com.slim.studiog.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.slim.studiog.BuildConfig
import com.slim.studiog.network.models.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

enum class StudioGhibliApiStatus {LOADING, ERROR, DONE}

private val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val client = OkHttpClient.Builder().apply {
    connectTimeout(15, TimeUnit.SECONDS)
    readTimeout(20, TimeUnit.SECONDS)
    if(BuildConfig.DEBUG) {
        addInterceptor(loggingInterceptor)
    }
}.build()

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .client(client)
    .baseUrl(BuildConfig.BASE_URL)
    .build()



interface StudioGhibliService {

    @GET("films")
    suspend fun getFilms(): List<Film>

    @GET("films/{id}")
    suspend fun getFilmById(@Path("id") filmId: String): Film

    @GET("people")
    suspend fun getPeople(): List<People>

    @GET("people/{id}")
    suspend fun getPeopleById(@Path("id") peopleId: String): People

    @GET("locations")
    suspend fun getLocations(): List<Location>

    @GET("locations/{id}")
    suspend fun getLocationById(@Path("id") locationId: String): Location

    @GET("species")
    suspend fun getSpecies(): List<Species>

    @GET("species/{id}")
    suspend fun getSpeciesById(@Path("id") speciesId: String): Species

    @GET("vehicles")
    suspend fun getVehicles(): List<Vehicle>

    @GET("vehicles/{id}")
    suspend fun getVehicleById(@Path("id") vehicle: String): Vehicle
}

object StudioGhibliApi {
    val retrofitService: StudioGhibliService by lazy {
        retrofit.create(StudioGhibliService::class.java)

    }
}