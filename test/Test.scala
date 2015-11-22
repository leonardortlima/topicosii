import org.specs2.mutable._
import play.api.test.Helpers._
import play.api.test._

class Test extends Specification {

  "Endpoints usuario" should {
    "responder com codigo 200 com usuario existente" in new WithApplication {
      route(FakeRequest(GET, "/users/1")) must beSome.which (status(_) == OK)
    }

    "responder com codigo 404 com usuario inexistente" in new WithApplication {
      route(FakeRequest(GET, "/users/-1")) must beSome.which (status(_) == NOT_FOUND)
    }
  }
}
