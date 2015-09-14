package scm

import grails.transaction.Transactional

@Transactional
class PedidoCotizacionService {

	def obtenerPedidos(ProyectoFabricacion proyecto){

      return PedidoCotizacion.findAll("from PedidoCotizacion as pc where pc.proyecto = ?",
                       [proyecto])
   }
   
}
