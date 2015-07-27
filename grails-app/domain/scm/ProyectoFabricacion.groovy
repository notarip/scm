package scm

class ProyectoFabricacion {


	String nombre
	Producto producto
	Integer cantidad
	Date fecha
	List<OrdenFabricacion> ordenes = new ArrayList();
	List<PedidoProducto> pedidosProductos = new ArrayList();
	List<PedidoCotizacion> pedidosCotizacion = new ArrayList();
	

	static hasMany = [ordenes: OrdenFabricacion, pedidosProductos:PedidoProducto, pedidosCotizacion:PedidoCotizacion]

    static constraints = {
    	ordenes nullable:true
    	pedidosProductos nullable:true
    	pedidosCotizacion nullable:true
    }

    

    

}
