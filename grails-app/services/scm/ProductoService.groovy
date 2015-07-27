package scm

import grails.transaction.Transactional

@Transactional
class ProductoService {

    def cantidadFinales(){

        return obtenerFinales().size();
    }

    def cantidadPrimarios(){

        return obtenerPrimarios().size();
    }

    def cantidadSecundarios(){

        return obtenerSecundarios().size();
    }

    
    def obtenerFinales(){

        def list = Producto.findAll();
        List<Producto> finales = new ArrayList<Producto>();

        list.each{ producto ->
            if(producto.esFinal()){
                finales.add(producto)
            }
        }

        return finales;
    }

    def obtenerPrimarios(){

        def list = Producto.findAll();
        List<Producto> primarios = new ArrayList<Producto>();

        list.each{ producto ->
            if(producto.esPrimario()){
                primarios.add(producto)
            }
        }

        return primarios;
    }

    def obtenerSecundarios(){

        def list = Producto.findAll();
        List<Producto> secundarios = new ArrayList<Producto>();

        list.each{ producto ->
            if(producto.esPrimario()){
                secundarios.add(producto)
            }
        }

        return secundarios;
    }    

    
    def obtenerProductosFabricables(){

        List<Producto> productos = new ArrayList<Producto>()

        Producto.findAll().each{ producto ->

            if(!producto.esPrimario())
                productos.add(producto)

        }

        return productos

    }


    def actualizarProducto(Producto producto, def productoCmd){

        producto.setNombre(productoCmd.nombre)
        producto.setDescripcion(productoCmd.descripcion)
        actualizarMateriales(producto, productoCmd)
    }


    def actualizarMateriales(Producto producto, def productoCmd) {

		def materialesNuevos = productoCmd.materiales.findAll{ !(it._deleted) }
        def materialesBorrados = productoCmd.materiales.findAll{ it._deleted }
        
        materialesBorrados.each{ log.info "materiales borrados: ${it}"}
        materialesBorrados.each{ material -> producto.materiales.remove(Material.get(material.id)) }

        materialesNuevos.each{material -> 

            log.info "materiales nuevos: ${material}";

        	if(material.id){
        		def p  = Material.get(material.id);
        		p.cantidad = material.cantidad;
                //p.etapaFabricacion = EtapaFabricacion.get(material.idEtapa)
        		p.save();
        	}else{
        		producto.addToMateriales(new Material(material));
        	}
      	
        }

    }
}

