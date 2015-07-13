import scm.*

class BootStrap {

    def init = { servletContext ->

    	println "******************* PROCESO DE HIDRATACION ****************************"

		println "Cargando Etapas..."

		def corte = new EtapaFabricacion(nombre:"Corte").save()
		def costura = new EtapaFabricacion(nombre:"Costura").save()
		def pegado = new EtapaFabricacion(nombre:"Pegado").save()
		def lavado = new EtapaFabricacion(nombre:"Lavado").save()

		println "Cargando Productos Primarios..."
		
		def boton1 = new Producto(nombre:"B001 - Boton N5",descripcion:"Boton de camisa N5").save()
		def tela1 = new Producto(nombre:"T001 - Tela Camisa ",descripcion:"Tela para camisa con lineas grises").save()
		def hilo1 = new Producto(nombre:"H001 - Hilo Negro ",descripcion:"Hilo negro").save()

		println "Cargando Productos Secundarios..."

		def punio1 = new Producto(nombre:"P001 - Puño T1",descripcion:"Puño T").save()
		punio1.addToMateriales(new Material(producto:tela1,cantidad:10, etapaFabricacion: costura))
		punio1.addToMateriales(new Material(producto:hilo1,cantidad:1000, etapaFabricacion: costura))
		punio1.save()

		println "Cargando Productos Secundarios I..."

		def cuello1 = new Producto(nombre:"C001 - Cuello T1",descripcion:"Cuello T1").save()
		cuello1.addToMateriales(new Material(producto:tela1,cantidad:5, etapaFabricacion: costura))
		cuello1.addToMateriales(new Material(producto:hilo1,cantidad:500, etapaFabricacion: costura))
		cuello1.save()

		def pechera1 = new Producto(nombre:"PE001 - Pechera T1",descripcion:"Pechera T1").save()
		pechera1.addToMateriales(new Material(producto:tela1,cantidad:20, etapaFabricacion: corte))
		pechera1.save()

		def espalda1 = new Producto(nombre:"E001 - Espalda T1",descripcion:"Espalda T1").save()
		espalda1.addToMateriales(new Material(producto:tela1,cantidad:20, etapaFabricacion: corte))
		espalda1.save()

		def manga1 = new Producto(nombre:"MA001 - Manga T1",descripcion:"Manga T1").save()
		manga1.addToMateriales(new Material(producto:tela1,cantidad:20, etapaFabricacion: corte))
		manga1.save()

		println "Cargando Productos Finales..."	

		def camisa1 = new Producto(nombre:"CA001 - Camisa",descripcion:"Camisa T1").save()
		camisa1.addToMateriales(new Material(producto:punio1,cantidad:2, etapaFabricacion: costura))
		camisa1.addToMateriales(new Material(producto:cuello1,cantidad:1, etapaFabricacion: costura))
		camisa1.addToMateriales(new Material(producto:pechera1,cantidad:1, etapaFabricacion: costura))
		camisa1.addToMateriales(new Material(producto:espalda1,cantidad:1, etapaFabricacion: costura))
		camisa1.addToMateriales(new Material(producto:manga1,cantidad:2, etapaFabricacion: costura))
		camisa1.save()




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
