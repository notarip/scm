package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

class PedidoCotizacionCmd{

    Long id
    String costoUnitarioPrevisto
    String costoUnitarioEstimado

}


@Transactional(readOnly = true)
class PedidoCotizacionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {

        
        def pedidoCotizacionList = PedidoCotizacion.withCriteria {
            if(params.query && params.query != "null"){
                  punto {
                    like('nombre', "%${params.query}%")
                }
            }
        }

        def puntosList = PuntoFabricacion.getAll() 

        params.max = Math.min(max ?: 10, 100)

        model:[puntos:puntosList, pedidoCotizacionList: pedidoCotizacionList, pedidoCotizacionCount: pedidoCotizacionList.size()]
    }

    def show(PedidoCotizacion pedidoCotizacion) {
        respond pedidoCotizacion
    }

    def create() {
        respond new PedidoCotizacion(params)
    }

    @Transactional
    def save(PedidoCotizacion pedidoCotizacion) {
        
        if (pedidoCotizacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pedidoCotizacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pedidoCotizacion.errors, view:'create'
            return
        }

        pedidoCotizacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pedidoCotizacion.label', default: 'PedidoCotizacion'), pedidoCotizacion.id])
                redirect pedidoCotizacion
            }
            '*' { respond pedidoCotizacion, [status: CREATED] }
        }
    }

    def edit(PedidoCotizacion pedidoCotizacion) {
        respond pedidoCotizacion
    }

    @Transactional
    def update(PedidoCotizacionCmd pedidoCmd) {
        
        PedidoCotizacion pedidoCotizacion = PedidoCotizacion.get(pedidoCmd.id)
        
        pedidoCotizacion.setCostoUnitarioEstimado(Float.valueOf(pedidoCmd.costoUnitarioEstimado))

        if (pedidoCotizacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pedidoCotizacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pedidoCotizacion.errors, view:'edit'
            return
        }

        pedidoCotizacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pedidoCotizacion.label', default: 'PedidoCotizacion'), pedidoCotizacion.id])
                action:"index"
            }
        }
    }

    @Transactional
    def delete(PedidoCotizacion pedidoCotizacion) {

        if (pedidoCotizacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pedidoCotizacion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pedidoCotizacion.label', default: 'PedidoCotizacion'), pedidoCotizacion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoCotizacion.label', default: 'PedidoCotizacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
