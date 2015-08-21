package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PedidoProductoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PedidoProducto.list(params), model:[pedidoProductoCount: PedidoProducto.count()]
    }

    def show(PedidoProducto pedidoProducto) {
        respond pedidoProducto
    }

    def create() {
        respond new PedidoProducto(params)
    }

    @Transactional
    def save(PedidoProducto pedidoProducto) {
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
                redirect pedidoProducto
            }
            '*' { respond pedidoProducto, [status: CREATED] }
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
