package models
import anorm._
import anorm.SqlParser._

import play.api.db



case class Task(id: Long, label: String)


object Task {

  val task = {
    get[Long]("id") ~
      get[String]("label") map {
      case id~label => Task(id, label)
    }
  }

  def all(): List[Task] = DB.withConnection { implicit c =>
    SQL("select * from task").as(task *)
  }

  def create(label: String) {}

  def delete(id: Long) {}

}