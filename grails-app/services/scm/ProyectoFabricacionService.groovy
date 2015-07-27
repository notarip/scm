package scm

import grails.transaction.Transactional


class ProductosDisponibles{

    Disponible disponibles
    Disponible noDisponibles

}

class Disponible{

    List<DisponibleDTO> finales = new ArrayList<DisponibleDTO>()
    List<DisponibleDTO> secundarios = new ArrayList<DisponibleDTO>()
    List<DisponibleDTO> primarios = new ArrayList<DisponibleDTO>()

}

class DisponibleDTO{

    Producto producto
    Integer cantidad

}

@Transactional
class ProyectoFabricacionService {

    ProductoService productoService 

    def serviceMethod() {

    }

	def obtenerProductosFabricables(){

		return productoService.obtenerProductosFabricables()

	}
    

    def crearProyecto(def proyectoCmd){

        Producto producto = Producto.get(proyectoCmd.idProducto)
        
        Date fecha = Date.parse('dd/MM/yyyy', proyectoCmd.fecha)

        ProyectoFabricacion proyecto = new ProyectoFabricacion(nombre:proyectoCmd.nombre, producto:producto, cantidad:proyectoCmd.cantidad,fecha:fecha)

/*
        - Se  analiza que productos (secundarios, finales) no se pueden cubrir, basado en la cuenta corriente de productos.
        - Se analiza que puntos de son mas convenientes para la fabricación priorizando según la disponibilidad, los internos y la experiencia (cuántas ordenes de fabricación haya completado) 
        - Se crean los pedidos de cotización por esos productos para los puntos de fabricación seleccionados.

*/

        /*
            1- Armar una lista de los productos finales que faltan
            2- Armar una lista de los productos secundarios que faltan
            3- Armar una lista de los productos primarios que faltan
        */

        /*
        disparar el bloqueo de productos
        productos.dispo.finales
        productos.dispo.secundarios
        productos.dispo.primarios

        disparar los pedidos
        productos.noDispo.finales
        productos.noDispo.secundarios (pedido de cotizacion)
        productos.noDispo.primarios   (pedido de producto)     
        */
        ProductosDisponibles productos = analizarArbolProductos(proyecto)

        bloquearProductos(proyecto, productos.disponibles);

        crearPedidosDeCotizacion(proyecto, productos.noDisponibles.secundarios)

        crearPedidosDeProductos(proyecto, productos.noDisponibles.primarios)



        proyecto.save()

        return proyecto

    }

    ProductosDisponibles analizarArbolProductos(ProyectoFabricacion proyecto){

        ProductosDisponibles productos = new ProductosDisponibles();

        
        return productos

    }

    def crearPedidosDeProductos(ProyectoFabricacion proyecto, List<DisponibleDTO> productos){


    }


    def crearPedidosDeCotizacion(ProyectoFabricacion proyecto, List<DisponibleDTO> productos){


    }

    def bloquearProductos(ProyectoFabricacion proyecto, Disponible disponibles){


    }

}
