package nz.bradcampbell.eventfinda.shared.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.squareup.leakcanary.LeakCanary
import nz.bradcampbell.eventfinda.domain.events.EventsInteractor
import javax.inject.Inject

class App : Application() {
  lateinit private var appComponent: AppComponent

  @Inject lateinit var eventsInteractor: EventsInteractor

  override fun onCreate() {
    super.onCreate()

    AndroidThreeTen.init(this);
    LeakCanary.install(this);

    appComponent = DaggerAppComponent.builder()
      .appDataModule(AppDataModule())
      .appApiModule(AppApiModule())
      .appDomainModule(AppDomainModule())
      .build()

    appComponent.inject(this)

    eventsInteractor.loadEvents()
  }

  fun getAppComponent() : AppComponent = appComponent
}
