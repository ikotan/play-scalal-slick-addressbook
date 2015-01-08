package controllers

import play.api._
import play.api.mvc._
import models.db.common.Generate
import scala.slick.model.codegen.SourceCodeGenerator
import play.api.data._
import play.api.data.Forms._
import org.joda.time._
import scala.slick.{model => m}

class CustomSourceCodeGenerator(model: m.Model) extends SourceCodeGenerator(model) {

  // add some custom imports
  // TODO: fix these imports to refer to your JdbcSupport and your Joda imports
  override def code = "import com.github.tototoshi.slick.PostgresJodaSupport._\n" + "import org.joda.time.DateTime\n" + super.code

  override def Table = new Table(_) {
    override def Column = new Column(_) {

      // munge rawType -> SQL column type HERE (scaladoc in Slick 2.1.0 is outdated or incorrect, GeneratorHelpers#mapJdbcTypeString does not exist)
      // you can filter on model.name for the column name or model.tpe for the column type
      // your IDE won't like the String here but don't worry, the return type the compiler expects here is String
      override def rawType = model.tpe match {
        case "java.sql.Date"               => "DateTime" // kill j.s.Timestamp
        case _ => {
          super.rawType
        }
      }
    }
  }
}

case class GenerateForms(slickDriver: String, outputFolder: String, pkg: String, schema: Option[String])

object Application extends Controller {

  val generateForm = Form(
    mapping(
      "slickDriver" -> nonEmptyText(maxLength = 100),
      "outputFolder" -> nonEmptyText(maxLength = 100),
      "pkg" -> nonEmptyText(maxLength = 100),
      "schema" -> optional(text(maxLength = 100)))(GenerateForms.apply)(GenerateForms.unapply))

  def index = Action {
    val form = GenerateForms("scala.slick.driver.MySQLDriver", "app", "models.db.common", Some("hoge"))
    Ok(views.html.generate(generateForm.fill(form)))
  }

  def generate = Action { implicit request =>
    generateForm.bindFromRequest.fold(
      hasErrors = { form =>
        Ok(views.html.generate(form))
      },
      success = { form =>
        val model = Generate.model(form.schema)
        new CustomSourceCodeGenerator(model).writeToFile(form.slickDriver, form.outputFolder, form.pkg)
        Ok(views.html.generate(generateForm.bindFromRequest))
      })
  }

}