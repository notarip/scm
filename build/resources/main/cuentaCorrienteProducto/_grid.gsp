        </div>
        <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Producto</th>
                <th>Origen</th>
                <th>Proyecto</th>
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
                <td><g:link action="show" id="${it.producto.id}">${it.producto}</g:link></td>
                <td>${it.origen}</td>
                <td>${it.proyecto}</td>
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