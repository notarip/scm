package scm

class PedidoCotizacion {

    ProyectoFabricacion proyecto
    PuntoFabricacion punto
    Producto producto
    Integer cantidad
    Float costoUnitarioPrevisto = 0
    Float costoUnitarioEstimado = 0

    //se envian desde el proyecto
    //Date fechaEstimadaInicio
    //Date fechaEstimadaEntrega

    //completa el punto
    //Date fechaPosibleInicio
    //Date fechaPosibleEntrega
    

    static constraints = {
      
      costoUnitarioPrevisto nullable:true
      costoUnitarioEstimado nullable:true
      //fechaEstimadaInicio nullable:true
      //fechaEstimadaEntrega nullable:true
      //fechaPosibleInicio nullable:true
      //fechaPosibleEntrega nullable:true
      
    }

    //TODO-SCM ver como calcular bien el costo de traslado
    def getCostoTraslado(){
      return (punto.getDistancia()*0.1)
    }

    public Float getVarianza(){

      Float varianza = 0

      if(costoUnitarioEstimado != 0 && costoUnitarioPrevisto != 0){

        varianza = costoUnitarioEstimado/costoUnitarioPrevisto*100 - 100

      }

      return varianza
    }

    @Override
    public String toString(){
      return "${punto}";
    }

}
