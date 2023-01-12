package com.mirostuyven.leto.network

import android.os.Parcelable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://leto.onrender.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

@Parcelize
data class OverviewResponse<T : Parcelable> (val data: @RawValue List<T>) : Parcelable {}

interface LetoService {
    @GET("quiz")
    suspend fun listQuizzes(): OverviewResponse<Quiz>
}

object LetoApi {
    val service: LetoService by lazy { retrofit.create(LetoService::class.java) }
}