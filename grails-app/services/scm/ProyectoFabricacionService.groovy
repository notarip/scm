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
    PedidoProductoService pedidoProductoService

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


        //crearPedidosDeCotizacion(proyecto, productos.noDisponibles.secundarios)

        //crearPedidosDeProductos(proyecto, productos.noDisponibles.primarios)



        proyecto.save()

        return proyecto

    }

    def analizarProyecto(ProyectoFabricacion proyecto){

        ProductosDisponibles disponibilidad = new ProductosDisponibles();

        Producto productoFinal = proyecto.producto

        Long cantidad = proyecto.cantidad

        /*en esta pasada levanta los productos que va a utilizar
        * y los va reservando, ver metodo bloquearProducto
        */
        analizarArbolProductos(proyecto, disponibilidad, productoFinal, cantidad, true)

        HashMap<Producto,Long> faltantes = unificarProductos(proyecto, disponibilidad.noDisponibles)

        crearPedidosProductos(proyecto, faltantes)

        println faltantes

    }

    def HashMap<Producto, Long> unificarProductos(ProyectoFabricacion proyecto, Disponible faltantes){

        HashMap<Producto,Long> map = new HashMap<Producto,Long>();
        ArrayList<ProductoDTO> todos = new ArrayList<ProductoDTO>();

        //todos.addAll(faltantes.finales)
        //todos.addAll(faltantes.secundarios)
        todos.addAll(faltantes.primarios)

        todos.each{ prod ->

            if(prod.cantidad > 0){
              if(map.containsKey(prod.producto)){
                  map.put(prod.producto, map.get(prod.producto) + prod.cantidad);

              }else{
                  map.put(prod.producto,prod.cantidad)
              }
            }
        }


        return map

    }

    def analizarArbolProductos(ProyectoFabricacion proyecto, ProductosDisponibles disponibilidad, Producto producto, Long cantidad, boolean consultarDisponibilidad){

        Cantidad disponible = analizarDisponibilidad(producto,cantidad, consultarDisponibilidad)

        bloquearProducto(proyecto, producto, disponible.disponible)


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
                    analizarArbolProductos(proyecto,disponibilidad, material.producto, cant, consultarDisponibilidad)

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
                disponible.faltante = 0
            }
        }

        return disponible

    }

    def bloquearProducto(ProyectoFabricacion proyecto, Producto producto, Long disponible){

        cuentaCorrienteProductoService.debitarProducto(proyecto, producto, disponible)

    }

    def crearPedidosProductos(ProyectoFabricacion proyecto, HashMap<Producto, Long> productos){

        for(Producto producto: productos.keySet()) {

            Long cantidad = productos.get(producto)

            pedidoProductoService.crearPedidoProducto(proyecto, producto, cantidad)

        }


    }


    def crearPedidosDeCotizacion(ProyectoFabricacion proyecto, List<ProductoDTO> productos){


    }

    def bloquearProductos(ProyectoFabricacion proyecto, Disponible disponibles){


    }

}
