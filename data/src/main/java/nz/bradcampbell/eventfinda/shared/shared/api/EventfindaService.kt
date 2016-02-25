package nz.bradcampbell.eventfinda.shared.shared.api

import okhttp3.HttpUrl
import retrofit2.http.GET
import rx.Observable

val PRODUCTION_API_URL = HttpUrl.parse("http://api.eventfinda.co.nz/v2/")

interface EventfindaService {

  @GET("events.json?rows=25")
  fun events() : Observable<Events>;
}
