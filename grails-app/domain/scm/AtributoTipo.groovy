package scm

class AtributoTipo {

	String nombre
	static hasMany = [unidadesMedida:UnidadMedida]
	


    static constraints = {
    	unidadesMedida nullable:true
    }
}
