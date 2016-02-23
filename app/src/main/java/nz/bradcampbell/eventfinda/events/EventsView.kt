package nz.bradcampbell.eventfinda.events

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import nz.bradcampbell.eventfinda.presentation.events.EventsContract
import nz.bradcampbell.eventfinda.presentation.events.EventsPresenter
import nz.bradcampbell.eventfinda.shared.activity.MainActivityComponent
import javax.inject.Inject

class EventsView : FrameLayout, EventsContract {
  @Inject lateinit var presenter: EventsPresenter

  constructor(context: Context, attrs: AttributeSet, component: MainActivityComponent) : super(context, attrs) {
    component.inject(this)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.bind(this)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.unbind()
  }
}
