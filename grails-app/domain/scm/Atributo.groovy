package scm

class Atributo {

	AtributoTipo tipo
	UnidadMedida unidad
	String value

    static constraints = {

    	unidad validator: {
	  		if (!tipo.unidadesMedida.containsValue(unidad)) return ['unidadNoSoportada']
       	}
   	
    }

}
