package nz.bradcampbell.eventfinda.shared.app

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import nz.bradcampbell.eventfinda.shared.shared.api.EventfindaService
import nz.bradcampbell.eventfinda.shared.shared.api.PRODUCTION_API_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

import javax.inject.Singleton

@Module
class AppApiModule {

  @Provides @Singleton
  internal fun provideRetrofit(client: OkHttpClient, moshi: Moshi) : Retrofit {
    return Retrofit.Builder()
      .client(client)
      .baseUrl(PRODUCTION_API_URL)
      .addConverterFactory(MoshiConverterFactory.create(moshi))
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .build()
  }

  @Provides @Singleton
  internal fun provideEventsService(retrofit: Retrofit) : EventfindaService {
    return retrofit.create(EventfindaService::class.java)
  }
}
