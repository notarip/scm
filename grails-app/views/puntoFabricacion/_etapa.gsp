<div id="etapa${i}">
    <g:hiddenField name='etapas[${i}].id' value='${etapa.id}'/>
    <g:textField name='etapa[${i}].id' readonly="readonly" value='${etapa?.nombre}'/>
    <input type="hidden" name='etapas[${i}]._deleted' id='etapas[${i}]._deleted' value='false'/>
    <span  onClick="$('#etapas\\[${i}\\]\\._deleted').val('true'); $('#etapa${i}').hide()">
    	<a class="btn-eliminar-material btn btn-danger">Borrar</a>
	</span>
</div>
