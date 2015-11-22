package com.topicos.module.user.domain

import play.api.libs.json.Json

class Place(private var _street: String, private var _number: String, private var _lat: String,
            private var _lon: String) {

  def street = _street
  def number = _number
  def lat = _lat
  def lon = _lon

  def apply(street: String, number: String, lat: String, lon: String): Place =
    new Place(street, number, lat, lon)
}

object Place {
  def apply(street: String, number: String, lat: String, lon: String): Place =
    new Place(street, number, lat, lon)

  def unapply(place: Place) = Some(place.street, place.number, place.lat, place.lon)

  implicit val jsonFormat = Json.format[Place]
}

case class UserDetailsEntity(id: Long, firstName: String, lastName: String, username: String, email: String,
                             password: String, place: Place)

object UserDetailsEntity {
  implicit val jsonFormat = Json.format[UserDetailsEntity]

  def of(item: UserCreateEntity) = {

    UserDetailsEntity(
      id = 0,
      firstName = item.firstName,
      lastName = item.lastName,
      username = item.username,
      email = item.email,
      password = item.password,
      place = item.place
    )
  }
}