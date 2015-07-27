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
            <fieldset class="buttons">
                <g:link class="btn-guardar btn btn-info" action="index">${message(code: 'default.button.back.label', default: 'Volver')}</g:link>            
            </fieldset>        
            <div class="dialog">

            <div class="cabecera-grilla jumbotron">
            <table>
            <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Producto:</td>
                            <td valign="top" class="value">${producto.nombre}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Disponibilidad:</td>
                            <td valign="top" class="value">${disponibilidad}</td>
                        </tr>
                    </tbody>
            </table>
            </div>
            
                 <g:render template="grid" model="['cuentaCorrienteProductoList':cuentaCorrienteProductoList]"/>

            </div>
        </div>
    </div>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        </div>
    </body>
</html>