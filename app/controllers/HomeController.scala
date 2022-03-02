package controllers

import javax.inject._
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import models.Task

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val cc: ControllerComponents) extends AbstractController(cc) with play.api.i18n.I18nSupport {
  val taskForm = Form(
    "label" -> nonEmptyText
  )
  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Redirect(routes.HomeController.tasks)
  }

  def tasks = Action  { implicit request =>
    Ok(views.html.index(Task.all(), taskForm))
  }


  def newTask = Action { implicit request =>
    taskForm.bindFromRequest.fold(
      errors => BadRequest(views.html.index(Task.all(), errors)),
      label => {
        Task.create(label)
        Redirect(routes.HomeController.tasks)
      }
    )
  }

  def deleteTask(id: Long) = TODO
}




