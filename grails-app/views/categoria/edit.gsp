<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'categoria.label', default: 'categoria')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">${message(code: 'categoria.label', default: 'categoria')}</h4>
            </div>
                 <g:form resource="${categoria}" method="PUT">
                 <table>
                    <g:hiddenField name="version" value="${categoria?.version}" />
                    <fieldset class="form">
                        <g:hiddenField type="text" name="id" value="${categoria?.id}"/>
                        <tr class="prop">
                            <td valign="top" class="name">Nombre:</td>
                            <td valign="top" class="value"><g:field type="text" readonly="readonly" name="nombre" value="${categoria?.nombre}"/></td>
                        </tr>
                        <tr class="prop">
                            <td valign="top" class="name">Costo Fabricacion:</td>
                            <td valign="top" class="value"><g:field type="number" name="costoFabricacion"  step="0.001" min="0" value="${categoria?.costoFabricacion}"/></td>
                        </tr>
                        <g:link class="btn-guardar btn btn-info" action="index">${message(code: 'default.button.cancel.label', default: 'Cancelar')}</g:link>
                        <input class="btn-guardar btn btn-success" type="submit" value="${message(code: 'default.button.update.label', default: 'Guardar')}" />
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