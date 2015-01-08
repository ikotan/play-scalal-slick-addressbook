package tasks

import play.api.Play
import play.api.Play.current

object cliTask extends play.core.StaticApplication(new java.io.File(".")) {
	def main(args: Array[String]) {
		try {
			}
		finally {
				Play.stop()
		}
	}
}