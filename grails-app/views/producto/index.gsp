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
        <div>
            <g:link class="btn-alta btn btn-success" action="create">Nuevo</g:link>
        </div> 
        <table class="table table-bordered" style="">
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
                <td>${it.nombre}</td>
                <td>${it.descripcion}</td>
                <td>51</td>
                <td colspan="2">
                    <g:form resource="${producto}" method="DELETE">
                        <fieldset class="buttons">
                            <g:hiddenField name="id" value="${it?.id}" />
                            <g:actionSubmit class="btn-actualizar btn btn-warning" action="edit" value="Editar" />
                            <g:actionSubmit class="btn-actualizar btn btn-danger" action="delete" value="Borrar" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />

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

