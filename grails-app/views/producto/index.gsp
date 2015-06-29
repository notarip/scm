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
            <li><g:link class="btn-alta btn btn-success" action="create">Nuevo</g:link></li>
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
                    <g:form method="post" >
                        <g:hiddenField name="id" value="${it?.id}" />
                        <g:hiddenField name="version" value="${it?.version}" /> 
                        <fieldset class="buttons">
                        <g:actionSubmit class="btn-alta btn btn-actualizar" action="update" value="Actualizar" />
                        <g:actionSubmit class="btn-alta btn btn-eliminar" action="delete" value="Eliminar" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </fieldset>         
                    </g:form>
                </td>
            </tr>
            </g:each>
            </tbody>
            </table>
            <div class="pagination">
                <g:paginate total="${productoCount ?: 0}" />
            </div>
          </div>
          </div>


        <div class="nav" role="navigation">
            <ul>
                <g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link>
            </ul>
        </div>
        <div id="list-producto" class="content scaffold-list" role="main">
        <div class="page-header">
            <h1 ><g:message code="default.list.label" args="[entityName]" /></h1>
        </div>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>




        </div>
    </body>
</html>

