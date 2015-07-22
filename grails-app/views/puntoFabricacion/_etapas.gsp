<script type="text/javascript">

    var childCount = ${puntoFabricacion?.etapas.size()} + 0;
    var etapas = "";

    $.ajax({
        url:"/etapaFabricacion/ajaxGetEtapas",
        async:false
    }).done(function(result) {
        $.each(result, function() {
            etapas += "<option value='"+ this.id + "'>" + this.nombre + "</option>\n";
        });
        
    }).fail(function(result) {
        alert( "error" );
    }).always(function(result) {
        //alert( "complete" );
    });



    function addChild() {

        var htmlId = "etapa" + childCount;

        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<select class='form-control select-material' id='etapas[" + childCount + "].id' name='etapas[" + childCount + "].id'>\n";

        templateHtml += etapas;
        templateHtml += "</select>\n";

        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><a class='btn-eliminar-material btn btn-danger'>Borrar<a/></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
        childCount++;
    }


</script>

<div id="childList" class="materiales-container">
    <g:each var="etapa" in="${puntoFabricacion.etapas}" status="i">
        <g:render template='etapa' model="['etapa':etapa,'i':i]"/>
    </g:each>
</div>
<span> <a class="btn-alta-material btn btn-success" type="button" onclick="addChild();"> + Etapa</a></span>
