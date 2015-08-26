package scm

import grails.transaction.Transactional

@Transactional
class PedidoProductoService {

    def serviceMethod() {

    }


    def crearPedido(def pedido){

      Producto producto = Producto.get(pedido.producto)

      new PedidoProducto(producto:producto,cantidad:pedido.cantidad).save()

    }


    def crearPedidoProducto(ProyectoFabricacion proyecto, Producto producto, Long cantidad){

    	PedidoProducto pedido = new PedidoProducto(producto:producto,cantidad:cantidad).save()

      if(proyecto){
        println "seteando el proyecto ${proyecto} en el pedido ${pedido}"
        pedido.setProyecto(proyecto)
        pedido.save()
      }

      return pedido
    }
}
