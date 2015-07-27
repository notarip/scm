package scm

class CuentaCorrienteProducto {

	Producto producto
	Boolean ingreso
	String origen
    ProyectoFabricacion proyecto
	OrdenFabricacion orden
	PuntoFabricacion punto
	Integer cantidad
	Date fecha


	 def beforeInsert() {
	      if (!fecha) {
	         fecha = new Date()
	      }
	   }

    static constraints = {
    		origen inList: ["Orden", "Stock"]
    		orden nullable:true
    		punto nullable:true
            fecha nullable:true
            proyecto nullable:true
    }


    def getCantidadConSigno(){

    	if(ingreso)
    		return (cantidad)
    	else
    		return (cantidad*-1)
    }

    def getTipo(){

    	if(ingreso)
    		return "Ingreso"
    	else
    		return "Egreso"
    }

}
