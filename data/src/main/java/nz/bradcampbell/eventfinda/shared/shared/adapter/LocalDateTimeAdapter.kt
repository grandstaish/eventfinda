package nz.bradcampbell.eventfinda.shared.shared.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDateTime

class LocalDateTimeAdapter {
  @ToJson fun toJson(date: LocalDateTime) : String {
    return date.toString().replace('T', ' ');
  }

  @FromJson fun fromJson(value: String) : LocalDateTime {
    return LocalDateTime.parse(value.replace(' ', 'T'));
  }
}