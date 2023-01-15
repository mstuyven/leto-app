package com.mirostuyven.leto.network

import android.os.Parcelable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


private const val BASE_URL = "https://leto.onrender.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val okHttpClient = OkHttpClient.Builder()
    .readTimeout(60, TimeUnit.SECONDS)
    .connectTimeout(60, TimeUnit.SECONDS)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

@Parcelize
data class OverviewResponse<T : Parcelable>(val data: @RawValue List<T>) : Parcelable {}

@Parcelize
data class DetailResponse<T : Parcelable >(val data: @RawValue T) : Parcelable {}

@Parcelize
data class TokenResponse(val data: String?) : Parcelable {}

data class TokenRequest(val email: String, val password: String) {}

interface LetoService {
    @GET("quiz")
    suspend fun listQuizzes(): OverviewResponse<Quiz>

    @GET("quiz/{id}")
    suspend fun quizDetail(@Path("id") id: String): DetailResponse<Quiz>

    @POST("session")
    suspend fun createSessionToken(@Body body: TokenRequest): TokenResponse
}

object LetoApi {
    val service: LetoService by lazy { retrofit.create(LetoService::class.java) }
}