package nz.bradcampbell.eventfinda.shared.activity

import android.support.v7.app.AppCompatDelegate
import dagger.Module
import dagger.Provides
import nz.bradcampbell.eventfinda.domain.events.EventsInteractor
import nz.bradcampbell.eventfinda.presentation.events.EventsPresenter

@Module
class MainActivityModule(val activity: MainActivity) {

  @Provides
  @MainActivityScope
  internal fun provideMainActivity() : MainActivity {
    return activity;
  }

  @Provides
  @MainActivityScope
  internal fun provideAppCompatDelegate() : AppCompatDelegate {
    return activity.delegate;
  }

  @Provides
  @MainActivityScope
  internal fun provideEventsPresenter(eventsInteractor: EventsInteractor) : EventsPresenter {
    return EventsPresenter(eventsInteractor)
  }
}