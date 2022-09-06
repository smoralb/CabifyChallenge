package com.smb.core.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.smb.core.ShoppingCart
import com.smb.core.data.dataStore.proto.Serializer
import com.smb.core.data.dataStore.source.CheckoutLocalSource
import com.smb.core.data.dataStore.source.LocalSourceImpl
import com.smb.core.data.dataStore.source.mapper.LocalDataMapper
import com.smb.core.data.dataStore.source.mapper.LocalDataMapperImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_URL = "https://gist.githubusercontent.com"
const val DATA_STORE_FILE_NAME = "cart.db"

val baseDataModule = module {

    fun provideMoshiInstance(): Moshi {
        return Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(logging)
            .addInterceptor(Interceptor { chain ->
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .build()
                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder.build()

                chain.proceed(request)
            }).build()
    }

    fun provideRetrofit(factory: Moshi, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(factory))
            .client(client)
            .build()
    }

    factory { provideMoshiInstance() }

    factory { provideHttpClient() }

    factory { provideRetrofit(get(), get()) }

    single<DataStore<ShoppingCart>> {
        DataStoreFactory.create(
            serializer = Serializer,
            produceFile = { androidContext().dataStoreFile(DATA_STORE_FILE_NAME) },
            corruptionHandler = null
        )
    }

    factory<LocalDataMapper> { LocalDataMapperImpl() }

    single<CheckoutLocalSource> { LocalSourceImpl(dataStore = get(), mapper = get()) }

}