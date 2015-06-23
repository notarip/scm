<script type="text/javascript">

    var childCount = ${producto?.materiales.size()} + 0;

    function addChild() {

        var htmlId = "material" + childCount;
        var deleteIcon = "${resource(dir:'assets/images/skin', file:'database_delete.png')}";
        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<select id='materiales[" + childCount + "].idProducto' name='materiales[" + childCount + "].idProducto'>\n";
        templateHtml += "<option value='1'>uno</option>\n";
        templateHtml += "<option value='2'>dos</option>\n";
        templateHtml += "</select>\n";
        templateHtml += "<input type='number' id='materiales[" + childCount + "].cantidad' name='materiales[" + childCount + "].cantidad' />\n";
        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><img src='" + deleteIcon + "' /></span>\n";
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
