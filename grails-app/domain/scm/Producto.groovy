package scm

class Producto {

	String nombre
	String descripcion
	Categoria categoria
	List<Material> materiales = new ArrayList();

	static hasMany = [materiales:Material]
	static mappedBy = [materiales: "productoPadre"]

	static constraints = {
		materiales nullable:true
		categoria nullable:true
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


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
/*
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Producto other = (Producto) obj;
        if (id != other.id)
            return false;
        return true;
    }
*/
}
