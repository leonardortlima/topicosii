package controllers

import javax.inject.Inject
import com.topicos.module.user.domain.UserCreateEntity
import com.topicos.module.user.service.domain.UserDomainService
import play.api.libs.json.Json
import play.api.mvc.{BodyParsers, Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class UserController @Inject()(private val userDomainService: UserDomainService)() extends Controller {

  def read(id: Long) = Action.async {
    request =>
      for {
        itemCandidate <- this.userDomainService.tryGetById(id)
        result <- {
          if(itemCandidate.isEmpty) {
            Future.successful(NotFound)
          } else {
            Future.successful(Ok(Json.toJson(itemCandidate.get)))
          }
        }
      } yield result
  }

  def create = Action.async(BodyParsers.parse.json) { implicit request =>
    try {
      request.body.validate[UserCreateEntity].fold(
        errors => {
          Future.successful(BadRequest)
        },
        user => {
          for {
            generatedId <- this.userDomainService.create(user)
            createdItem <- this.userDomainService.getById(generatedId.id)
            result <- {
              for {
                result <- Future.successful(Ok(Json.toJson(createdItem)))
              } yield result
            }
          } yield result
        }
      )
    } catch {
      case e: Exception =>
        Future.successful(InternalServerError)
    }
  }
}
