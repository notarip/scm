package scm

class PuntoFabricacion {

	String nombre
	String observaciones
	Boolean interno
	List<EtapaFabricacion> etapas = new ArrayList();
	static hasMany = [etapas:EtapaFabricacion]

    static constraints = {
    	observaciones nullable:true
    }


    def getTipo(){

        if(interno){
            return "Interno"
        }else{
            return "Externo"
        }
       
    }
}
