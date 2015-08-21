package scm

class PedidoProducto {

	ProyectoFabricacion proyecto
	Producto producto 
	Integer cantidad
	Date fecha

    static constraints = {
    	
    	fecha nullable:true
    		
    }

    def beforeInsert() {
	    if (!fecha) {
	       fecha = new Date()
	    }
	}

}
