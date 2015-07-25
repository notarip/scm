package scm

import grails.transaction.Transactional

@Transactional
class ProyectoFabricacionService {

    def serviceMethod() {

    }


    def crearProyecto(ProyectoFabricacion proyecto, def proyectoCmd){

/*
    	- Se  analiza que productos (secundarios, finales) no se pueden cubrir, basado en la cuenta corriente de productos.
		- Se analiza que puntos de son mas convenientes para la fabricación priorizando según la disponibilidad, los internos y la experiencia (cuántas ordenes de fabricación haya completado) 
		- Se crean los pedidos de cotización por esos productos para los puntos de fabricación seleccionados.

*/

    }
}
