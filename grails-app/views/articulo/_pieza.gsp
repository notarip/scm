<div id="pieza${i}">
    <g:hiddenField name='piezas[${i}].id' value='${pieza.id}'/>
    <g:textField name='piezas[${i}].idArticulo' value='${pieza?.articulo?.id}'/>
    <g:textField name='piezas[${i}].cantidad' value='${pieza?.cantidad}'/>
    <input type="hidden" name='piezas[${i}]._deleted' id='piezas[${i}]._deleted' value='false'/>
    <span onClick="$('#piezas\\[${i}\\]\\._deleted').val('true'); $('#pieza${i}').hide()"><img src="${resource(dir:'skin', file:'database_delete.png')}" /></span>
</div>
