package nz.bradcampbell.eventfinda.events

import nz.bradcampbell.eventfinda.domain.events.EventsInteractor
import nz.bradcampbell.eventfinda.shared.Presenter
import rx.Subscription
import rx.subscriptions.Subscriptions

class EventsPresenter(val interactor: EventsInteractor) : Presenter<EventsContract> {
  private var view: EventsContract? = null

  private var eventsSubscription: Subscription? = Subscriptions.empty()
  private var errorsSubscription: Subscription? = Subscriptions.empty()

  override fun bind(view: EventsContract) {
    this.view = view

    view.showLoading()

    eventsSubscription = interactor.eventStream()
      .subscribe {
        view.hideLoading()
        view.displayEvents(it)
      }

    errorsSubscription = interactor.errorStream()
      .subscribe {
        view.hideLoading()
        view.displayError(it)
      }
  }

  override fun unbind() {
    this.view = null
  }

  fun refresh() {
    view?.showLoading()
    interactor.loadEvents()
  }
}
