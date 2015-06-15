
import scm.*

class BootStrap {

    def init = { servletContext ->

    	println "******************* PROCESO DE HIDRATACION ****************************"

    	UnidadMedidaCategoria colores = new UnidadMedidaCategoria(nombre:"Color").save()
    	UnidadMedidaCategoria longitud = new UnidadMedidaCategoria(nombre:"Longitud").save()
/*    	UnidadMedidaCategoria peso = new UnidadMedidaCategoria(nombre:"Peso").save()
    	UnidadMedidaCategoria sensitivo = new UnidadMedidaCategoria(nombre:"Sensitivo").save()

    	UnidadMedida rgb = new UnidadMedida(nombre:"rgb",tipo:"String",categoria:colores).save()
		UnidadMedida color = new UnidadMedida(nombre:"color",tipo:"String",categoria:colores).save()

		UnidadMedida text = new UnidadMedida(nombre:"textura",tipo:"String",categoria:sensitivo).save()

		UnidadMedida kg = new UnidadMedida(nombre:"kg",tipo:"Float",categoria:peso).save()
		UnidadMedida tn = new UnidadMedida(nombre:"tn",tipo:"Float",categoria:peso).save()

		UnidadMedida mm = new UnidadMedida(nombre:"mm",tipo:"Float",categoria:longitud).save()
		UnidadMedida cm = new UnidadMedida(nombre:"cm",tipo:"Float",categoria:longitud).save()
		UnidadMedida mt = new UnidadMedida(nombre:"mt",tipo:"Float",categoria:longitud).save()

*/

    }
    def destroy = {
    }
}
