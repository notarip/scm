<script type="text/javascript">

    var childCount = ${producto?.materiales.size()} + 0;
    var productos = "";

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
        //var deleteIcon = "${resource(dir:'images/skin', file:'database_delete.png')}";
        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<select id='materiales[" + childCount + "].idProducto' name='materiales[" + childCount + "].idProducto'>\n";
        templateHtml += productos
        templateHtml += "</select>\n";
        templateHtml += "<input type='number' id='materiales[" + childCount + "].cantidad' name='materiales[" + childCount + "].cantidad' />\n";
        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><img class='delete'/></span>\n";
        //templateHtml += "<span class='delete' onClick='$(\"#" + htmlId + "\").remove();'></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
        childCount++;
    }


</script>

<div id="childList">
    <g:each var="material" in="${producto.materiales}" status="i">
        <g:render template='material' model="['material':material,'i':i]"/>
    </g:each>
</div>
<input type="button" value="Add Material" onclick="addChild();" />
