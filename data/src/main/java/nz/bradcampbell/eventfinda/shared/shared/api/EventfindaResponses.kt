package nz.bradcampbell.eventfinda.shared.shared.api

import org.threeten.bp.LocalDateTime

data class Events(val events: Array<Event>)

data class Event(val id: Int,
                 val url: String,
                 val url_slug: String,
                 val name: String,
                 val description: String,
                 val datetime_start: LocalDateTime,
                 val datetime_end: LocalDateTime,
                 val location_summary: String,
                 val address: String,
                 val is_free: Boolean,
                 val is_featured: Boolean,
                 val is_cancelled: Boolean,
                 val restrictions: String,
                 val point: Coordinate,
                 val booking_web_site: Website,
                 val username: String,
                 val timezone: String,
                 val location: Location,
                 val category: Category,
                 val datetime_summary: String,
                 val sessions: Sessions,
                 val ticket_types: TicketTypes,
                 val artists: Artists,
                 val web_sites: Websites,
                 val images: Images)

data class Coordinate(val lat: Double, val lng: Double)

data class Websites(val web_sites: Array<Website>)

data class Website(val url: String, val name: String)

data class Location(val id: Int)

data class Category(val id: Int)

data class Sessions(val sessions: Array<Session>)

data class Session(val id: Int)

data class TicketTypes(val ticket_types: Array<TicketType>)

data class TicketType(val id: Int)

data class Artists(val artists: Array<Artist>)

data class Artist(val id: Int)

data class Images(val images: Array<Image>)

data class Image(val id: Int)