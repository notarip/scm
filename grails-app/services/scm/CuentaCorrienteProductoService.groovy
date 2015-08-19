package scm

import grails.transaction.Transactional

@Transactional
class CuentaCorrienteProductoService {

    def serviceMethod() {

    }


    def crearMovimiento(def cmd){

        def producto = Producto.get(cmd.producto)
        
        CuentaCorrienteProducto movimiento = new CuentaCorrienteProducto(
            producto:producto,
            origen:cmd.origen,
            ingreso:cmd.ingreso,
            cantidad:cmd.cantidad)
    
        movimiento.save()

        actualizarMoviemiento(cmd, movimiento)

        return movimiento

    }

    def debitarProducto(ProyectoFabricacion proyecto, Producto producto, Long cantidad){

         if (cantidad > 0){

            CuentaCorrienteProducto movimiento = new CuentaCorrienteProducto(
                producto:producto,
                origen:"Proyecto",
                ingreso:false,
                cantidad:cantidad)
        
            
            movimiento.save()

            movimiento.setProyecto(proyecto)
        }

    }

	def actualizarMoviemiento(def cmd, CuentaCorrienteProducto movimiento){

		movimiento.setOrden(OrdenFabricacion.get(cmd.orden))
		movimiento.setPunto(PuntoFabricacion.get(cmd.punto))

	}

    def obtenerDisponibilidad(Producto producto){

    	log.info "${producto}"

    	def ccprod =  CuentaCorrienteProducto.findAll("from CuentaCorrienteProducto as ccp where ccp.producto = ?",
                         [producto])

    	def disponible =0

    	ccprod.each{ mov ->

    		if(mov.ingreso)
    			disponible += mov.cantidad
    		else
    			disponible -= mov.cantidad
    	}

    	log.info disponible

    	return disponible
    }

    def obtenerMovimientos(Producto producto){

        def movimientos =  CuentaCorrienteProducto.findAll("from CuentaCorrienteProducto as ccp where ccp.producto = ?  order by ccp.fecha desc",[producto])

        return movimientos

    }


}
