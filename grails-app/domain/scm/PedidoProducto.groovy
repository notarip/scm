package scm

class PedidoProducto {

	Producto producto
	Integer cantidad
	Date fecha
	Date fechaCierre
	CuentaCorrienteProducto movimiento
	ProyectoFabricacion proyecto

    static constraints = {

    	fecha nullable:true
			fechaCierre nullable:true
			proyecto nullable:true
			movimiento nullable:true

    }

  def beforeInsert() {
		if (!fecha) {
		   fecha = new Date()
		}
	}

	def cerrarPedido(){

		fechaCierre = new Date()

	}

	@Override
	public String toString(){
		return "${producto} - ${cantidad} - ${proyecto}";
	}
}
