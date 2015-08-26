        </div>
        <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Producto</th>
                <th>Cantidad</th>
                <th>Proyecto</th>
                <th>Fecha</th>
                <th>Fecha Cierre</th>
                <th>Movimento</th>
              </tr>
            </thead>
            <tbody>
            <g:each in="${pedidoProductoList}">
            <tr>
                <td>${it.id}</td>
                <td><g:link controller="Producto" action="show" id="${it.producto.id}">${it.producto}</g:link></td>
                <td>${it.cantidad}</td>
                <td><g:link controller="ProyectoFabricacion" action="show" id="${it.proyecto?.id}">${it.proyecto}</g:link></td>
                <td>${it.fecha}</td>
                <td>${it.fechaCierre}</td>
                <td><g:link controller="CuentaCorrienteProducto" action="show" id="${it.producto.id}">${it.movimiento?.id}</g:link></td>
                <td colspan="2">
                  <fieldset class="buttons">
                      <g:link class="btn-actualizar btn btn-warning" action="close" resource="${it}"><g:message code="default.button.close.label" default="Cerrar" /></g:link>
                  </fieldset>
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
                <g:paginate total="${cuentaCorrienteProductoCount ?: 0}" />
            </div>
            </div>
          </div>
