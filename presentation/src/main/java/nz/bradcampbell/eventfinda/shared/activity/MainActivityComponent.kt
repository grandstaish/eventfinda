package nz.bradcampbell.eventfinda.shared.activity

import android.support.v7.app.AppCompatDelegate
import dagger.Subcomponent
import nz.bradcampbell.eventfinda.artists.ArtistsView
import nz.bradcampbell.eventfinda.events.EventsView

@MainActivityScope
@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
  fun inject(eventsView: EventsView)
  fun inject(artistsView: ArtistsView)

  fun activity() : MainActivity
  fun appCompatDelegate() : AppCompatDelegate
}