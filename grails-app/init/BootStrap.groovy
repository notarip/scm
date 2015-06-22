
import scm.*

class BootStrap {

    def init = { servletContext ->

    	println "******************* PROCESO DE HIDRATACION ****************************"

		println "Cargando Articulos..."

		new Articulo(nombre:"Boton N5",descripcion:"Boton de chomba N5",).save()
		new Articulo(nombre:"Chomba",descripcion:"Chomba Roja",).save()




    }
    def destroy = {
    }
}
