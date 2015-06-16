package scm

class Atributo {

	AtributoTipo tipo
	UnidadMedida unidad
	String valor

    static constraints = {

/*
    	unidad validator: {
	  		if (!tipo.unidadesMedida.containsValue(unidad)) return ['unidadNoSoportada']
       	}
       	*/
   	
    }

}
