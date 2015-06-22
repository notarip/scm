package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.transform.ToString


@ToString(includeNames=true)
class ArticuloCmd {
    Integer id
    String nombre
    String descripcion
    List<PiezaCmd> piezas
}

@ToString(includeNames=true)
class PiezaCmd {
   Integer id
   Integer idArticulo
   Integer cantidad
   boolean _deleted

}

@Transactional(readOnly = true)
class ArticuloController {

    ArticuloService articuloService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Articulo.list(params), model:[articuloCount: Articulo.count()]
    }

    def show(Articulo articulo) {
        respond articulo
    }

    def create() {
        respond new Articulo(params)
    }

    @Transactional
    def save(ArticuloCmd articuloCmd) {

        def articulo = new Articulo(nombre: articuloCmd.nombre, descripcion: articuloCmd.descripcion)

        articuloCmd.piezas.each{ pieza ->  articulo.addToPiezas(new Pieza(pieza)) }
        
        
        if (articulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (articulo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond articulo.errors, view:'create'
            return
        }

        articulo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
                redirect articulo
            }
            '*' { respond articulo, [status: CREATED] }
        }
    }

    def edit(Articulo articulo) {
        respond articulo
    }

    @Transactional
    def update(ArticuloCmd articuloCmd) {

        def articulo = Articulo.get(articuloCmd.id)

        if (articulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (articulo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond articulo.errors, view:'edit'
            return
        }

        articuloService.actualizarPiezas(articulo, articuloCmd)

        articulo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
                redirect articulo
            }
            '*'{ respond articulo, [status: OK] }
        }
    }

    @Transactional
    def delete(Articulo articulo) {

        if (articulo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        articulo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'articulo.label', default: 'Articulo'), articulo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'articulo.label', default: 'Articulo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
