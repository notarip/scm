package scm


class HomeController {

	static allowedMethods = []

	def index() {
		def productosFinales = Producto.countByEsFinal(true)
		render view:"index", model: [productosFinales: productosFinales]
	}

}