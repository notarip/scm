package scm

class Categoria {


    String nombre 
    Float costoFabricacion = 0
    static hasMany = [productos:Producto, puntos:PuntoFabricacion]


    static constraints = {
    	costoFabricacion nullable:true
    }


    @Override
    public String toString(){

    	return "${nombre}";
    }
}
