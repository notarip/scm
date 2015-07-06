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
                 <g:form  action="save">
                 <table>
                    <g:hiddenField name="version" value="${producto?.version}" />
                    <fieldset class="form">
                        <g:hiddenField type="text" name="id" value="${producto?.id}"/>
                        <tr class="prop">
                            <td valign="top" class="name">Nombre:</td>
                            <td valign="top" class="value"><g:field type="text" name="nombre" value="${producto?.nombre}"/></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Descripción:</td>
                            <td valign="top" class="value"><g:field type="text" name="descripcion" value="${producto?.descripcion}"/></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Materiales:</td>
                            <td>
                            <g:render template="materiales" model="['producto':producto]"/>
                            </td>
                        </tr>   
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