package com.topicos.module.user.dao

import com.google.inject.ImplementedBy
import com.topicos.core.GeneratedId
import com.topicos.module.user.dao.sql.UserRepositoryImpl
import com.topicos.module.user.domain.UserDetailsEntity

import scala.concurrent.Future

@ImplementedBy(classOf[UserRepositoryImpl])
trait UserRepository {
  def insert(item: UserDetailsEntity): Future[GeneratedId]

  def findById(id: Long): Future[Option[UserDetailsEntity]]
  def findByUsername(username: String): Future[Option[UserDetailsEntity]]
  def findByEmail(email: String): Future[Option[UserDetailsEntity]]
  def findAll: Future[List[UserDetailsEntity]]
}
