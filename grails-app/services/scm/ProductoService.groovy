package scm

import grails.transaction.Transactional

@Transactional
class ProductoService {

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

