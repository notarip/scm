package scm

class Producto {

	String nombre
	String descripcion
	List<Material> materiales = new ArrayList();

	//static transients = ['cantidadtMateriales']
	static hasMany = [materiales:Material]
	static mappedBy = [materiales: "productoPadre"]

	static constraints = {
		materiales nullable:true
    }

    static mapping = {
		materiales cascade:"all,delete-orphan"
	}
    

    def borrarMaterial(def idMaterial){

		if (idMaterial){
    		Material material = Material.get(idMaterial);
    		this.removeFromMateriales(material);
    	}
    }


    def esFinal(){

        def esFinal = false

        if (!materiales.isEmpty()){
            def esParte = Material.findAllByProducto(this).size()
            if(esParte == 0){
                esFinal = true
            }
        }

        return esFinal
    }

    def esPrimario(){

        return materiales.isEmpty()
    }

    def esSecundario(){

        return (!(materiales.isEmpty()) && !esFinal())

    }

    def getTipo(){

        if(esPrimario()){
            return "Primario"
        }else if (esSecundario()){
            return "Secundario"
        }else{
            return "Final"
        }
       
    }

    @Override
    public String toString(){
    	return "${nombre}";
    }

}