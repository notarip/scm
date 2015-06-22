<script type="text/javascript">
    var childCount = ${articulo?.piezas.size()} + 0;

    function addChild() {
        var htmlId = "pieza" + childCount;
        var deleteIcon = "${resource(dir:'assets/images/skin', file:'database_delete.png')}";
        var templateHtml = "<div id='" + htmlId + "' name='" + htmlId + "'>\n";
        templateHtml += "<input type='text' id='piezas[" + childCount + "].idArticulo' name='piezas[" + childCount + "].idArticulo' />\n";
        templateHtml += "<input type='number' id='piezas[" + childCount + "].cantidad' name='piezas[" + childCount + "].cantidad' />\n";
        templateHtml += "<span onClick='$(\"#" + htmlId + "\").remove();'><img src='" + deleteIcon + "' /></span>\n";
        templateHtml += "</div>\n";
        $("#childList").append(templateHtml);
        childCount++;
    }
</script>

<div id="childList">
    <g:each var="pieza" in="${articulo.piezas}" status="i">
        <g:render template='pieza' model="['pieza':pieza,'i':i]"/>
    </g:each>
</div>
<input type="button" value="Add Pieza" onclick="addChild();" />
