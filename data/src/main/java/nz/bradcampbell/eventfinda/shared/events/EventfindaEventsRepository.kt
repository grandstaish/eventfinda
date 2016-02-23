package nz.bradcampbell.eventfinda.shared.events

import nz.bradcampbell.eventfinda.domain.events.Event
import nz.bradcampbell.eventfinda.domain.events.Events
import nz.bradcampbell.eventfinda.domain.events.EventsRepository
import nz.bradcampbell.eventfinda.shared.shared.api.EventfindaService
import rx.Observable

class EventfindaEventsRepository(val service: EventfindaService) : EventsRepository {
  override fun events() : Observable<Events> {
    return service.events()
      .flatMap { Observable.from(it.events) }
      .map { Event(it.id) }
      .toList()
      .map { Events(it) }
  }
}
