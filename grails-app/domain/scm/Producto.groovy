package scm

class Producto {

	String nombre
	String descripcion
	List<Material> materiales = new ArrayList();
	Integer  cantidadtMateriales = materiales.size()
	
	static transients = ['cantidadtMateriales']

	static hasMany = [materiales:Material]

	static mappedBy = [materiales: "productoPadre"]

	static constraints = {
		materiales nullable:true
    }

    static mapping = {
		materiales cascade:"all,delete-orphan"
	}
    
    def getMaterialesList() {
        return LazyList.decorate(materiales,FactoryUtils.instantiateFactory(Material.class))
    }

    def borrarMaterial(def idMaterial){

		if (idMaterial){
    		Material material = Material.get(idMaterial);
    		this.removeFromMateriales(material);
    	}

    }

    @Override
    public String toString(){

    	return "${nombre}";
    }

}
