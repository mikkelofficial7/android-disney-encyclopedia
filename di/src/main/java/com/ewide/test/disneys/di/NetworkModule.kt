package com.ewide.test.disneys.di

import android.content.Context
import com.ewide.test.disneys.base.helper.NetworkHandler
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NetworkModule  {
    companion object {
        private fun httpInterceptor() = HttpLoggingInterceptor().apply {
            return HttpLoggingInterceptor { message ->
            }.apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        fun provideNetworkHandler(context: Context) = NetworkHandler(context)

        fun provideOkHttpClient() : OkHttpClient {
            return Builder.initInterceptor(httpInterceptor())
        }

        fun provideRetrofitService(okHttpClient: OkHttpClient): Retrofit {
            return Builder.initRetrofit(okHttpClient)
        }
    }
}