package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.transform.ToString

@ToString(includeNames=true)
class PedidoCmd {
    Integer id
    Integer producto
    Integer cantidad
}


@Transactional(readOnly = true)
class PedidoProductoController {

    PedidoProductoService pedidoProductoService
    ProductoService productoService
    CuentaCorrienteProductoService cuentaCorrienteProductoService


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = "fecha"
        params.order = "desc"

        def pedidoProductoList = PedidoProducto.createCriteria().list (params) {
            if ( params.query ) {
                ilike("producto.nombre", "%${params.query}%")
            }
        }

        [pedidoProductoList:pedidoProductoList, pedidoProductoCount: PedidoProducto.count()]
    }

    def show(PedidoProducto pedidoProducto) {
        respond pedidoProducto
    }

    @Transactional
    def close(PedidoProducto pedidoProducto) {

        def msgcode = "default.updated.message"

        if(!pedidoProducto.fechaCierre){

          CuentaCorrienteProducto movIngreso = cuentaCorrienteProductoService.crearMovimiento(pedidoProducto.producto, pedidoProducto.cantidad, pedidoProducto.proyecto, true)

          movIngreso.save()
          pedidoProducto.cerrarPedido()
          pedidoProducto.setMovimiento(movIngreso)
          pedidoProducto.save()


          CuentaCorrienteProducto movEgreso = cuentaCorrienteProductoService.crearMovimiento(pedidoProducto.producto, pedidoProducto.cantidad, pedidoProducto.proyecto, false)
          movEgreso.save()

       }else{
         msgcode = "default.not.updated.message"
       }


        request.withFormat {
            form multipartForm {
                flash.message = message(code: msgcode, args: [message(code: 'pedidoProducto.label', default: 'PedidoProducto'), pedidoProducto.id])
                redirect action:"index"
            }

        }
    }

    def create() {
        def productos = productoService.obtenerPrimarios()


        respond new PedidoProducto(params), model:[productos:productos]
    }

    @Transactional
    def save(PedidoCmd pedido) {

        PedidoProducto pedidoProducto = pedidoProductoService.crearPedido(pedido)

        if (pedidoProducto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pedidoProducto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pedidoProducto.errors, view:'create'
            return
        }

        pedidoProducto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pedidoProducto.label', default: 'PedidoProducto'), pedidoProducto.id])
                redirect action:"index", method:"GET"
            }
            '*' { render status: CREATED }
        }
    }

    def edit(PedidoProducto pedidoProducto) {
        respond pedidoProducto
    }

    @Transactional
    def update(PedidoProducto pedidoProducto) {
        if (pedidoProducto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pedidoProducto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pedidoProducto.errors, view:'edit'
            return
        }

        pedidoProducto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pedidoProducto.label', default: 'PedidoProducto'), pedidoProducto.id])
                redirect pedidoProducto
            }
            '*'{ respond pedidoProducto, [status: OK] }
        }
    }

    @Transactional
    def delete(PedidoProducto pedidoProducto) {

        if (pedidoProducto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pedidoProducto.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pedidoProducto.label', default: 'PedidoProducto'), pedidoProducto.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoProducto.label', default: 'PedidoProducto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
