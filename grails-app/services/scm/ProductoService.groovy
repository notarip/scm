package scm

import grails.transaction.Transactional

@Transactional
class ProductoService {

    def cantidadFinales(){

        def list = Producto.findAll();
        def cantidad = 0    

        list.each{ producto ->
            if (!producto.materiales.isEmpty()){
                def esHijo = Material.findAllByProducto(producto).size()
                if(esHijo == 0){
                    println producto
                    cantidad++
                }
            }

        }

        return cantidad;
    }

    def cantidadPrimarios(){

        def list = Producto.findAll()
        def cantidad = 0 
        list.each{ producto ->
            if (producto.materiales.isEmpty()){
                cantidad++
            }
        }
        return cantidad
    }

    def cantidadSemiElaborados(){

        def list = Producto.findAll()
        def cantidad = 0 
        list.each{ producto ->
            if (!producto.materiales.isEmpty()){
                cantidad++
            }
        }
        return cantidad
    }



    def actualizarMateriales(Producto producto, def productoCmd) {

		def materialesNuevos = productoCmd.materiales.findAll{ !(it._deleted) }
        def materialesBorrados = productoCmd.materiales.findAll{ it._deleted }
        
        materialesBorrados.each{ println "materiales borrados: ${it}"}
        materialesBorrados.each{ material -> producto.materiales.remove(Material.get(material.id)) }

        materialesNuevos.each{material -> 

            println "materiales nuevos: ${material}";

        	if(material.id){
        		def p  = Material.get(material.id);
        		p.cantidad = material.cantidad;
        		p.save();
        	}else{
        		producto.addToMateriales(new Material(material));
        	}
      	
        }

    }
}

