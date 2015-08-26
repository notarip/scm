<!DOCTYPE html>
<html>
    <%@ page import="scm.*"%>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'pedidoProducto.label', default: 'Movimiento Pedido Producto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">Movimiento CC Producto</h4>
            </div>
                 <g:form  action="save">
                 <table>
                    <g:hiddenField name="version" value="${pedidoProducto?.version}" />
                    <fieldset class="form">
                        <g:hiddenField type="text" name="id" value="${pedidoProducto?.id}"/>
                        <tr class="prop">
                            <td valign="top" class="name">Producto</td>
                            <td valign="top" class="value">
                            <g:select name="producto" class='form-control select-material' from="${productos}" value="" optionKey="id" /></td>
                            <td valign="top" class="name">Cantidad</td>
                            <td valign="top" class="value"><g:field name='cantidad' min="0" value='' type='number'/></td>
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
