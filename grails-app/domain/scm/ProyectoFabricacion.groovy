package scm


class ProyectoFabricacion {


	String nombre
	Producto producto
	Integer cantidad
	Date fecha
	List<OrdenFabricacion> ordenes = new ArrayList();
	List<PedidoCotizacion> pedidosCotizacion = new ArrayList();


	static hasMany = [ordenes: OrdenFabricacion, pedidosCotizacion:PedidoCotizacion]

    static constraints = {
    	ordenes nullable:true
    	pedidosCotizacion nullable:true
    }


	@Override
	public String toString(){
		return "${nombre}";
	}


}
