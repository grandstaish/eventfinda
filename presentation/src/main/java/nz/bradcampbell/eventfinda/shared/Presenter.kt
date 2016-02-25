package nz.bradcampbell.eventfinda.shared

interface Presenter<T> {
  fun bind(view: T)
  fun unbind()
}