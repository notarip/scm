package scm

class PuntoFabricacion {

	String nombre
	String observaciones
	Boolean interno
	Integer distancia

	static hasMany = [categorias:Categoria]

	static belongsTo = Categoria

  static constraints = {
  	observaciones nullable:true
		distancia nullable:true
  }


  def getTipo(){

        if(interno){
            return "Interno"
        }else{
            return "Externo"
        }
  }

  @Override
  public String toString(){
    return "${nombre}";
  }

}
