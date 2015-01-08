package controllers

import play.api._
import play.api.mvc._
import models.AddressBooks
import models.db.common._
import play.api.data._
import play.api.data.Forms._
import org.joda.time._
import java.sql.Timestamp

case class AddressBookForm(
    name : String,
    nameInKana : String,
    telephoneNumber : String,
    zipCode : String,
    address : String,
    birthday : DateTime
)

object AddressBook extends Controller {

  val addressBookForm = Form(
    mapping(
      "name"             -> nonEmptyText(maxLength = 50),
      "name_in_kana"     -> nonEmptyText(maxLength = 50),
      "telephone_number" -> nonEmptyText(maxLength = 50),
      "zip_code"         -> nonEmptyText(maxLength = 50),
      "address"          -> nonEmptyText(maxLength = 500),
      "birthday"         -> jodaDate
      )(AddressBookForm.apply)(AddressBookForm.unapply))

  def index = Action { implicit request =>
    Ok(views.html.addressbook.create(addressBookForm))
  }

  /**
   * 新規登録
   */
  def create = Action { implicit request =>
    addressBookForm.bindFromRequest.fold(
      hasErrors = { form =>
        Ok(views.html.addressbook.create(form))
      },
      success = { form =>
        val addr = Tables.AddressesRow(
            id              = 0,
            name            = form.name,
            nameInKana      = form.nameInKana,
            telephoneNumber = form.telephoneNumber,
            zipCode         = form.zipCode,
            address         = form.address,
            birthday        = form.birthday,
            updateDate      = new Timestamp(System.currentTimeMillis()),
            createDate      = new Timestamp(System.currentTimeMillis())
        )
        AddressBooks.create(addr)
        Redirect(controllers.routes.AddressBook.create).flashing(
          "success" -> "ユーザの追加を行いました。"
        )

      })
  }

  def delete(id : Int) = Action { implicit request =>
    AddressBooks.delete(id)
    Redirect(controllers.routes.AddressBookSearch.index.url, Map("name" -> Seq(""))).flashing(
        "success" -> s"ID : ${id}のデータの削除を行いました。"
    )
  }

  /**
   * 編集
   */
  def edit(id : Int) = Action { implicit request =>
    val address = AddressBooks.findById(id)
    val form = AddressBookForm(
        address.name,
        address.nameInKana,
        address.telephoneNumber,
        address.zipCode,
        address.address,
        address.birthday
        )
    Ok(views.html.addressbook.edit(addressBookForm.fill(form))(id))
  }

  /**
   * 更新
   */
  def update(id : Int) = Action { implicit request =>
    addressBookForm.bindFromRequest.fold(
      hasErrors = { form =>
        Ok(views.html.addressbook.edit(form)(id))
      },
      success = { form =>
        val addr = Tables.AddressesRow(
            id              = id,
            name            = form.name,
            nameInKana      = form.nameInKana,
            telephoneNumber = form.telephoneNumber,
            zipCode         = form.zipCode,
            address         = form.address,
            birthday        = form.birthday,
            updateDate      = new Timestamp(System.currentTimeMillis()),
            createDate      = new Timestamp(System.currentTimeMillis())
        )
        AddressBooks.update(addr)
        Redirect(controllers.routes.AddressBook.edit(id)).flashing(
          "success" -> "ユーザの更新を行いました。"
        )

      })
  }
}
