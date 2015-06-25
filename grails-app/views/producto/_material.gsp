<div id="material${i}">
    <g:hiddenField name='materiales[${i}].id' value='${material.id}'/>
    <g:textField name='materiales[${i}].idProducto' value='${material?.producto?.id}'/>
    <g:textField name='materiales[${i}].cantidad' value='${material?.cantidad}'/>
    <input type="hidden" name='materiales[${i}]._deleted' id='materiales[${i}]._deleted' value='false'/>
    <span  onClick="$('#materiales\\[${i}\\]\\._deleted').val('true'); $('#material${i}').hide()"><a  class="delete"/></span>
</div>
