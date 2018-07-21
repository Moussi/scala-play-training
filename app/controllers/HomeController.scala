package controllers

import javax.inject._

import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
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

  def aymen(name:String, country:String) = Action { request =>
    Ok(s"Hello from $country Mr/Mrs $name")
  }

  def notFound = Action { request =>
    NotFound(s"This call is made by Aymen. ${request}")
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
}