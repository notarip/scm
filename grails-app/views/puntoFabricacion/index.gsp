<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <g:set var="entityName" value="${message(code: 'puntoFabricacion.label', default: 'Producto')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">Puntos de Fabricación</h4>
        </div>
        <div class="cabecera-grilla jumbotron">
        <table>
        <tr>
        <td>
        <div>
            <g:link class="btn-alta btn btn-success" action="create">+ Punto Fabricación</g:link>
        </div> 
        </td>           
        <td>
            <fieldset class="form">
                <g:form action="index" method="GET">
                <div class="fieldcontain">
                <label for="query">Buscar:</label>
                <g:textField placeholder="Ej. Taller I" name="query" value="${params.query}"/>
                </div>
                </g:form>
            </fieldset>
        </td>
        </tr>
            </div>
        </table>

        </div>
        <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Nombre</th>
                <th>Observaciones</th>
                <th>Tipo</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
            <g:each in="${puntoFabricacionList}">
            <tr>
                <td>${it.id}</td>
                <td><g:link action="show" id="${it.id}">${it.nombre}</g:link></td>
                <td>${it.observaciones}</td>
                <td>${it.getTipo()}</td>    
                <td colspan="2">
                    <g:form resource="${puntoFabricacion}">
                        <fieldset class="buttons">
                            <g:hiddenField name="id" value="${it?.id}" />
                            <g:actionSubmit class="btn-actualizar btn btn-warning" action="edit" value="${message(code: 'default.button.edit.label', default: 'Editar')}" />
                            <!-- g:actionSubmit class="btn-actualizar btn btn-danger" action="delete" value="Borrar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" / -->

                        </fieldset>
                    </g:form>
                </td>
            </tr>
            </g:each>
            </tbody>
            </table>
            <div>
                <g:if test="${flash.message}">
                    <div class="alert alert-info" role="alert">${flash.message}</div>
                </g:if>
            </div>
            <div>
            <div class="pagination" >
                <g:paginate total="${puntoFabricacionCount ?: 0}" />
            </div>
            </div>
          </div>
          </div>
        </div>
    </body>
</html>

