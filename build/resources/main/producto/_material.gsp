<div id="material${i}">
    <g:hiddenField name='materiales[${i}].id' value='${material.id}'/>
    <g:textField name='materiales[${i}].idProducto' readonly="readonly" value='${material?.producto?.nombre}'/>
    <g:hiddenField name='materiales[${i}].idProducto' value='${material?.producto?.id}'/>
    <g:field name='materiales[${i}].cantidad' value='${material?.cantidad}' type='number'/>
    <g:textField name='materiales[${i}].idEtapa' readonly="readonly" value='${material?.etapaFabricacion?.nombre}'/>
    <input type="hidden" name='materiales[${i}]._deleted' id='materiales[${i}]._deleted' value='false'/>
    <span  onClick="$('#materiales\\[${i}\\]\\._deleted').val('true'); $('#material${i}').hide()">
    	<a class="btn-eliminar-material btn btn-danger">Borrar</a>
	</span>
</div>
