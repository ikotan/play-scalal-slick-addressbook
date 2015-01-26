package util

import play.api.Logger
import scala.runtime.ScalaRunTime._
import play.api.Logger

object DataDumper {
  // case of Array print
  // println(stringOf( Array(1,2,3) ))  // => Array(1, 2, 3)
  def dump[T](t: T)(implicit mt: Manifest[T] = null) {
    Logger.debug("%s: %s".format(t, if (mt == null) "<?>" else mt.toString))
  }
}

