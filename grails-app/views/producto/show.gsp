<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">Producto</h4>
        </div>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Nombre:</td>
                            <td valign="top" class="value">${fieldValue(bean:producto, field:'nombre')}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Descripcion:</td>
                            <td valign="top" class="value">${fieldValue(bean:producto, field:'descripcion')}</td>
                        </tr>
                        <tr class="prop">
                        <td valign="top" class="name">Materiales:</td>
                        <g:if test="${producto.materiales == null || producto.materiales.size() == 0}">
                            <td valign="top" class="value">Sin Materiales</td>
                        </g:if>
                        <g:else>
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="p" in="${producto.materiales}">
                                    <li>${p?.encodeAsHTML()}</li>
                                </g:each>
                                </ul>
                            </td>
                        </g:else>
                        </tr>
                    </tbody>
                </table>
            </div>
            <g:form resource="${producto}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="btn-actualizar btn btn-warning" action="edit" resource="${producto}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
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