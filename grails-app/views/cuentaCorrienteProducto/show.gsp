<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'cuentaCorrienteProducto.label', default: 'cuentaCorrienteProducto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">Movimiento Cuenta Corriente</h4>
        </div>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Producto:</td>
                            <td valign="top" class="value">${fieldValue(bean:cuentaCorrienteProducto, field:'producto.nombre')}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Disponibilidad:</td>
                            <td valign="top" class="value">${disponibilidad}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <g:form resource="${cuentaCorrienteProducto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="btn-actualizar btn btn-warning" action="edit" resource="${cuentaCorrienteProducto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
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