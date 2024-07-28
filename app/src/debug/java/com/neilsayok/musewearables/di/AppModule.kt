package com.neilsayok.musewearables.di

import android.content.Context
import androidx.room.Room
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.neilsayok.musewearables.BuildConfig
import com.neilsayok.musewearables.data.error.ErrorEventData
import com.neilsayok.musewearables.data.error.Resource
import com.neilsayok.musewearables.data.model.Cart
import com.neilsayok.musewearables.domain.repo.MainRepo
import com.neilsayok.musewearables.domain.services.ApiInterface
import com.neilsayok.musewearables.domain.services.AppDatabase
import com.neilsayok.musewearables.domain.services.CartDao
import com.neilsayok.musewearables.domain.services.LikeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(
        @ApplicationContext context: Context,
    ): Context {
        return context
    }

    @Provides
    fun provideFlipperInterceptor(
        @ApplicationContext context: Context
    ): FlipperOkhttpInterceptor =
        FlipperOkhttpInterceptor(
            AndroidFlipperClient
                .getInstance(context)
                .getPluginByClass(NetworkFlipperPlugin::class.java))


    @Singleton
    @Provides
    fun providesOkHttpClient(
        flipperOkhttpInterceptor: FlipperOkhttpInterceptor,
        ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(flipperOkhttpInterceptor)
            .callTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BuildConfig.BFF_HOST)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Singleton
    @Provides
    fun providesErrorLiveData() = MutableStateFlow<Resource<ErrorEventData?>?>(null)

    @Singleton
    @Provides
    fun providesMainRepo(apiInterface: ApiInterface) = MainRepo(apiInterface)


    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "MyDatabase").build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): CartDao {
        return database.cartDao()
    }

    @Provides
    @Singleton
    fun provideLikeDao(database: AppDatabase): LikeDao {
        return database.likeDao()
    }




}