package scm


class HomeController {

    ProductoService productoService
    PuntoFabricacionService puntoFabricacionService

	static allowedMethods = []

	def index() {

		def prodFinales  = productoService.cantidadFinales()
		def prodPrimarios = productoService.cantidadPrimarios()
		def prodSemiElab = productoService.cantidadSecundarios()

		def puntosInternos = puntoFabricacionService.cantidadInternos()
		def puntosExternos = puntoFabricacionService.cantidadExternos()


		render view:"index", model: [
		productosFCount:prodFinales, 
		productosMPCount: prodPrimarios,
		productosSECount: prodSemiElab,
		puntosInternos: puntosInternos,
		puntosExternos:puntosExternos]
	}

}