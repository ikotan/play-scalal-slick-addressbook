package models

import play.api.db.DB
import play.api.Play.current
import scala.slick.driver.MySQLDriver.simple._
import java.sql.Date
import models.db.common.Tables._

object AddressBooks {

  /** データベースコネクション */
  val database = Database.forDataSource(DB.getDataSource())

  def findById(id : Int) = database.withTransaction { implicit session  :Session =>
    Addresses.filter(_.id === id).first
  }

  def find(name : String) = database.withTransaction { implicit session  :Session =>
    var q = Addresses.sortBy(_.name)
    q = if (name.isEmpty) q else q.filter(_.name like s"%${name}%")
    q.invoker.list
  }

  /** 登録 */
  def create(e: AddressesRow) = database.withTransaction { implicit session: Session =>
    Addresses.insert(e)
  }

  /** 更新 */
  def update(e: AddressesRow) = database.withTransaction { implicit session: Session =>
    Addresses.filter(_.id === e.id).update(e)
  }

  def delete(id : Int) = database.withTransaction { implicit session: Session =>
    Addresses.filter(_.id === id).delete
  }
}

