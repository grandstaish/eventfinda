package nz.bradcampbell.eventfinda.shared.app

import dagger.Component
import nz.bradcampbell.eventfinda.shared.activity.MainActivityComponent
import nz.bradcampbell.eventfinda.shared.activity.MainActivityModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppDataModule::class, AppApiModule::class, AppDomainModule::class))
interface AppComponent {
  fun plus(mainActivityModule: MainActivityModule) : MainActivityComponent

  fun inject(app: App)
}