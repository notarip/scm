package scm

class Pieza {

	Articulo articuloPadre;
	Articulo articulo;
	Integer cantidad;
	boolean _deleted;

	static transients = [ '_deleted' ]

	static belongsTo = [articuloPadre: Articulo]

    static constraints = {

    }


    Pieza(def piezaCmd){

        Articulo articulo = Articulo.get(piezaCmd.idArticulo);

    	this.articulo = articulo;
    	this.cantidad = piezaCmd.cantidad;
    }


    @Override
    public String toString(){

    	return "${articulo} - ${cantidad}";
    }
}
