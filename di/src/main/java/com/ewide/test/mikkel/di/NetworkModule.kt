package com.ewide.test.mikkel.di

import android.content.Context
import com.ewide.test.mikkel.base.helper.NetworkHandler
import com.ewide.test.mikkel.network.Builder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class NetworkModule  {
    companion object {
        private fun httpInterceptor() = HttpLoggingInterceptor().apply {
            return HttpLoggingInterceptor { _ ->
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