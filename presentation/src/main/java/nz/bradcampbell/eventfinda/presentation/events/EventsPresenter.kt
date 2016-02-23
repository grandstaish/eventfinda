package nz.bradcampbell.eventfinda.presentation.events

import nz.bradcampbell.eventfinda.domain.events.EventsInteractor
import nz.bradcampbell.eventfinda.presentation.shared.Presenter
import rx.Subscription
import rx.subscriptions.Subscriptions

class EventsPresenter(val interactor: EventsInteractor) : Presenter<EventsContract> {
  private var view: EventsContract? = null

  private var eventsSubscription: Subscription? = Subscriptions.empty()
  private var errorsSubscription: Subscription? = Subscriptions.empty()

  override fun bind(view: EventsContract) {
    this.view = view

    eventsSubscription = interactor.eventStream()
      .subscribe { response -> System.out.println(response.toString()) }

    errorsSubscription = interactor.errorStream()
      .subscribe { response -> System.out.println(response.toString()) }
  }

  override fun unbind() {
    this.view = null
  }
}
