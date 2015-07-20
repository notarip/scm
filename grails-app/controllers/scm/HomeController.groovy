package scm


class HomeController {

    ProductoService productoService

	static allowedMethods = []

	def index() {

		def prodFinales  = productoService.cantidadFinales()
		def prodPrimarios = productoService.cantidadPrimarios()
		def prodSemiElab = productoService.cantidadSecundarios()

		def puntosPropios = 10
		def puntosExternos = 50


		render view:"index", model: [
		productosFCount:prodFinales, 
		productosMPCount: prodPrimarios,
		productosSECount: prodSemiElab,
		puntosPropios: puntosPropios,
		puntosExternos:puntosExternos]
	}

}