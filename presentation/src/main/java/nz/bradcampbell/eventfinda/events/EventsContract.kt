package nz.bradcampbell.eventfinda.events

import nz.bradcampbell.eventfinda.domain.events.Events

interface EventsContract {
  fun showLoading()
  fun hideLoading()
  fun displayEvents(events: Events)
  fun displayError(throwable: Throwable)
}
