package scm

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ProyectoFabricacion)
class ProyectoFabricacionSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }



    //Functional Test
    def "el jefe de suministros crea un nuevo proyecto de fabricacion"(){

    	given: "el producto que se va a fabricar existe y tiene cargadas todas sus etapas de fabricación 
    			y hay disponibilidad de todos los artículos necesarios"

    	when: "El jefe de suministros crea un nuevo proyecto de fabricación, especificando el artículo y la cantidad"


    	then: "Se crea el proyecto de fabricación, Se crean los pedidos de cotización y se envían a los talleres, Se bloquean los artículos que se van a utilizar para el proyecto"


    }
}
