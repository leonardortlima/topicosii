import java.util.TimeZone

import org.joda.time.DateTimeZone
import play.api._
import play.api.mvc.WithFilters

object Global extends WithFilters() with GlobalSettings {
  override def beforeStart(app: Application) {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    DateTimeZone.setDefault(DateTimeZone.UTC)
  }

  override def onStop(app: play.api.Application) {

  }
}
