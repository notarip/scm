<!DOCTYPE html>
<html>
    <%@ page import="scm.*"%>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'categoria.label', default: 'Movimiento Pedido Producto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">Categorias</h4>
            </div>
                 <g:form  action="save">
                 <table>
                    <g:hiddenField name="version" value="${categoria?.version}" />
                    <fieldset class="form">
                        <g:hiddenField type="text" name="id" value="${categoria?.id}"/>
                        <tr class="prop">
                            <td valign="top" class="name">Nombre</td>
                            <td valign="top" class="value">
                            <td valign="top" class="value"><g:field name='nombre' type='text'/></td>
                            <td valign="top" class="name">Costo Fabricacion</td>
                            <td valign="top" class="value"><g:field name='costoFabricacion' step="0.001" min="0" value='' type='number'/></td>
                        </tr>
                        <g:link class="btn-guardar btn btn-info" action="index">${message(code: 'default.button.cancel.label', default: 'Cancelar')}</g:link>
                        <input class="btn-guardar btn btn-success" type="submit" value="${message(code: 'default.button.save.label', default: 'Guardar')}" />
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
