package scm

class Producto {

	String nombre
	String descripcion
	List<Material> materiales = new ArrayList();
	static hasMany = [materiales:Material]

	static mappedBy = [materiales: "productoPadre"]

	static constraints = {
		materiales nullable:true
    }

    static mapping = {
		materiales cascade:"all,delete-orphan"
	}

	def getEsFinal(){
		return materiales.isEmpty()
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

}
