package nz.bradcampbell.eventfinda.domain.shared

import rx.Observable

interface SchedulerProvider {
  fun <T> applySchedulers() : Observable.Transformer<T, T>
}