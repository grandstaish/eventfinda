package nz.bradcampbell.eventfinda.domain.events

import nz.bradcampbell.eventfinda.domain.shared.SchedulerProvider
import rx.lang.kotlin.BehaviourSubject

class EventsInteractor(private val eventsRepository: EventsRepository,
                       private val schedulerProvider : SchedulerProvider) {

  private val events = BehaviourSubject<Events>()
  private val errors = BehaviourSubject<Throwable>()

  fun loadEvents() {
    eventsRepository.events()
      .compose(schedulerProvider.applySchedulers<Events>())
      .subscribe({
        events.onNext(it)
      }, { e ->
        errors.onNext(e)
      })
  }

  fun eventStream() = events.asObservable()

  fun errorStream() = errors.asObservable()
}
