<script type="text/javascript">

    var childCount = ${producto?.materiales?.size()} + 0;
    var productos = "";
    var etapas = "";

    $.ajax({
        url:"/producto/ajaxGetProductos",
        async:false
    }).done(function(result) {
        $.each(result, function() {
            productos += "<option value='"+ this.id + "'>" + this.nombre + "</option>\n";
        });

    }).fail(function(result) {
        alert( "error" );
    }).always(function(result) {
        //alert( "complete" );
    });

    function addChild() {

        var htmlId = "material" + childCount;

        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<select class='form-control select-material' id='materiales[" + childCount + "].idProducto' name='materiales[" + childCount + "].idProducto'>\n";
        templateHtml += productos;
        templateHtml += "</select>\n";
        templateHtml += "<input type='number' id='materiales[" + childCount + "].cantidad' name='materiales[" + childCount + "].cantidad' />\n";

        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><a class='btn-eliminar-material btn btn-danger'>Borrar<a/></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
        childCount++;
    }


</script>

<div id="childList" class="materiales-container">
    <g:each var="material" in="${producto.materiales}" status="i">
        <g:render template='material' model="['material':material,'i':i]"/>
    </g:each>
</div>
<span> <a class="btn-alta-material btn btn-success" type="button" onclick="addChild();"> + Material</a></span>
