package nz.bradcampbell.eventfinda.events

import android.content.Context
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.Toast
import butterknife.bindView
import nz.bradcampbell.eventfinda.R
import nz.bradcampbell.eventfinda.domain.events.Events
import nz.bradcampbell.eventfinda.shared.activity.MainActivityComponent
import javax.inject.Inject

class EventsView : SwipeRefreshLayout, EventsContract, SwipeRefreshLayout.OnRefreshListener {
  @Inject lateinit var presenter: EventsPresenter

  private val eventsRecyclerView: RecyclerView by bindView(R.id.events_list)
  private val eventsAdapter = EventsAdapter()

  constructor(context: Context, attrs: AttributeSet, component: MainActivityComponent) : super(context, attrs) {
    component.inject(this)
    setOnRefreshListener(this)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    eventsRecyclerView.layoutManager = LinearLayoutManager(context)
    eventsRecyclerView.adapter = eventsAdapter
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.bind(this)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.unbind()
  }

  override fun onRefresh() {
    presenter.refresh()
  }

  override fun displayEvents(events: Events) {
    eventsAdapter.setEvents(events)
    eventsAdapter.notifyDataSetChanged()
  }

  override fun showLoading() {
    // For whatever reason, the SRL's spinner does not draw itself when we call setRefreshing(true)
    // unless it is posted.
    post { isRefreshing = true }
  }

  override fun hideLoading() {
    // Needs to be posted because showLoading is posted
    post { isRefreshing = false }
  }

  override fun displayError(throwable: Throwable) {
    Toast.makeText(context, throwable.message, Toast.LENGTH_LONG).show()
  }
}
