package nz.bradcampbell.eventfinda.domain.events

import rx.Observable

interface EventsRepository {
  fun events() : Observable<Events>
}