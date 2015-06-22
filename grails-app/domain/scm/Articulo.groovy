package scm

class Articulo {

	String nombre
	String descripcion
	List piezas = new ArrayList();
	static hasMany = [piezas:Pieza]

	static mappedBy = [piezas: "articuloPadre"]

	static constraints = {
		piezas nullable:true
    }

    static mapping = {
		piezas cascade:"all,delete-orphan"
	}
    
    def getPiezasList() {
        return LazyList.decorate(piezas,FactoryUtils.instantiateFactory(Pieza.class))
    }

    @Override
    public String toString(){

    	return nombre;
    }
}
