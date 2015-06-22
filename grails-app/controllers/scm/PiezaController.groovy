package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PiezaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Pieza.list(params), model:[piezaCount: Pieza.count()]
    }

    def show(Pieza pieza) {
        respond pieza
    }

    def create() {
        respond new Pieza(params)
    }

    @Transactional
    def save(Pieza pieza) {
        if (pieza == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pieza.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pieza.errors, view:'create'
            return
        }

        pieza.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'pieza.label', default: 'Pieza'), pieza.id])
                redirect pieza
            }
            '*' { respond pieza, [status: CREATED] }
        }
    }

    def edit(Pieza pieza) {
        respond pieza
    }

    @Transactional
    def update(Pieza pieza) {
        if (pieza == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (pieza.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond pieza.errors, view:'edit'
            return
        }

        pieza.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'pieza.label', default: 'Pieza'), pieza.id])
                redirect pieza
            }
            '*'{ respond pieza, [status: OK] }
        }
    }

    @Transactional
    def delete(Pieza pieza) {

        if (pieza == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        pieza.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'pieza.label', default: 'Pieza'), pieza.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'pieza.label', default: 'Pieza'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
