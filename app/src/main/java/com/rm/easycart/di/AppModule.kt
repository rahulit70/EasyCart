package com.rm.easycart.di

import android.content.Context
import androidx.room.Room
import com.rm.easycart.core.db.AppDatabase
import com.rm.easycart.core.db.ProductDAO
import com.rm.easycart.core.network.ApiService
import com.rm.easycart.utils.AppConstants.Companion.BASE_URL
import com.rm.easycart.utils.AppConstants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(@Named("Retrofit") okHttpClient: OkHttpClient): ApiService = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)


    @Provides
    @Singleton
    @Named("Retrofit")
    fun provideOkHttpClientForRetrofit(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext app: Context): ProductDAO =
        Room.databaseBuilder(app, AppDatabase::class.java, DATABASE_NAME)
            .build()
            .getDao()
}