package scm

import grails.transaction.Transactional

@Transactional
class PuntoFabricacionService {

    def serviceMethod() {

    }

    def cantidadInternos(){

        def list = PuntoFabricacion.findAll()
        def cantidad = 0
        list.each{ punto ->
            if (punto.getInterno()){
                cantidad++
            }
        }
        return cantidad
    }

    def cantidadExternos(){

        def list = PuntoFabricacion.findAll()
        def cantidad = 0
        list.each{ punto ->
            if (!punto.getInterno()){
                cantidad++
            }
        }
        return cantidad
    }

    def getPuntos(Producto producto){

      def puntos = PuntoFabricacion.withCriteria {
          categorias {
          eq('id', producto.categoria.id)
        }
      }

      return puntos

    }

    def actualizarPuntoFabricacion(PuntoFabricacion punto, def puntoCmd){


    	punto.setNombre(puntoCmd.nombre)
    	punto.setObservaciones(puntoCmd.observaciones)
    	punto.setInterno(puntoCmd.interno)

    	actualizarCategorias(punto, puntoCmd)
    }


    def actualizarCategorias(PuntoFabricacion punto, def puntoCmd){


		  def categoriasNuevas = puntoCmd.categorias.findAll{ !(it._deleted) }
      def categoriasBorradas = puntoCmd.categorias.findAll{ it._deleted }

      categoriasBorradas.each{ categoria -> punto.categorias.remove(Categoria.get(categoria.id)) }

      categoriasNuevas.each{categoria ->

          def e = Categoria.get(categoria.id)

          if(!punto.categorias.contains(e)){
          	punto.addToCategorias(e);
          }

         }
    }

}
