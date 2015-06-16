
import scm.*

class BootStrap {

    def init = { servletContext ->

    	println "******************* PROCESO DE HIDRATACION ****************************"
    	println "Cargando Categorias de Unidades de Medida..."


    	UnidadMedidaCategoria colores = new UnidadMedidaCategoria(nombre:"Color").save()
    	UnidadMedidaCategoria longitud = new UnidadMedidaCategoria(nombre:"Longitud").save()
    	UnidadMedidaCategoria peso = new UnidadMedidaCategoria(nombre:"Peso").save()
    	UnidadMedidaCategoria sensitivo = new UnidadMedidaCategoria(nombre:"Sensitivo").save()
    	UnidadMedidaCategoria numeral = new UnidadMedidaCategoria(nombre:"Numeral").save()
		
		println "Cargando Unidades de Medida..."
    	
		UnidadMedida color = new UnidadMedida(nombre:"color",tipo:"String",categoria:colores).save()
    	UnidadMedida rgb = new UnidadMedida(nombre:"rgb",tipo:"String",categoria:colores).save()

		UnidadMedida text = new UnidadMedida(nombre:"textura",tipo:"String",categoria:sensitivo).save()
		UnidadMedida olor = new UnidadMedida(nombre:"olor",tipo:"String",categoria:sensitivo).save()

		UnidadMedida gr = new UnidadMedida(nombre:"gr",tipo:"Float",categoria:peso).save()
		UnidadMedida kg = new UnidadMedida(nombre:"kg",tipo:"Float",categoria:peso).save()
		UnidadMedida tn = new UnidadMedida(nombre:"tn",tipo:"Float",categoria:peso).save()

		UnidadMedida mm = new UnidadMedida(nombre:"mm",tipo:"Float",categoria:longitud).save()
		UnidadMedida cm = new UnidadMedida(nombre:"cm",tipo:"Float",categoria:longitud).save()
		UnidadMedida mt = new UnidadMedida(nombre:"mt",tipo:"Float",categoria:longitud).save()

		UnidadMedida entero = new UnidadMedida(nombre:"no",tipo:"Integer",categoria:numeral).save()

		println "Cargando Tipos de Atributos..."

		AtributoTipo aColor = new AtributoTipo(nombre:"Color").save()
		aColor.addToUnidadesMedida(color)
		aColor.addToUnidadesMedida(rgb)
		aColor.save()

		AtributoTipo textura = new AtributoTipo(nombre:"Textura").save()
		textura.addToUnidadesMedida(text)
		textura.save()

		AtributoTipo aOlor = new AtributoTipo(nombre:"Olor").save()
		aOlor.addToUnidadesMedida(olor)
		aOlor.save()

		AtributoTipo alto = new AtributoTipo(nombre:"Alto").save()
		alto.addToUnidadesMedida(mm)
		alto.addToUnidadesMedida(cm)
		alto.addToUnidadesMedida(mt)
		alto.save()

		AtributoTipo ancho = new AtributoTipo(nombre:"Ancho").save()
		ancho.addToUnidadesMedida(mm)
		ancho.addToUnidadesMedida(cm)
		ancho.addToUnidadesMedida(mt)
		ancho.save()

		AtributoTipo largo = new AtributoTipo(nombre:"Largo").save()
		largo.addToUnidadesMedida(mm)
		largo.addToUnidadesMedida(cm)
		largo.addToUnidadesMedida(mt)
		largo.save()

		AtributoTipo diametro = new AtributoTipo(nombre:"Diametro").save()
		diametro.addToUnidadesMedida(mm)
		diametro.addToUnidadesMedida(cm)
		diametro.addToUnidadesMedida(mt)
		diametro.save()

		AtributoTipo radio = new AtributoTipo(nombre:"Radio").save()
		radio.addToUnidadesMedida(mm)
		radio.addToUnidadesMedida(cm)
		radio.addToUnidadesMedida(mt)
		radio.save()

		AtributoTipo aPeso = new AtributoTipo(nombre:"Peso").save()
		aPeso.addToUnidadesMedida(gr)
		aPeso.addToUnidadesMedida(kg)
		aPeso.addToUnidadesMedida(tn)
		aPeso.save()

		AtributoTipo numero = new AtributoTipo(nombre:"Numero").save()
		numero.addToUnidadesMedida(entero)
		numero.save()

		println "Cargando Articulos..."

		Articulo a1 = new Articulo(nombre:"Boton N5",descripcion:"Boton de chomba N5",).save()
		Atributo attr1 = new Atributo(tipo:numero,unidad:entero,valor:"5").save()
		Atributo attr2 = new Atributo(tipo:aColor,unidad:color,valor:"Rojo").save()
		Atributo attr3 = new Atributo(tipo:aPeso,unidad:gr,valor:"15,123").save()
		a1.addToAtributos(attr1)
		a1.addToAtributos(attr2)
		a1.addToAtributos(attr3)
		a1.save()



    }
    def destroy = {
    }
}
