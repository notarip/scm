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
                <h4 class="panel-title">Puntos de Fabricaci&oacute;n</h4>
            </div>
                 <g:form  action="save">
                 <table>
                    <g:hiddenField name="version" value="${puntoFabricacion?.version}" />
                    <fieldset class="form">
                        <g:hiddenField type="text" name="id" value="${puntoFabricacion?.id}"/>
                        <tr class="prop">
                            <td valign="top" class="name">Nombre:</td>
                            <td valign="top" class="value"><g:field type="text" name="nombre" value="${puntoFabricacion?.nombre}"/></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Observaciones:</td>
                            <td valign="top" class="value"><g:field type="text" name="observaciones" value="${puntoFabricacion?.observaciones}"/></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Interno:</td>
                            <td valign="top" class="value"><g:checkBox name="interno" value="${puntoFabricacion?.interno}"/></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Categorias:</td>
                            <td>
                            <g:render template="categorias" model="['puntoFabricacion':puntoFabricacion]"/>
                            </td>
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
