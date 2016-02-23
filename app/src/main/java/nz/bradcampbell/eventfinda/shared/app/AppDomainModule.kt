package nz.bradcampbell.eventfinda.shared.app

import dagger.Module
import dagger.Provides
import nz.bradcampbell.eventfinda.domain.events.EventsInteractor
import nz.bradcampbell.eventfinda.domain.events.EventsRepository
import nz.bradcampbell.eventfinda.domain.shared.SchedulerProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Singleton

@Module
class AppDomainModule {

  @Provides
  @Singleton
  internal fun provideEventsInteractor(eventsRepository: EventsRepository,
                                       schedulerProvider: SchedulerProvider) : EventsInteractor {
    return EventsInteractor(eventsRepository, schedulerProvider)
  }

  @Provides
  @Singleton
  internal fun provideSchedulerProvider() : SchedulerProvider {
    return object : SchedulerProvider {
      override fun <T> applySchedulers() : Observable.Transformer<T, T> {
        return Observable.Transformer {
          it.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        }
      }
    }
  }
}