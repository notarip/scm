package scm

import grails.transaction.Transactional

@Transactional
class ArticuloService {

    def actualizarPiezas(Articulo articulo, def articuloCmd) {

		def piezasNuevas = articuloCmd.piezas.findAll{ !(it._deleted) }
        def piezasBorradas = articuloCmd.piezas.findAll{ it._deleted }
        
        piezasBorradas.each{ println "piezas borradas: ${it}"}
        piezasBorradas.each{ pieza -> articulo.piezas.remove(Pieza.get(pieza.id)) }

        piezasNuevas.each{pieza -> 

        	if(pieza.id){
        		def p  = Pieza.get(pieza.id);
        		p.cantidad = pieza.cantidad;
        		p.save();
        	}else{
        		articulo.addToPiezas(new Pieza(pieza));
        	}
      	
        }

    }
}

