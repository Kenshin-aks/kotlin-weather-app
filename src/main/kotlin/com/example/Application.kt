package com.example


import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.html.*
import kotlinx.html.*


import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


@Serializable
data class GeoResults(val results: List<GeoItem>? = null)


@Serializable
data class GeoItem(
val name: String,
val latitude: Double,
val longitude: Double,
val country: String? = null,
val timezone: String? = null
)


@Serializable
data class Current(
val time: String? = null,
val temperature_2m: Double? = null,
val relative_humidity_2m: Double? = null,
val apparent_temperature: Double? = null,
val wind_speed_10m: Double? = null
)


@Serializable
data class Hourly(
val time: List<String>? = null,
val temperature_2m: List<Double>? = null
)


@Serializable
data class WeatherResponse(
val current: Current? = null,
val hourly: Hourly? = null
)


suspend fun geocode(client: HttpClient, city: String): GeoItem? {
val res: GeoResults = client.get("https://geocoding-api.open-meteo.com/v1/search") {
parameter("name", city)
parameter("count", 1)
parameter("language", "vi")
parameter("format", "json")
}.body()
return res.results?.firstOrNull()
}


suspend fun fetchWeather(client: HttpClient, lat: Double, lon: Double): WeatherResponse {
return client.get("https://api.open-meteo.com/v1/forecast") {
parameter("latitude", lat)
parameter("longitude", lon)
parameter("current", "temperature_2m,relative_humidity_2m,apparent_temperature,wind_speed_10m")
parameter("hourly", "temperature_2m")
parameter("timezone", "auto")
}.body()
}


fun HTML.page(cityInput: String?, error: String? = null, place: GeoItem? = null, weather: WeatherResponse? = null) {
head {
}
