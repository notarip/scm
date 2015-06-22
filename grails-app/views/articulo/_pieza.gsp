<div id="pieza${i}">
    <g:hiddenField name='piezaList[${i}].id' value='${pieza.id}'/>
    <g:textField name='piezaList[${i}].articulo.id' value='${pieza.articulo.id}'/>
    <g:textField name='piezaList[${i}].cantidad' value='${pieza.cantidad}'/>
    <input type="hidden" name='piezaList[${i}]._deleted' id='piezaList[${i}]._deleted' value='false'/>
    <span onClick="$('#piezaList\\[${i}\\]\\._deleted').val('true'); $('#pieza${i}').hide()"><img src="${resource(dir:'skin', file:'database_delete.png')}" /></span>
</div>
