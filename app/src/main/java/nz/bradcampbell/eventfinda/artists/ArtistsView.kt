package nz.bradcampbell.eventfinda.artists

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import nz.bradcampbell.eventfinda.presentation.artists.ArtistsContract
import nz.bradcampbell.eventfinda.shared.activity.MainActivityComponent

class ArtistsView : FrameLayout, ArtistsContract {
//  @Inject lateinit var presenter: ArtistsPresenter

  constructor(context: Context, attrs: AttributeSet, component: MainActivityComponent) : super(context, attrs) {
    component.inject(this)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
//    presenter.bind(this)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
//    presenter.unbind()
  }
}