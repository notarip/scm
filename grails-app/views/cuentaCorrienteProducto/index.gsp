<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <g:set var="entityName" value="${message(code: 'cuentaCorrienteProducto.label', default: 'cuentaCorrienteProducto')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>

        <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
        <div class="panel-heading">
            <h4 class="panel-title">Cuenta Corriente Productos</h4>
        </div>
        <div class="cabecera-grilla jumbotron">
        <table>
        <tr>
        <td>
        <div>
            <g:link class="btn-alta btn btn-success" action="create">+ Movimiento</g:link>
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
                <th>Producto</th>
                <th>Origen</th>
                <th>Orden</th>
                <th>Punto</th>
                <th>Cantidad</th>
                <th>Fecha</th>
              </tr>
            </thead>
            <tbody>
            <g:each in="${cuentaCorrienteProductoList}">
            <tr>
                <td>${it.id}</td>
                <td>${it.producto}</td>
                <td>${it.origen}</td>
                <td><g:link controller="OrdenFabricacion" action="show" id="${it.orden?.id}">${it.orden?.id}</g:link></td>
                <td><g:link controller="PuntoFabricacion" action="show" id="${it.punto?.id}">${it.punto?.id}</g:link></td>
                <td>${it.getCantidadConSigno()}</td>
                <td>${it.fecha}</td>
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
                <g:paginate total="${cuentaCorrienteProductoCount ?: 0}" />
            </div>
            </div>
          </div>
          </div>
        </div>
    </body>
</html>

