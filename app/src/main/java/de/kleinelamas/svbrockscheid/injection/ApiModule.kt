package de.kleinelamas.svbrockscheid.injection

import android.content.Context
import dagger.Module
import dagger.Provides
import de.kleinelamas.svbrockscheid.connection.ApiClient
import retrofit2.Retrofit
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * @author Matthias Kutscheid
 */
@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideApiClient(context: Context): ApiClient {
        val clientBuilder = OkHttpClient().newBuilder()
        clientBuilder.cache(
                Cache(context.cacheDir, 1024*1024*50))
        val retrofit = Retrofit.Builder().client(clientBuilder.build()).baseUrl(BuildDependentConstants.URL).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit.create(ApiClient::class.java)
    }

}