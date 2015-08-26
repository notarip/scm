<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'pedidoProducto.label', default: 'pedidoProducto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">pedidoProducto</h4>
        </div>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Producto:</td>
                            <td valign="top" class="value">${fieldValue(bean:pedidoProducto, field:'producto')}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Cantidad:</td>
                            <td valign="top" class="value">${fieldValue(bean:pedidoProducto, field:'cantidad')}</td>
                        </tr>
                        <tr class="prop">
                        <td valign="top" class="name">Proyecto:</td>
                        <g:if test="${pedidoProducto.proyecto == null}">
                            <td valign="top" class="value">Sin Proyecto</td>
                        </g:if>
                        <g:else>
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="p" in="${pedidoProducto.proyecto}">
                                    <li>${p?.encodeAsHTML()}</li>
                                </g:each>
                                </ul>
                            </td>
                        </g:else>
                        </tr>
                    </tbody>
                </table>
            </div>
            <g:form resource="${pedidoProducto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="btn-actualizar btn btn-warning" action="edit" resource="${pedidoProducto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <input class="btn-eliminar btn btn-danger" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </div>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        </div>
    </body>
</html>
