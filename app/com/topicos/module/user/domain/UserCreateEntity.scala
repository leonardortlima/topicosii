package com.topicos.module.user.domain

import play.api.libs.json.Json

case class UserCreateEntity(id: Option[Long], firstName: String, lastName: String, username: String, email: String,
  password: String, place: Place)

object UserCreateEntity {
  implicit val jsonFormat = Json.format[UserCreateEntity]
}
