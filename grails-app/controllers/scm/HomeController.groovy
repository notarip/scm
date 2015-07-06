package scm


class HomeController {

    ProductoService productoService

	static allowedMethods = []

	def index() {

		def productosFList = (1..3)
		def productosMPList = (1..5)
		def productosSEList = (1..7)
		
		def prodFinales  = productoService.cantidadFinales()
		def prodPrimarios = productoService.cantidadPrimarios()
		def prodSemiElab = productoService.cantidadSecundarios()

		render view:"index", model: [productosFCount:prodFinales, productosMPCount: prodPrimarios,
		productosSECount: prodSemiElab]
	}

}