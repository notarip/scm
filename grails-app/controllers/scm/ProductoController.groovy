package scm

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import groovy.transform.ToString
import grails.converters.*


@ToString(includeNames=true)
class ProductoCmd {
    Integer id
    String nombre
    String descripcion
    List<MaterialCmd> materiales
}

@ToString(includeNames=true)
class MaterialCmd {
   Integer id
   Integer idProducto
   Integer cantidad
   Integer idEtapa
   boolean _deleted

}

@Transactional(readOnly = true)
class ProductoController {

    ProductoService productoService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]



   def ajaxGetProductos = {
        def productos = Producto.getAll()
        render productos as JSON
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
 
        def productoList = Producto.createCriteria().list (params) {
            if ( params.query ) {
                ilike("nombre", "%${params.query}%")
            }
        }
 

        [productoList: productoList, productoCount: Producto.count()]
    }

    def show(Producto producto) {
        respond producto
    }

    def create() {
        respond new Producto(params)
    }

    @Transactional
    def save(ProductoCmd productoCmd) {

        def producto = new Producto(nombre: productoCmd.nombre, descripcion: productoCmd.descripcion)

        //productoCmd.materiales.each{ material ->  producto.addToMateriales(new Material(material)) }

        productoService.actualizarMateriales(producto, productoCmd)

        
        if (producto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (producto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond producto.errors, view:'create'
            return
        }

        producto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'producto.label', default: 'Producto'), producto.id])
                redirect producto
            }
            '*' { respond producto, [status: CREATED] }
        }
    }

    def edit(Producto producto) {
        respond producto
    }

    @Transactional
    def update(ProductoCmd productoCmd) {

        def producto = Producto.get(productoCmd.id)

        if (producto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (producto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond producto.errors, view:'edit'
            return
        }

        producto.setNombre(productoCmd.nombre)
        producto.setDescripcion(productoCmd.descripcion)
        productoService.actualizarMateriales(producto, productoCmd)

        producto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'producto.label', default: 'Producto'), producto.id])
                redirect producto
            }
            '*'{ respond producto, [status: OK] }
        }
    }

    @Transactional
    def delete(Producto producto) {

        if (producto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        def msg;
        if(producto.esFinal()){
            producto.delete flush:true
            msg = message(code: 'default.deleted.message', args: [message(code: 'producto.label', default: 'Producto'), producto.id])
        }else{
            msg = message(code: 'default.not.deleted.message', args: [message(code: 'producto.label', default: 'Producto'), producto.id])
        }

        request.withFormat {
            form multipartForm {
                flash.message = msg
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
