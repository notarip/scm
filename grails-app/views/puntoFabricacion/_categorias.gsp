<script type="text/javascript">

    var childCount = ${puntoFabricacion?.categorias.size()} + 0;
    var categorias = "";

    $.ajax({
        url:"/categoria/ajaxGetCategorias",
        async:false
    }).done(function(result) {
        $.each(result, function() {
            categorias += "<option value='"+ this.id + "'>" + this.nombre + "</option>\n";
        });

    }).fail(function(result) {
        alert( "error" );
    }).always(function(result) {
        //alert( "complete" );
    });



    function addChild() {

        var htmlId = "categoria" + childCount;

        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<select class='form-control select-material' id='categorias[" + childCount + "].id' name='categorias[" + childCount + "].id'>\n";

        templateHtml += categorias;
        templateHtml += "</select>\n";

        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><a class='btn-eliminar-material btn btn-danger'>Borrar<a/></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
        childCount++;
    }


</script>

<div id="childList" class="materiales-container">
    <g:each var="categoria" in="${puntoFabricacion.categorias}" status="i">
        <g:render template='categoria' model="['categoria':categoria,'i':i]"/>
    </g:each>
</div>
<span> <a class="btn-alta-material btn btn-success" type="button" onclick="addChild();"> + Categoria</a></span>
