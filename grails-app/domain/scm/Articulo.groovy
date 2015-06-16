package scm

class Articulo {

	String nombre
	String descripcion
	static hasMany = [atributos:Atributo]
	
    static constraints = {
		atributos nullable:true
    }
}
