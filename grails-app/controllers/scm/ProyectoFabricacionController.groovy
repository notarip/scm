package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


class ProyectoCmd{
    Integer id
    String nombre
    Integer idProducto
    Integer cantidad
    String fecha
}


@Transactional(readOnly = true)
class ProyectoFabricacionController {

    ProyectoFabricacionService proyectoFabricacionService
    PedidoProductoService pedidoProductoService
    PedidoCotizacionService pedidoCotizacionService


    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = "fecha"
        params.order = "desc"

        def proyectoFabricacionList = ProyectoFabricacion.createCriteria().list (params) {
            if ( params.query ) {
                ilike("nombre", "%${params.query}%")
          }
        }

        model:[proyectoFabricacionList:proyectoFabricacionList,proyectoFabricacionCount: ProyectoFabricacion.count()]
    }

    def show(ProyectoFabricacion proyecto) {

        def pedidoProductoList = pedidoProductoService.obtenerPedidos(proyecto)
        def pedidoCotizacionList = pedidoCotizacionService.obtenerPedidos(proyecto)

        model:[proyecto:proyecto, pedidoProductoList:pedidoProductoList, pedidoCotizacionList:pedidoCotizacionList, vistaProyecto:true]
    }

    def create() {

        def productos = proyectoFabricacionService.obtenerProductosFabricables()

        model:[productos:productos]
    }

    @Transactional
    def save(ProyectoCmd proyectoCmd) {

        ProyectoFabricacion proyectoFabricacion = proyectoFabricacionService.crearProyecto(proyectoCmd)

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
    def aprobar(PedidoCotizacion cotizacion){

      println " aprobando cotizacion: ${cotizacion} ...."

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
