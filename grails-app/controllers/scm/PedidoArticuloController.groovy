package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PedidoArticuloController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PedidoArticulo.list(params), model:[pedidoArticuloCount: PedidoArticulo.count()]
    }

    def show(PedidoArticulo pedidoArticulo) {
        respond pedidoArticulo
    }

    def create() {
        respond new PedidoArticulo(params)
    }

    @Transactional
    def save(PedidoArticulo pedidoArticulo) {
        if (pedidoArticulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pedidoArticulo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pedidoArticulo.errors, view:'create'
            return
        }

        pedidoArticulo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pedidoArticulo.label', default: 'PedidoArticulo'), pedidoArticulo.id])
                redirect pedidoArticulo
            }
            '*' { respond pedidoArticulo, [status: CREATED] }
        }
    }

    def edit(PedidoArticulo pedidoArticulo) {
        respond pedidoArticulo
    }

    @Transactional
    def update(PedidoArticulo pedidoArticulo) {
        if (pedidoArticulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pedidoArticulo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pedidoArticulo.errors, view:'edit'
            return
        }

        pedidoArticulo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pedidoArticulo.label', default: 'PedidoArticulo'), pedidoArticulo.id])
                redirect pedidoArticulo
            }
            '*'{ respond pedidoArticulo, [status: OK] }
        }
    }

    @Transactional
    def delete(PedidoArticulo pedidoArticulo) {

        if (pedidoArticulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pedidoArticulo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pedidoArticulo.label', default: 'PedidoArticulo'), pedidoArticulo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pedidoArticulo.label', default: 'PedidoArticulo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
