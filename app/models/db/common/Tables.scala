package models.db.common
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = scala.slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: scala.slick.driver.JdbcProfile
  import profile.simple._
  import com.github.tototoshi.slick.PostgresJodaSupport._
  import org.joda.time.DateTime
  import scala.slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import scala.slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val ddl = Addresses.ddl ++ Test.ddl

  /** Entity class storing rows of table Addresses
   *  @param id Database column id AutoInc, PrimaryKey
   *  @param name Database column name
   *  @param nameInKana Database column name_in_kana
   *  @param telephoneNumber Database column telephone_number
   *  @param zipCode Database column zip_code
   *  @param address Database column address
   *  @param birthday Database column birthday
   *  @param updateDate Database column update_date
   *  @param createDate Database column create_date  */
  case class AddressesRow(id: Int, name: String, nameInKana: String, telephoneNumber: String, zipCode: String, address: String, birthday: DateTime, updateDate: java.sql.Timestamp, createDate: java.sql.Timestamp)

  /** GetResult implicit for fetching AddressesRow objects using plain SQL queries */
  implicit def GetResultAddressesRow(implicit e0: GR[Int], e1: GR[String], e2: GR[DateTime], e3: GR[java.sql.Timestamp]): GR[AddressesRow] = GR{
    prs => import prs._
    AddressesRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<[String], <<[String], <<[DateTime], <<[java.sql.Timestamp], <<[java.sql.Timestamp]))
  }
  /** Table description of table addresses. Objects of this class serve as prototypes for rows in queries. */
  class Addresses(_tableTag: Tag) extends Table[AddressesRow](_tableTag, "addresses") {
    def * = (id, name, nameInKana, telephoneNumber, zipCode, address, birthday, updateDate, createDate) <> (AddressesRow.tupled, AddressesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (id.?, name.?, nameInKana.?, telephoneNumber.?, zipCode.?, address.?, birthday.?, updateDate.?, createDate.?).shaped.<>({r=>import r._; _1.map(_=> AddressesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get, _8.get, _9.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id AutoInc, PrimaryKey */
    val id: Column[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column name  */
    val name: Column[String] = column[String]("name")
    /** Database column name_in_kana  */
    val nameInKana: Column[String] = column[String]("name_in_kana")
    /** Database column telephone_number  */
    val telephoneNumber: Column[String] = column[String]("telephone_number")
    /** Database column zip_code  */
    val zipCode: Column[String] = column[String]("zip_code")
    /** Database column address  */
    val address: Column[String] = column[String]("address")
    /** Database column birthday  */
    val birthday: Column[DateTime] = column[DateTime]("birthday")
    /** Database column update_date  */
    val updateDate: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("update_date")
    /** Database column create_date  */
    val createDate: Column[java.sql.Timestamp] = column[java.sql.Timestamp]("create_date")
  }
  /** Collection-like TableQuery object for table Addresses */
  lazy val Addresses = new TableQuery(tag => new Addresses(tag))

  /** Entity class storing rows of table Test
   *  @param id Database column id
   *  @param name Database column name  */
  case class TestRow(id: Option[Int], name: Option[String])
  /** GetResult implicit for fetching TestRow objects using plain SQL queries */
  implicit def GetResultTestRow(implicit e0: GR[Option[Int]], e1: GR[Option[String]]): GR[TestRow] = GR{
    prs => import prs._
    TestRow.tupled((<<?[Int], <<?[String]))
  }
  /** Table description of table test. Objects of this class serve as prototypes for rows in queries. */
  class Test(_tableTag: Tag) extends Table[TestRow](_tableTag, "test") {
    def * = (id, name) <> (TestRow.tupled, TestRow.unapply)

    /** Database column id  */
    val id: Column[Option[Int]] = column[Option[Int]]("id")
    /** Database column name  */
    val name: Column[Option[String]] = column[Option[String]]("name")
  }
  /** Collection-like TableQuery object for table Test */
  lazy val Test = new TableQuery(tag => new Test(tag))
}