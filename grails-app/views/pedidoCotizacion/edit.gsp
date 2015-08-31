<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'pedidoCotizacion.label', default: 'pedidoCotizacion')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">${message(code: 'pedidoCotizacion.label', default: 'pedidoCotizacion')}</h4>
            </div>
                 <g:form resource="${pedidoCotizacion}" method="PUT">
                 <table>
                    <g:hiddenField name="version" value="${pedidoCotizacion?.version}" />
                    <fieldset class="form">
                        <g:hiddenField type="text" name="id" value="${pedidoCotizacion?.id}"/>
                        <tr class="prop">
                            <td valign="top" class="name">Punto:</td>
                            <g:hiddenField name='punto' value='${pedidoCotizacion?.punto.id}'/>
                            <td valign="top" class="value"><g:textField type="text" readonly="readonly" name="nombrePunto" value="${pedidoCotizacion?.punto}"/></td>
                            <td valign="top" class="name">Proyecto:</td>
                            <g:hiddenField name='proyecto' value='${pedidoCotizacion?.proyecto.id}'/>
                            <td valign="top" class="value"><g:field type="text" readonly="readonly" name="proyectoNombre" value="${pedidoCotizacion?.proyecto}"/></td>
                            <td valign="top" class="name">Producto:</td>
                            <g:hiddenField name='producto' value='${pedidoCotizacion?.producto.id}'/>
                            <td valign="top" class="value"><g:field type="text" readonly="readonly" name="productoNombre" value="${pedidoCotizacion?.producto}"/></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">C.U. Previsto:</td>
                            <td valign="top" class="value"><g:field type="number" step="0.01" readonly="readonly" name="costoUnitarioPrevisto" value="${pedidoCotizacion?.costoUnitarioPrevisto}"/></td>
                        </tr>
                         <tr class="prop">
                            <td valign="top" class="name">C.U Estimado</td>
                            <td valign="top" class="value"><g:field type="number"  step="0.01" min="0" name="costoUnitarioEstimado" value="${pedidoCotizacion?.costoUnitarioEstimado}"/></td>
                        </tr>
                        <g:link class="btn-guardar btn btn-info" action="index">${message(code: 'default.button.cancel.label', default: 'Cancelar')}</g:link>
                        <input class="btn-guardar btn btn-success" type="submit" value="${message(code: 'default.button.update.label', default: 'Guardar')}" />
                    </fieldset>
                    </table>
                </g:form>
        </div>
    </div>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        </div>
    </body>
</html>