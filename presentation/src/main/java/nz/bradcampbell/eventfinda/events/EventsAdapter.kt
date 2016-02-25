package nz.bradcampbell.eventfinda.events

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import nz.bradcampbell.eventfinda.domain.events.Events

class EventsAdapter : RecyclerView.Adapter<EventItemViewHolder>() {
  private var events: Events? = null

  override fun onBindViewHolder(holder: EventItemViewHolder, position: Int) {
    val text = holder.itemView as TextView
    text.text = events!!.events[position].id.toString()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventItemViewHolder? {
    val layoutInflater = LayoutInflater.from(parent.context)
    return EventItemViewHolder(layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false))
  }

  override fun getItemCount(): Int {
    val ev = events
    return if (ev == null) 0 else ev.events.size
  }

  fun setEvents(events: Events) {
    this.events = events
  }
}
