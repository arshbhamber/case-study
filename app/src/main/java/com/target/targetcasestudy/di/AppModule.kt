package com.target.targetcasestudy.di

import android.content.Context
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.repository.DealRepository
import com.target.targetcasestudy.data.local.AppDatabase
import com.target.targetcasestudy.data.local.DealDao
import com.target.targetcasestudy.data.network.DealService
import com.target.targetcasestudy.data.repository.DealRepositoryImpl
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

    @Singleton
    @Provides
    @Named("BASE_URL")
    fun provideBaseUrl(
        @ApplicationContext context: Context
    ): String = context.getString(R.string.BASE_URL)



    @Singleton
    @Provides
    fun provideRetrofit(
        @Named("BASE_URL") baseUrl: String,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesOkHTTPClient(
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideApiService(
        retrofit: Retrofit
    ): DealService = retrofit.create(DealService::class.java)

    @Provides
    fun provideDealRepository(repository: DealRepositoryImpl): DealRepository {
        return repository
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase{
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun provideDealDao(database: AppDatabase): DealDao{
        return database.dealDao()
    }
}