package scm

import grails.transaction.Transactional

@Transactional
class PuntoFabricacionService {

    def serviceMethod() {

    }

    def cantidadInternos(){

        def list = PuntoFabricacion.findAll()
        def cantidad = 0 
        list.each{ punto ->
            if (punto.getInterno()){
                cantidad++
            }
        }
        return cantidad
    }

    def cantidadExternos(){

        def list = PuntoFabricacion.findAll()
        def cantidad = 0 
        list.each{ punto ->
            if (!punto.getInterno()){
                cantidad++
            }
        }
        return cantidad
    }



    def actualizarPuntoFabricacion(PuntoFabricacion punto, def puntoCmd){


    	punto.setNombre(puntoCmd.nombre)
    	punto.setObservaciones(puntoCmd.observaciones)
    	punto.setInterno(puntoCmd.interno)

    	actualizarEtapas(punto, puntoCmd)
    }


    def actualizarEtapas(PuntoFabricacion punto, def puntoCmd){


		def etapasNuevas = puntoCmd.etapas.findAll{ !(it._deleted) }
        def etapasBorradas = puntoCmd.etapas.findAll{ it._deleted }
        
        etapasBorradas.each{ log.info "etapas borradas: ${it}"}
        etapasBorradas.each{ etapa -> punto.etapas.remove(EtapaFabricacion.get(etapa.id)) }

        etapasNuevas.each{etapa -> 

            log.info "etapa nueva: ${etapa}";
            def e = EtapaFabricacion.get(etapa.id)

            if(!punto.etapas.contains(e)){
            	punto.addToEtapas(e);
            }

           }
    }

}