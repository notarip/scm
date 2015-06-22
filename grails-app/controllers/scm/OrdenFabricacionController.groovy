package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class OrdenFabricacionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond OrdenFabricacion.list(params), model:[ordenFabricacionCount: OrdenFabricacion.count()]
    }

    def show(OrdenFabricacion ordenFabricacion) {
        respond ordenFabricacion
    }

    def create() {
        respond new OrdenFabricacion(params)
    }

    @Transactional
    def save(OrdenFabricacion ordenFabricacion) {
        if (ordenFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ordenFabricacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ordenFabricacion.errors, view:'create'
            return
        }

        ordenFabricacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'ordenFabricacion.label', default: 'OrdenFabricacion'), ordenFabricacion.id])
                redirect ordenFabricacion
            }
            '*' { respond ordenFabricacion, [status: CREATED] }
        }
    }

    def edit(OrdenFabricacion ordenFabricacion) {
        respond ordenFabricacion
    }

    @Transactional
    def update(OrdenFabricacion ordenFabricacion) {
        if (ordenFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (ordenFabricacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond ordenFabricacion.errors, view:'edit'
            return
        }

        ordenFabricacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ordenFabricacion.label', default: 'OrdenFabricacion'), ordenFabricacion.id])
                redirect ordenFabricacion
            }
            '*'{ respond ordenFabricacion, [status: OK] }
        }
    }

    @Transactional
    def delete(OrdenFabricacion ordenFabricacion) {

        if (ordenFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        ordenFabricacion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ordenFabricacion.label', default: 'OrdenFabricacion'), ordenFabricacion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'ordenFabricacion.label', default: 'OrdenFabricacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
