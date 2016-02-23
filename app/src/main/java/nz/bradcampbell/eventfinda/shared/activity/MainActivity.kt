package nz.bradcampbell.eventfinda.shared.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.LayoutInflaterCompat.setFactory
import android.view.ViewGroup
import nz.bradcampbell.eventfinda.shared.app.App
import nz.bradcampbell.eventfinda.R

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    val app = application as App
    val appComponent = app.getAppComponent()
    val activityComponent = appComponent.plus(MainActivityModule(this))
    setFactory(layoutInflater, MainActivityComponentViewFactory(activityComponent));
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // TODO: navigation
    layoutInflater.inflate(R.layout.events, findViewById(R.id.root) as ViewGroup)
  }
}
