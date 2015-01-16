package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json.Json
import models.AddressBooks
import models.db.common._
import play.api.data._
import play.api.data.Forms._
import org.joda.time._
import java.sql.Timestamp

object JsonApi extends Controller {
  def index = Action { implicit request =>
      Ok(Json.toJson(Map("message"->"Hello, World!")))
  }
}

