package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.transform.ToString



@ToString(includeNames=true)
class PuntoFabricacionCmd {
    Integer id
    String nombre
    String observaciones
    Boolean interno
    List<CategoriaCmd> categorias
}

@ToString(includeNames=true)
class CategoriaCmd {
   Integer id
   String nombre
   boolean _deleted
}


@Transactional(readOnly = true)
class PuntoFabricacionController {

    PuntoFabricacionService puntoFabricacionService


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        def puntoList = PuntoFabricacion.createCriteria().list (params) {
            if ( params.query ) {
                ilike("nombre", "%${params.query}%")
            }
        }

      [puntoFabricacionList: puntoList, puntoFabricacionCount: PuntoFabricacion.count()]
    }

    def show(PuntoFabricacion puntoFabricacion) {
        respond puntoFabricacion
    }

    def create() {
        respond new PuntoFabricacion(params)
    }

    @Transactional
    def save(PuntoFabricacion puntoFabricacion) {
        if (puntoFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (puntoFabricacion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond puntoFabricacion.errors, view:'create'
            return
        }

        puntoFabricacion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'puntoFabricacion.label', default: 'PuntoFabricacion'), puntoFabricacion.id])
                redirect puntoFabricacion
            }
            '*' { respond puntoFabricacion, [status: CREATED] }
        }
    }

    def edit(PuntoFabricacion puntoFabricacion) {
        respond puntoFabricacion
    }

    @Transactional
    def update(PuntoFabricacionCmd puntoCmd) {

        def punto = PuntoFabricacion.get(puntoCmd.id)

        if (punto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (punto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond punto.errors, view:'edit'
            return
        }

        puntoFabricacionService.actualizarPuntoFabricacion(punto, puntoCmd)

        punto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'puntoFabricacion.label', default: 'PuntoFabricacion'), punto.id])
                redirect punto
            }
            '*'{ respond punto, [status: OK] }
        }
    }

    @Transactional
    def delete(PuntoFabricacion puntoFabricacion) {

        if (puntoFabricacion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        puntoFabricacion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'puntoFabricacion.label', default: 'PuntoFabricacion'), puntoFabricacion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'puntoFabricacion.label', default: 'PuntoFabricacion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
