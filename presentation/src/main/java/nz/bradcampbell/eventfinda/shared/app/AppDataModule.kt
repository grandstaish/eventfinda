package nz.bradcampbell.eventfinda.shared.app

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import nz.bradcampbell.eventfinda.domain.events.EventsRepository
import nz.bradcampbell.eventfinda.shared.events.EventfindaEventsRepository
import nz.bradcampbell.eventfinda.shared.shared.adapter.LocalDateTimeAdapter
import nz.bradcampbell.eventfinda.shared.shared.api.EventfindaService
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import javax.inject.Singleton

@Module
class AppDataModule {

  @Provides
  @Singleton
  internal fun provideLoggingInterceptor() : HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return loggingInterceptor
  }

  @Provides
  @Singleton
  internal fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient {
    return OkHttpClient.Builder()
      .authenticator { route, response ->
        val credential = Credentials.basic("na3", "vwq4zbc35rnn")
        response.request().newBuilder()
          .header("Authorization", credential)
          .build();
      }
      .addInterceptor(loggingInterceptor)
      .build()
  }

  @Provides
  @Singleton
  internal fun provideMoshi() : Moshi {
    return Moshi.Builder()
      .add(LocalDateTimeAdapter())
      .build()
  }

  @Provides
  @Singleton
  internal fun provideEventsRepository(eventfindaService: EventfindaService) : EventsRepository {
    return EventfindaEventsRepository(eventfindaService)
  }
}
