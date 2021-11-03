package tk.svsq.gamesbestdeals.di

import android.app.Application
import android.content.Context
import androidx.viewbinding.BuildConfig
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tk.svsq.gamesbestdeals.data.network.GameApi
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    companion object {
        private const val COMMON_TIMEOUT = 15L
        const val CONNECT_TIMEOUT = COMMON_TIMEOUT
        const val READ_TIMEOUT = COMMON_TIMEOUT
        const val WRITE_TIMEOUT = COMMON_TIMEOUT
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonBuilder: GsonBuilder,
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
        .baseUrl("https://www.cheapshark.com/api/1.0/")
        .build()

    @Provides
    @Singleton
    fun provideGsonBuilder(): GsonBuilder = GsonBuilder()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        cache: Cache,
        @ApplicationContext context: Context,
        //authInterceptor: HttpAuthInterceptor,
        loggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder().apply {
        cache(cache)
        connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        addInterceptors(context)
        if (BuildConfig.DEBUG) {
            addInterceptor(loggingInterceptor)
        }
    }.build()

    private fun OkHttpClient.Builder.addInterceptors(context: Context): OkHttpClient.Builder {
//    addInterceptor(GzipRequestInterceptor())
        addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        addInterceptor(
            ChuckerInterceptor.Builder(context)
                .collector(ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(emptySet())
                .alwaysReadResponseBody(false)
                .build()
        )
        return this
    }

    @Provides
    @Singleton
    fun providesOkHttpCache(application: Application): Cache = Cache(
        File(application.cacheDir, "http-cache"), (15 * 1024 * 1024).toLong() /* 15 MB */)

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor()
        .apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun provideGameApi(retrofit: Retrofit): GameApi = retrofit.create(GameApi::class.java)

}