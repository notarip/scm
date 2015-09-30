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
    Disponible faltantes = new Disponible()

}


@Transactional
class ProyectoFabricacionService {

    ProductoService productoService
    CuentaCorrienteProductoService cuentaCorrienteProductoService
    PedidoProductoService pedidoProductoService
    PuntoFabricacionService puntoFabricacionService

    def serviceMethod() {

    }

	def obtenerProductosFabricables(){

		return productoService.obtenerProductosFabricables()

	}

  @Transactional
  def crearProyecto(def proyectoCmd){

      Producto producto = Producto.get(proyectoCmd.idProducto)
      Date fecha = null

      if(proyectoCmd.fecha)
        fecha = Date.parse('dd/MM/yyyy', proyectoCmd.fecha)
      else
        fecha = new Date()

      ProyectoFabricacion proyecto = new ProyectoFabricacion(nombre:proyectoCmd.nombre, producto:producto, cantidad:proyectoCmd.cantidad,fecha:fecha)

      proyecto.save()

      analizarProyecto(proyecto)

      proyecto.save()

      return proyecto
  }

    def analizarProyecto(ProyectoFabricacion proyecto){

        ProductosDisponibles disponibilidad = new ProductosDisponibles();

        Producto productoFinal = proyecto.producto

        Long cantidad = proyecto.cantidad

        analizarArbolProductos(proyecto, disponibilidad, productoFinal, cantidad, true)


        //HashMap<Producto,Long> faltantes = unificarProductos(proyecto, disponibilidad.faltantes.primarios)

        //crearPedidosProductos(proyecto, faltantes)

        crearPedidosProductos2(proyecto, disponibilidad.faltantes.primarios)

        ArrayList<ProductoDTO> fabricables = new ArrayList<ProductoDTO>()
        fabricables.addAll(disponibilidad.disponibles.finales)
        fabricables.addAll(disponibilidad.faltantes.finales)
        fabricables.addAll(disponibilidad.disponibles.secundarios)
        fabricables.addAll(disponibilidad.faltantes.secundarios)

        log.info "Productos a fabricar: ${fabricables}"

        HashMap<Producto,Long> productos = unificarProductos(proyecto, fabricables)

        crearPedidosDeCotizacion(proyecto, productos)



    }


    def HashMap<Producto, Long> unificarProductos(ProyectoFabricacion proyecto, List<ProductoDTO> productos){

        HashMap<Producto,Long> map = new HashMap<Producto,Long>();

        productos.each{ prod ->

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
            disponibilidad.faltantes.primarios.add(new ProductoDTO(producto: producto,cantidad:disponible.faltante))

        }else{

            if(producto.esFinal()){

                disponibilidad.disponibles.finales.add(new ProductoDTO(producto: producto,cantidad:disponible.disponible))
                disponibilidad.faltantes.finales.add(new ProductoDTO(producto: producto,cantidad:disponible.faltante))

            }else{

                disponibilidad.disponibles.secundarios.add(new ProductoDTO(producto: producto,cantidad:disponible.disponible))
                disponibilidad.faltantes.secundarios.add(new ProductoDTO(producto: producto,cantidad:disponible.faltante))

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

    def crearPedidoProducto(ProyectoFabricacion proyecto, Producto producto, Long cantidad){

      if (cantidad > 0){

        PedidoProducto pedido = pedidoProductoService.crearPedidoProducto(proyecto, producto, cantidad)

        pedido.save flush:true

      }

    }


    def crearPedidosProductos2(ProyectoFabricacion proyecto, List<ProductoDTO> productos){

        productos.each{ prod ->

          crearPedidoProducto(proyecto, prod.producto, prod.cantidad)

        }

    }


    def crearPedidosDeCotizacion(ProyectoFabricacion proyecto, HashMap<Producto,Long> productos){

      PedidoCotizacion cotizacion = null

      for(Producto producto: productos.keySet()) {

          Long cantidad = productos.get(producto)

          ArrayList<PuntoFabricacion> puntos = puntoFabricacionService.getPuntos(producto)

          for(PuntoFabricacion punto: puntos){

            cotizacion = new PedidoCotizacion(proyecto:proyecto, punto:punto, producto:producto, cantidad:cantidad)
            cotizacion.setCostoUnitarioPrevisto(producto.categoria.costoFabricacion)

            cotizacion.save flush:true

          }

      }

    }

}
