package scm

class Material {

	Producto productoPadre;
	Producto producto;
	Integer cantidad;
    EtapaFabricacion etapaFabricacion
	boolean _deleted;

	static transients = [ '_deleted' ]

	static belongsTo = [productoPadre: Producto]

    static constraints = {

    }


    Material(def materialCmd){

        Producto producto = Producto.get(materialCmd.idProducto);
        EtapaFabricacion etapa = EtapaFabricacion.get(materialCmd.idEtapa)

    	this.producto = producto;
    	this.cantidad = materialCmd.cantidad;
        this.etapaFabricacion = etapa;

        log.info this.toString()
    }


    @Override
    public String toString(){

    	return "${producto} - ${cantidad} - ${etapaFabricacion}";
    }
}
