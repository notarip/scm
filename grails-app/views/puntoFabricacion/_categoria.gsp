<div id="categoria${i}">
    <g:hiddenField name='categorias[${i}].id' value='${categoria.id}'/>
    <g:textField name='categoria[${i}].id' readonly="readonly" value='${categoria?.nombre}'/>
    <input type="hidden" name='categorias[${i}]._deleted' id='categorias[${i}]._deleted' value='false'/>
    <span  onClick="$('#categorias\\[${i}\\]\\._deleted').val('true'); $('#categoria${i}').hide()">
    	<a class="btn-eliminar-material btn btn-danger">Borrar</a>
	</span>
</div>
