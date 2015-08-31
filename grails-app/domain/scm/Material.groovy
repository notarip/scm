package scm

class Material {

	Producto productoPadre;
	Producto producto;
	Integer cantidad;
	boolean _deleted;

	static transients = [ '_deleted' ]

	static belongsTo = [productoPadre: Producto]

    static constraints = {

    }


    Material(def materialCmd){

			Producto producto = Producto.get(materialCmd.idProducto);

			this.producto = producto;
			this.cantidad = materialCmd.cantidad;

    }


    @Override
    public String toString(){

    	return "${producto} - ${cantidad}";
    }
}
