import scm.*

class BootStrap {

    def init = { servletContext ->

    	println "******************* PROCESO DE HIDRATACION ****************************"

		println "Cargando Productos..."

		new Producto(nombre:"Boton N5",descripcion:"Boton de chomba N5").save()
		new Producto(nombre:"Chomba",descripcion:"Chomba Roja").save()
		new Producto(nombre:"Pantalon",descripcion:"Pantalon Rojo").save()
		new Producto(nombre:"Short",descripcion:"Short de baño Rojo").save()
		new Producto(nombre:"Saco",descripcion:"Saco Azul").save()
		new Producto(nombre:"Remera",descripcion:"Remera Roja").save()				

		new Producto(nombre:"Chomba",descripcion:"Chomba Azul").save()
		new Producto(nombre:"Pantalon",descripcion:"Pantalon Azul").save()
		new Producto(nombre:"Short",descripcion:"Short de baño Azul").save()
		new Producto(nombre:"Saco",descripcion:"Saco Azul").save()
		new Producto(nombre:"Remera",descripcion:"Remera Azul").save()				





    }
    def destroy = {
    }
}
