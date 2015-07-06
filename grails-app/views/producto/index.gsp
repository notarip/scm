<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">Productos</h4>
        </div>
        <div class="cabecera-grilla jumbotron">
        <table>
        <tr>
        <td>
        <div>
            <g:link class="btn-alta btn btn-success" action="create">+ Producto</g:link>
        </div> 
        </td>           
        <td>
            <fieldset class="form">
                <g:form action="index" method="GET">
                <div class="fieldcontain">
                <label for="query">Buscar:</label>
                <g:textField placeholder="Ej. Remera" name="query" value="${params.query}"/>
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
                <th>Descripcion</th>
                <th>#Materiales</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
            <g:each in="${productoList}">
            <tr>
                <td>${it.id}</td>
                <td><g:link action="show" id="${it.id}">${it.nombre}</g:link></td>
                <td>${it.descripcion}</td>
                <td>${it.materiales.size()}</td>
                <td colspan="2">
                    <g:form resource="${producto}">
                        <fieldset class="buttons">
                            <g:hiddenField name="id" value="${it?.id}" />
                            <g:actionSubmit class="btn-actualizar btn btn-warning" action="edit" value="Editar" />
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
                <g:paginate total="${productoCount ?: 0}" />
            </div>
            </div>
          </div>
          </div>
        </div>
    </body>
</html>

