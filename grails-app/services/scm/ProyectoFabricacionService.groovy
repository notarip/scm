package scm

import grails.transaction.Transactional
import grails.converters.*
import groovy.transform.ToString



@ToString(includeNames=true)
class Cantidad{

    Long disponible
    Long faltante

}

@ToString(includeNames=true)
class ProductoDTO{

    Producto producto
    Long cantidad

    @Override 
    public boolean equals(Object o) {    

        return this.producto.equals(o.producto);

    }

}

@ToString(includeNames=true)
class Disponible{

    List<ProductoDTO> finales = new ArrayList<ProductoDTO>()
    List<ProductoDTO> secundarios = new ArrayList<ProductoDTO>()
    List<ProductoDTO> primarios = new ArrayList<ProductoDTO>()

}

@ToString(includeNames=true)
class ProductosDisponibles{

    Disponible disponibles = new Disponible()
    Disponible noDisponibles = new Disponible()

}


@Transactional
class ProyectoFabricacionService {

    ProductoService productoService 
    CuentaCorrienteProductoService cuentaCorrienteProductoService

    def serviceMethod() {

    }

	def obtenerProductosFabricables(){

		return productoService.obtenerProductosFabricables()

	}
    

    def crearProyecto(def proyectoCmd){

        Producto producto = Producto.get(proyectoCmd.idProducto)
        
        Date fecha = Date.parse('dd/MM/yyyy', proyectoCmd.fecha)

        ProyectoFabricacion proyecto = new ProyectoFabricacion(nombre:proyectoCmd.nombre, producto:producto, cantidad:proyectoCmd.cantidad,fecha:fecha)

        proyecto.save()
        
        analizarProyecto(proyecto)

        //bloquearProductos(proyecto, productos.disponibles);

        //crearPedidosDeCotizacion(proyecto, productos.noDisponibles.secundarios)

        //crearPedidosDeProductos(proyecto, productos.noDisponibles.primarios)



        proyecto.save()

        return proyecto

    }

    def analizarProyecto(ProyectoFabricacion proyecto){

        ProductosDisponibles disponibilidad = new ProductosDisponibles();

        Producto productoFinal = proyecto.producto

        Long cantidad = proyecto.cantidad

        /*En esta pasada leventa los totales de productos que se van a utilizar*/
        analizarArbolProductos(disponibilidad, productoFinal, cantidad, false)

        //TODO  unificar todo en un Set<ProductoDTO>

        println disponibilidad 


    }

    def analizarArbolProductos(ProductosDisponibles disponibilidad, Producto producto, Long cantidad, boolean consultarDisponibilidad){

        Cantidad disponible = analizarDisponibilidad(producto,cantidad, consultarDisponibilidad)

        if(producto.esPrimario()){

            disponibilidad.disponibles.primarios.add(new ProductoDTO(producto: producto,cantidad:disponible.disponible))
            disponibilidad.noDisponibles.primarios.add(new ProductoDTO(producto: producto,cantidad:disponible.faltante))            

        }else{

            if(producto.esFinal()){
        
                disponibilidad.disponibles.finales.add(new ProductoDTO(producto: producto,cantidad:disponible.disponible))
                disponibilidad.noDisponibles.finales.add(new ProductoDTO(producto: producto,cantidad:disponible.faltante))            

            }else{

                disponibilidad.disponibles.secundarios.add(new ProductoDTO(producto: producto,cantidad:disponible.disponible))
                disponibilidad.noDisponibles.secundarios.add(new ProductoDTO(producto: producto,cantidad:disponible.faltante))            

            }
            
            producto.materiales.each{ material ->

                def cant = material.cantidad * disponible.faltante
                if(cant != 0)
                    analizarArbolProductos(disponibilidad, material.producto, cant, consultarDisponibilidad)

            }

        }

    }

    Cantidad analizarDisponibilidad(Producto producto, Long cantidad, boolean consultar){

        def dispoFinal = consultar?cuentaCorrienteProductoService.obtenerDisponibilidad(producto):0;
        Cantidad disponible = new Cantidad();
        disponible.disponible = 0
        disponible.faltante = cantidad


        if(dispoFinal > 0){
        
            if(dispoFinal < cantidad){
                
                disponible.disponible = dispoFinal
                disponible.faltante = cantidad - dispoFinal


            }else{

                disponible.disponible = cantidad
            }
        }

        return disponible

    }

    def crearPedidosDeProductos(ProyectoFabricacion proyecto, List<ProductoDTO> productos){


    }


    def crearPedidosDeCotizacion(ProyectoFabricacion proyecto, List<ProductoDTO> productos){


    }

    def bloquearProductos(ProyectoFabricacion proyecto, Disponible disponibles){


    }

}
