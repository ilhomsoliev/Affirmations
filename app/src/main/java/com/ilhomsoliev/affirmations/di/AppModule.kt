package com.ilhomsoliev.affirmations.di

import android.content.Context
import androidx.room.Room
import com.ilhomsoliev.affirmations.core.DataStoreManager
import com.ilhomsoliev.affirmations.data.local.AffirmationDao
import com.ilhomsoliev.affirmations.data.local.AffirmationDatabase
import com.ilhomsoliev.affirmations.data.remote.AffirmationsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAuthenticationApi(): AffirmationsApi {
        return Retrofit.Builder()
            .baseUrl("https://www.affirmations.dev")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build()
            )
            .build()
            .create(AffirmationsApi::class.java)
    }

    @Singleton
    @Provides
    fun provideGroupDatabase(@ApplicationContext context: Context): AffirmationDatabase =
        Room
            .databaseBuilder(context, AffirmationDatabase::class.java, "AffirmationDatabase")
            .createFromAsset("affirmations_with_categories.db")
            //.createFromAsset("")
            .addMigrations(
                AffirmationDatabase.MIGRATION_1_2,
                AffirmationDatabase.MIGRATION_2_3,
                AffirmationDatabase.MIGRATION_3_4
            )
            .build()

    @Singleton
    @Provides
    fun provideAffirmationDao(affirmationDatabase: AffirmationDatabase): AffirmationDao =
        affirmationDatabase.getAffirmationDao()

    @Singleton
    @Provides
    fun provideSessionManager(
        @ApplicationContext context: Context
    ) = DataStoreManager(context)

/*    @Singleton
    @Provides
    fun provideDailyManager(
        @ApplicationContext context: Context
    ) = DailyReadManager(WorkManager.getInstance(context))*/
}