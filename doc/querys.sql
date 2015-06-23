SELECT a.nombre, att_tipo.nombre as atributo, um.nombre as unidad , att.valor FROM producto a
inner join articulo_atributo aa on (aa.ARTICULO_ATRIBUTOS_ID = a.ID )
inner join atributo att on (att.id = aa.atributo_id)
inner join atributo_tipo att_tipo on (att_tipo.id = att.tipo_id)
inner join unidad_medida um on (um.id = att.unidad_id)