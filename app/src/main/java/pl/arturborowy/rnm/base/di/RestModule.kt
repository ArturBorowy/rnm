package pl.arturborowy.rnm.base.di

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pl.arturborowy.rnm.BuildConfig
import pl.arturborowy.rnm.data.remote.RnmService
import pl.arturborowy.rnm.data.remote.base.NetworkConnectionInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val restModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(RnmService.API_BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    single { GsonBuilder().create() }
    single {
        HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else
                    HttpLoggingInterceptor.Level.NONE
        }
    }
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<NetworkConnectionInterceptor>())
            .build()
    }

    single { NetworkConnectionInterceptor(get()) }

    single { get<Retrofit>().create(RnmService::class.java) }
}