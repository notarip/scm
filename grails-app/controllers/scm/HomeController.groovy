package scm


class HomeController {

	static allowedMethods = []

	def index() {

		def productosFList = (1..3)
		def productosMPList = (1..5)
		def productosSEList = (1..7)
		
		def productosFCount  = productosFList.size()
		def productosMPCount = productosMPList.size()
		def productosSECount = productosSEList.size()

		render view:"index", model: [productosFCount:productosFCount, productosMPCount: productosMPCount,
		productosSECount: productosSECount]
	}

}