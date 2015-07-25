package scm

import grails.transaction.Transactional

@Transactional
class ProductoService {

    def cantidadFinales(){

        def list = Producto.findAll();
        def cantidad = 0    

        list.each{ producto ->
            if(producto.esFinal()){
                cantidad++
            }
        }

        return cantidad;
    }

    def cantidadPrimarios(){

        def list = Producto.findAll()
        def cantidad = 0 
        list.each{ producto ->
            if (producto.esPrimario()){
                cantidad++
            }
        }
        return cantidad
    }

    def cantidadSecundarios(){

        def list = Producto.findAll()
        def cantidad = 0 
        list.each{ producto ->
            if (producto.esSecundario()){
                cantidad++
            }
        }
        return cantidad
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

