package scm

import grails.transaction.Transactional

@Transactional
class PedidoProductoService {

    def serviceMethod() {

    }



    def crearPedidoProducto(ProyectoFabricacion proyecto, Producto producto, Long cantidad){

    	new PedidoProducto(proyecto:proyecto,producto:producto,cantidad:cantidad).save()

    }
}
