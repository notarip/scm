package scm

import grails.transaction.Transactional

@Transactional
class PedidoProductoService {

    def serviceMethod() {

    }


    def obtenerPedidos(ProyectoFabricacion proyecto){


      return PedidoProducto.findAll("from PedidoProducto as pp where pp.proyecto = ?",
                       [proyecto])
    }

    def crearPedido(def pedido){

      Producto producto = Producto.get(pedido.producto)

      new PedidoProducto(producto:producto,cantidad:pedido.cantidad).save()

    }


    def crearPedidoProducto(ProyectoFabricacion proyecto, Producto producto, Long cantidad){


    	PedidoProducto pedido = new PedidoProducto(producto:producto,cantidad:cantidad).save()

      if(proyecto){
        pedido.setProyecto(proyecto)
      }

      return pedido
    }
}
