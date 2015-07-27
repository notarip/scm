<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'puntoFabricacion.label', default: 'Producto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">Punto de Fabricación</h4>
        </div>
            <div class="dialog">
                <table>
                    <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Nombre:</td>
                            <td valign="top" class="value">${fieldValue(bean:puntoFabricacion, field:'nombre')}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Observaciones:</td>
                            <td valign="top" class="value">${fieldValue(bean:puntoFabricacion, field:'observaciones')}</td>
                        </tr>
                        <tr class="prop">
                        <td valign="top" class="name">Etapas:</td>
                        <g:if test="${puntoFabricacion.etapas == null || puntoFabricacion.etapas.size() == 0}">
                            <td valign="top" class="value">Sin Etapas</td>
                        </g:if>
                        <g:else>
                            <td  valign="top" style="text-align:left;" class="value">
                                <ul>
                                <g:each var="p" in="${puntoFabricacion.etapas}">
                                    <li>${p?.encodeAsHTML()}</li>
                                </g:each>
                                </ul>
                            </td>
                        </g:else>
                        </tr>
                    </tbody>
                </table>
            </div>
            <g:form resource="${puntoFabricacion}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="btn-actualizar btn btn-warning" action="edit" resource="${puntoFabricacion}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
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