package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.transform.ToString



@ToString(includeNames=true)
class CCProductoCmd {
    Integer id
    Integer producto
    String origen
    Integer orden
    Integer punto
    Boolean ingreso
    Integer cantidad
}


@Transactional(readOnly = true)
class CuentaCorrienteProductoController {

    CuentaCorrienteProductoService cuentaCorrienteProductoService
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        params.sort = "fecha"
        params.order = "desc"

        respond CuentaCorrienteProducto.list(params), model:[cuentaCorrienteProductoCount: CuentaCorrienteProducto.count()]
    }

    def show(Producto producto) {

        def disponibilidad = cuentaCorrienteProductoService.obtenerDisponibilidad(producto)
        def cuentaCorrienteProductoList = cuentaCorrienteProductoService.obtenerMovimientos(producto)

        [producto:producto, cuentaCorrienteProductoList:cuentaCorrienteProductoList, disponibilidad: disponibilidad]
    }

    def create() {
        respond new CuentaCorrienteProducto(params)
    }

    @Transactional
    def save(CCProductoCmd cCProductoCmd) {

        CuentaCorrienteProducto cuentaCorrienteProducto = cuentaCorrienteProductoService.crearMovimiento(cCProductoCmd)

        if (cuentaCorrienteProducto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (cuentaCorrienteProducto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cuentaCorrienteProducto.errors, view:'create'
            return
        }

        
        cuentaCorrienteProducto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'cuentaCorrienteProducto.label', default: 'CuentaCorrienteProducto'), cuentaCorrienteProducto.id])
                redirect action: "index", method: "GET"            }
            '*' { respond cuentaCorrienteProducto, [status: CREATED] }
        }
    }

    def edit(CuentaCorrienteProducto cuentaCorrienteProducto) {
        respond cuentaCorrienteProducto
    }

    @Transactional
    def update(CuentaCorrienteProducto cuentaCorrienteProducto) {
        if (cuentaCorrienteProducto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (cuentaCorrienteProducto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond cuentaCorrienteProducto.errors, view:'edit'
            return
        }

        cuentaCorrienteProducto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cuentaCorrienteProducto.label', default: 'CuentaCorrienteProducto'), cuentaCorrienteProducto.id])
                redirect cuentaCorrienteProducto
            }
            '*'{ respond cuentaCorrienteProducto, [status: OK] }
        }
    }

    @Transactional
    def delete(CuentaCorrienteProducto cuentaCorrienteProducto) {

        if (cuentaCorrienteProducto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        cuentaCorrienteProducto.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cuentaCorrienteProducto.label', default: 'CuentaCorrienteProducto'), cuentaCorrienteProducto.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuentaCorrienteProducto.label', default: 'CuentaCorrienteProducto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
