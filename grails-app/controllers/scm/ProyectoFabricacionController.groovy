package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ProyectoFabricacionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ProyectoFabricacion.list(params), model:[proyectoFabricacionCount: ProyectoFabricacion.count()]
    }

    def show(ProyectoFabricacion proyectoFabricacion) {
        respond proyectoFabricacion
    }

    def create() {
        respond new ProyectoFabricacion(params)
    }

    @Transactional
    def save(ProyectoFabricacion proyectoFabricacion) {
        if (proyectoFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (proyectoFabricacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond proyectoFabricacion.errors, view:'create'
            return
        }

        proyectoFabricacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'proyectoFabricacion.label', default: 'ProyectoFabricacion'), proyectoFabricacion.id])
                redirect proyectoFabricacion
            }
            '*' { respond proyectoFabricacion, [status: CREATED] }
        }
    }

    def edit(ProyectoFabricacion proyectoFabricacion) {
        respond proyectoFabricacion
    }

    @Transactional
    def update(ProyectoFabricacion proyectoFabricacion) {
        if (proyectoFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (proyectoFabricacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond proyectoFabricacion.errors, view:'edit'
            return
        }

        proyectoFabricacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'proyectoFabricacion.label', default: 'ProyectoFabricacion'), proyectoFabricacion.id])
                redirect proyectoFabricacion
            }
            '*'{ respond proyectoFabricacion, [status: OK] }
        }
    }

    @Transactional
    def delete(ProyectoFabricacion proyectoFabricacion) {

        if (proyectoFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        proyectoFabricacion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'proyectoFabricacion.label', default: 'ProyectoFabricacion'), proyectoFabricacion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'proyectoFabricacion.label', default: 'ProyectoFabricacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
