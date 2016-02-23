package nz.bradcampbell.eventfinda.presentation.shared

interface Presenter<T> {
  fun bind(view: T)
  fun unbind()
}