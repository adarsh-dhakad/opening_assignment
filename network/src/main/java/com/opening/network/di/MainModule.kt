package com.opening.network.di

import android.util.Log
import com.google.gson.GsonBuilder
import com.opening.network.Constants.BASE_URL
import com.opening.network.data.api.ApiService
import com.opening.network.data.api.RetrofitHeaderInterceptor
import com.opening.network.data.repository.DashBoardRepository
import okhttp3.EventListener
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.ConnectException
import java.net.SocketException
import java.util.concurrent.TimeUnit

val networkModule = module {
    val gson = GsonBuilder().setLenient().create()

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                // Customize the request
                val request = original.newBuilder()
                request.header("Content-Type", "application/x-www-form-urlencoded")

                var response: Response?
                try {
                    response = chain.proceed(request.build())
                    // Customize or return the response
                    response
                } catch (e: ConnectException) {
                    Log.e("RETROFIT", "ERROR : " + e.localizedMessage)
                    chain.proceed(original)
                } catch (e: SocketException) {
                    Log.e("RETROFIT", "ERROR : " + e.localizedMessage)
                    chain.proceed(original)
                } catch (e: IOException) {
                    Log.e("RETROFIT", "ERROR : " + e.localizedMessage)
                    chain.proceed(original)
                } catch (e: Exception) {
                    Log.e("RETROFIT", "ERROR : " + e.localizedMessage)
                    chain.proceed(original)
                }
            }
            .eventListener(object : EventListener() {

            })
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(RetrofitHeaderInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val okHttpClient = makeOkHttpClient(loggingInterceptor)
        okHttpClient

    }

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }

    single { DashBoardRepository(get()) }
}