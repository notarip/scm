package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class EtapaFabricacionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond EtapaFabricacion.list(params), model:[etapaFabricacionCount: EtapaFabricacion.count()]
    }

    def show(EtapaFabricacion etapaFabricacion) {
        respond etapaFabricacion
    }

    def create() {
        respond new EtapaFabricacion(params)
    }

    @Transactional
    def save(EtapaFabricacion etapaFabricacion) {
        if (etapaFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (etapaFabricacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond etapaFabricacion.errors, view:'create'
            return
        }

        etapaFabricacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'etapaFabricacion.label', default: 'EtapaFabricacion'), etapaFabricacion.id])
                redirect etapaFabricacion
            }
            '*' { respond etapaFabricacion, [status: CREATED] }
        }
    }

    def edit(EtapaFabricacion etapaFabricacion) {
        respond etapaFabricacion
    }

    @Transactional
    def update(EtapaFabricacion etapaFabricacion) {
        if (etapaFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (etapaFabricacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond etapaFabricacion.errors, view:'edit'
            return
        }

        etapaFabricacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'etapaFabricacion.label', default: 'EtapaFabricacion'), etapaFabricacion.id])
                redirect etapaFabricacion
            }
            '*'{ respond etapaFabricacion, [status: OK] }
        }
    }

    @Transactional
    def delete(EtapaFabricacion etapaFabricacion) {

        if (etapaFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        etapaFabricacion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'etapaFabricacion.label', default: 'EtapaFabricacion'), etapaFabricacion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'etapaFabricacion.label', default: 'EtapaFabricacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
