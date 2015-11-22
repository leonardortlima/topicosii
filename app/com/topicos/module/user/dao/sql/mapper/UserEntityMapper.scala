package com.topicos.module.user.dao.sql.mapper

import com.topicos.module.user.domain.{Place, UserDetailsEntity}
import slick.driver.PostgresDriver.api._

object UserEntityMapper {
  final val USERS_TABLE_NAME = "users"

  final val ID_COLUMN          = "id"
  final val FIRST_NAME_COLUMN  = "first_name"
  final val LAST_NAME_COLUMN   = "last_name"
  final val USERNAME_COLUMN    = "username"
  final val EMAIL_COLUMN       = "email"
  final val PASSWORD_COLUMN    = "password"

  final val STREET_COLUMN      = "street"
  final val NUMBER_COLUMN      = "number"
  final val LAT_COLUMN         = "lat"
  final val LON_COLUMN         = "lon"

  class UserDetailsTableDescriptor(tag: Tag) extends Table[UserDetailsEntity](tag, USERS_TABLE_NAME)
  {
    def id        = column[Long]  (ID_COLUMN,          O.PrimaryKey, O.AutoInc )
    def firstName = column[String](FIRST_NAME_COLUMN               )
    def lastName  = column[String](LAST_NAME_COLUMN)
    def username  = column[String](USERNAME_COLUMN)
    def email     = column[String](EMAIL_COLUMN)
    def password  = column[String](PASSWORD_COLUMN)

    def street    = column[String](STREET_COLUMN)
    def number    = column[String](NUMBER_COLUMN)
    def lat       = column[String](LAT_COLUMN)
    def lon       = column[String](LON_COLUMN)

    def place = (street, number, lat, lon).<>[Place, (String, String, String, String)](
    {
      case (street, number, lat, lon) => Place(street, number, lat, lon)
      case _ => Place("", "", "", "")
    }, {
      case rec: Place => Some(rec.street, rec.number, rec.lat, rec.lon)
    })

    def * = (
      id,
      firstName,
      lastName,
      username,
      email,
      password,
      place
      ) <> ((UserDetailsEntity.apply _).tupled, UserDetailsEntity.unapply)
  }
  object UserDetailsTableDescriptor
  {
    def query = TableQuery[UserDetailsTableDescriptor]
    def queryWithId = query returning query.map(_.id)
  }
}

