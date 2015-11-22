package com.topicos.configuration

import javax.inject.Singleton

import play.api.Play.current
import play.api.db.DB
import slick.driver.PostgresDriver.api._

@Singleton()
class DatabaseProvider {
  lazy val db = Database.forDataSource(DB.getDataSource())
}
