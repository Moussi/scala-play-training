package controllers

import java.io.File

import javax.inject._
import play.api.mvc._


/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class HomeController @Inject()(cc: ControllerComponents)(implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  /**
    * Create an Action to render an HTML page with a welcome message.
    * The configuration in the `routes` file means that this method
    * will be called when the application receives a `GET` request with
    * a path of `/`.
    */
  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def aymen(name: String, country: String, address: Option[String]) = Action { request =>
    Ok(s"<h1>Hello from $country Mr/Mrs $name ${address.getOrElse("62 rue Charles 92800 Puteaux")}</h1>")
      .as(HTML)
      .withCookies(Cookie("test", "cookie"))
      .withHeaders("tid" -> "azqswx")
      .withSession("connected" -> "moussi@gmail.com")
      .flashing(
      "success" -> "Wiwwwwwwwwwwwww")
  }

  def notFound = Action { request =>
    NotFound(s"This call is made by Aymen. ${request}")
  }

  def session = Action { request =>
    request.session.get("connected").map { user =>
      Ok("Hello " + user + " => " + request.flash.get("success"))
    }.getOrElse {
      Unauthorized("Oops, you are not connected")
    }
  }

  def save = Action(parse.file(to = new File("/home/moussi/malek.pdf"))) { request =>
    Ok("Saved the request content to " + request.body).withNewSession
  }

  def serverError = Action { request =>
    InternalServerError(s"This call is made by Aymen. ${request}")
  }

  def status = Action { request =>
    Status(400)(s"This call is made by Aymen. ${request}")
  }

  def redirect = Action { request =>
    Redirect("/aymen")
  }

  def helloBob = Action {
    Redirect(routes.HomeController.aymen("Malek", "Tunis", Option.empty))
  }
}
