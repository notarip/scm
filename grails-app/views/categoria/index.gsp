<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <g:set var="entityName" value="${message(code: 'categoria.label', default: 'categoria')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">Categorias</h4>
        </div>
        <div class="cabecera-grilla jumbotron">
        <table>
        <tr>
        <td>
        <div>
            <g:link class="btn-alta btn btn-success" action="create">+ Categoria</g:link>
        </div>
        </td>
        <td>
            <fieldset class="form">
                <g:form action="index" method="GET">
                <div class="fieldcontain">
                <!--
                <label for="query">Buscar:</label>
                <g:textField placeholder="Ej. Remera" name="query" value="${params.query}"/>
                -->
                </div>
                </g:form>
            </fieldset>
        </td>
        </tr>
        </div>
        </table>

            <g:render template="grid" model="['categoriaList':categoriaList]"/>

          </div>
        </div>
    </body>
</html>
