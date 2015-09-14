<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'proyectoFabricacion.label', default: 'proyectoFabricacion')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">Proyecto Fabricacion</h4>
        </div>
            <div class="dialog">

            <div class="cabecera-grilla jumbotron">
            <table>
            <tbody>
                        <tr class="prop">
                            <td valign="top" class="name">Proyecto:</td>
                            <td valign="top" class="value">${proyecto.nombre}</td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Producto:</td>
                            <td valign="top" class="value">${proyecto.producto}</td>
                        </tr>
                    </tbody>
            </table>
            </div>
            
                 <g:render template="tree" model="['proyectoFabricacionList':proyectoFabricacionList]"/>

            </div>
        </div>
    </div>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        </div>
    </body>
</html>