import scm.*

class BootStrap {

    def init = { servletContext ->

    	log.info "******************* PROCESO DE HIDRATACION ****************************"

		log.info "Cargando Etapas..."

		def corte = new EtapaFabricacion(nombre:"Corte").save()
		def costura = new EtapaFabricacion(nombre:"Costura").save()
		def pegado = new EtapaFabricacion(nombre:"Pegado").save()
		def lavado = new EtapaFabricacion(nombre:"Lavado").save()

		log.info "Cargando Puntos de Fabricacion..."

		def punto1 = new PuntoFabricacion(nombre:"Taller 1", interno:new Boolean(false)).save()
		punto1.addToEtapas(corte)
		punto1.addToEtapas(costura)
		punto1.save()

		def punto2 = new PuntoFabricacion(nombre:"Taller 2", interno:new Boolean(false)).save()
		punto2.addToEtapas(pegado)
		punto2.save()

		def punto3 = new PuntoFabricacion(nombre:"Puesto 1", interno:new Boolean(true)).save()
		punto3.addToEtapas(lavado)
		punto3.save()



		log.info "Cargando Productos Primarios..."
		
		def boton1 = new Producto(nombre:"B001 - Boton N5",descripcion:"Boton de camisa N5").save()
		def boton2 = new Producto(nombre:"B002 - Boton N3",descripcion:"Boton N3").save()
		def tela1 = new Producto(nombre:"T001 - Tela Camisa ",descripcion:"Tela para camisa con lineas grises").save()
		def tela2 = new Producto(nombre:"T002 - Tela Pique ",descripcion:"Tela Pique Azul").save()
		def tela3 = new Producto(nombre:"T003 - Tela Pique Elastica ",descripcion:"Tela Pique Azul Elastica").save()
		def hilo1 = new Producto(nombre:"H001 - Hilo Negro ",descripcion:"Hilo negro").save()

		log.info "Cargando Productos Secundarios..."

		def punio1 = new Producto(nombre:"P001 - Puño T1",descripcion:"Puño T").save()
		punio1.addToMateriales(new Material(producto:tela1,cantidad:10, etapaFabricacion: costura))
		punio1.addToMateriales(new Material(producto:hilo1,cantidad:1000, etapaFabricacion: costura))
		punio1.save()

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

		def manga2 = new Producto(nombre:"MA001 - Manga Chomba T1",descripcion:"Manga T1").save()
		manga2.addToMateriales(new Material(producto:tela2,cantidad:20, etapaFabricacion: corte))
		manga2.save()

		def cuello2 = new Producto(nombre:"C002 - Cuello Chomba T1",descripcion:"Cuello Chomba T1").save()
		cuello2.addToMateriales(new Material(producto:tela2,cantidad:5, etapaFabricacion: costura))
		cuello2.addToMateriales(new Material(producto:hilo1,cantidad:500, etapaFabricacion: costura))
		cuello2.save()

		def pechera2 = new Producto(nombre:"PE002 - Pechera Chomba T1",descripcion:"Pechera T1").save()
		pechera2.addToMateriales(new Material(producto:tela2,cantidad:20, etapaFabricacion: corte))
		pechera2.save()

		def espalda2 = new Producto(nombre:"E002 - Espalda Chomba T1",descripcion:"Espalda T1").save()
		espalda2.addToMateriales(new Material(producto:tela2,cantidad:20, etapaFabricacion: corte))
		espalda2.save()


		log.info "Cargando Productos Finales..."	

		def camisa1 = new Producto(nombre:"CA001 - Camisa",descripcion:"Camisa T1").save()
		camisa1.addToMateriales(new Material(producto:punio1,cantidad:2, etapaFabricacion: costura))
		camisa1.addToMateriales(new Material(producto:cuello1,cantidad:1, etapaFabricacion: costura))
		camisa1.addToMateriales(new Material(producto:pechera1,cantidad:1, etapaFabricacion: costura))
		camisa1.addToMateriales(new Material(producto:espalda1,cantidad:1, etapaFabricacion: costura))
		camisa1.addToMateriales(new Material(producto:manga1,cantidad:2, etapaFabricacion: costura))
		camisa1.addToMateriales(new Material(producto:boton1,cantidad:12, etapaFabricacion: costura))
		camisa1.save()

		def chomba1 = new Producto(nombre:"CH001 - Chomba",descripcion:"Chomba Azul T1").save()
		chomba1.addToMateriales(new Material(producto:manga2,cantidad:2, etapaFabricacion: costura))
		chomba1.addToMateriales(new Material(producto:cuello2,cantidad:2, etapaFabricacion: costura))
		chomba1.addToMateriales(new Material(producto:pechera2,cantidad:2, etapaFabricacion: costura))
		chomba1.addToMateriales(new Material(producto:espalda2,cantidad:2, etapaFabricacion: costura))
		chomba1.addToMateriales(new Material(producto:boton2,cantidad:4, etapaFabricacion: costura))
		chomba1.save()


		def p1 = new Producto(nombre:"Pantalon",descripcion:"Pantalon Rojo").save()
		def p2 = new Producto(nombre:"Short",descripcion:"Short de baño Rojo").save()
		def p3 = new Producto(nombre:"Saco",descripcion:"Saco Azul").save()
		def p4 = new Producto(nombre:"Remera",descripcion:"Remera Roja").save()				

		def p5 = new Producto(nombre:"Chomba",descripcion:"Chomba Azul").save()
		def p6 = new Producto(nombre:"Pantalon",descripcion:"Pantalon Azul").save()
		def p7 = new Producto(nombre:"Short",descripcion:"Short de baño Azul").save()
		def p8 = new Producto(nombre:"Saco",descripcion:"Saco Azul").save()
		def p9 = new Producto(nombre:"Remera",descripcion:"Remera Azul").save()				

		log.info "Cargando en la Cuenta Corriente..."

		//cuello1 ,pechera1,espalda1,manga1,manga2 ,cuello2,pechera2,espalda2

		new CuentaCorrienteProducto(producto:boton1,ingreso:true,origen:"Stock",cantidad:1000).save()
		new CuentaCorrienteProducto(producto:boton2,ingreso:true,origen:"Stock",cantidad:1000).save()
		new CuentaCorrienteProducto(producto:tela1,ingreso:true,origen:"Stock",cantidad:4000).save()
		new CuentaCorrienteProducto(producto:tela2,ingreso:true,origen:"Stock",cantidad:4000).save()
		new CuentaCorrienteProducto(producto:tela3,ingreso:true,origen:"Stock",cantidad:4000).save()
		new CuentaCorrienteProducto(producto:hilo1,ingreso:true,origen:"Stock",cantidad:4000).save()
		


		new CuentaCorrienteProducto(producto:p1,ingreso:true,origen:"Orden",cantidad:10).save()
		new CuentaCorrienteProducto(producto:p2,ingreso:true,origen:"Orden",cantidad:20).save()
		new CuentaCorrienteProducto(producto:p3,ingreso:true,origen:"Orden",cantidad:30).save()
		new CuentaCorrienteProducto(producto:p4,ingreso:true,origen:"Orden",cantidad:40).save()
		new CuentaCorrienteProducto(producto:p5,ingreso:true,origen:"Orden",cantidad:10).save()
		new CuentaCorrienteProducto(producto:p6,ingreso:true,origen:"Orden",cantidad:20).save()
		new CuentaCorrienteProducto(producto:p7,ingreso:true,origen:"Orden",cantidad:30).save()
		new CuentaCorrienteProducto(producto:p8,ingreso:true,origen:"Orden",cantidad:40).save()
		new CuentaCorrienteProducto(producto:p9,ingreso:true,origen:"Orden",cantidad:50).save()

		new CuentaCorrienteProducto(producto:p2,ingreso:false,origen:"Stock",cantidad:15).save()
		new CuentaCorrienteProducto(producto:p1,ingreso:false,origen:"Stock",cantidad:15).save()
		new CuentaCorrienteProducto(producto:p3,ingreso:false,origen:"Stock",cantidad:15).save()
		new CuentaCorrienteProducto(producto:p4,ingreso:false,origen:"Stock",cantidad:15).save()
		new CuentaCorrienteProducto(producto:p5,ingreso:false,origen:"Stock",cantidad:15).save()
		new CuentaCorrienteProducto(producto:p6,ingreso:false,origen:"Stock",cantidad:15).save()
		new CuentaCorrienteProducto(producto:p7,ingreso:false,origen:"Stock",cantidad:15).save()
		new CuentaCorrienteProducto(producto:p8,ingreso:false,origen:"Stock",cantidad:15).save()
		new CuentaCorrienteProducto(producto:p9,ingreso:false,origen:"Stock",cantidad:15).save()
		




    }
    def destroy = {
    }
}
