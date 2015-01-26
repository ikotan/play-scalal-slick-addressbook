package controllers

import play.api._
import play.api.mvc._
import models.AddressBooks
import models.db.common._
import play.api.data._
import play.api.data.Forms._
import org.joda.time._
import util.DataDumper


// ケースクラス　検索結果をString型で受け取るフォーム
case class AddressBookSearchForm(name : String)

object AddressBookSearch extends Controller {

  /**
   * フォームの定義
   */
  val addressBookSearchForm = Form(
    mapping(
      "name"             -> text(maxLength = 50)
      )(AddressBookSearchForm.apply)(AddressBookSearchForm.unapply))

  /*
   * 検索初期画面
   */
  def index = Action { implicit request =>
    Ok(views.html.addressbook.search(addressBookSearchForm)(null))
  }

  /**
   * 検索Action処理
   */
  def search = Action { implicit request =>
    /**
     *
     */
    addressBookSearchForm.bindFromRequest.fold(
        // エラー時の処理　全検索している
        hasErrors = { form =>
            val addresses = AddressBooks.find("")
            Ok(views.html.addressbook.search(addressBookSearchForm.bindFromRequest)(addresses))
        },
        // 成功時の処理
        success = { form =>
            // formのnameを受け取って検索
            val addresses = AddressBooks.find(form.name)
            
            DataDumper.dump(addresses)
            
            Ok(views.html.addressbook.search(addressBookSearchForm.bindFromRequest)(addresses))
        }
    )
  }
}
