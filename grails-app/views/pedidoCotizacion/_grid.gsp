<div>
                <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Proyecto</th>
                <th>Punto</th>
                <th>Producto</th>
                <th>C.U. Previsto</th>
                <th>C.U. Estimado</th>
                <th>C.U. Varianza</th>
                <th>Cantidad</th>
                <th>Acciones</th>
              </tr>
            </thead>
            <tbody>
            <g:each in="${pedidoCotizacionList}">
            <tr>
                <td><g:link action="show" id="${it.id}">${it.id}</g:link></td>
                <td><g:link controller="ProyectoFabricacion" action="show" id="${it.id}">${it.proyecto}</g:link></td>
                <td>${it.punto}</td>
                <td>${it.producto}</td>
                <td>${it.costoUnitarioPrevisto}</td>
                <td>${it.costoUnitarioEstimado}</td>
                <g:if test="${it.getVarianza() < 0}">
                    <td style="background-color:#2ecc71">${it.getVarianza().round(2)}</td>
                </g:if>
                <g:elseif test="${it.getVarianza() > 0}">
                    <td style="background-color:#e74c3c">${it.getVarianza().round(2)}</td>
                </g:elseif>
                <g:elseif test="${it.getVarianza() == 0 && it.costoUnitarioEstimado != 0 }">
                    <td style="background-color:#f39c12">${it.getVarianza().round(2)}</td>
                </g:elseif>
                <g:else>
                    <td >${it.getVarianza().round(2)}</td>
                </g:else>
                <td>${it.cantidad}</td>
                <td colspan="2">
                  <g:if test="${it.costoUnitarioEstimado > 0 && vistaProyecto}">
                    <g:link class="btn-actualizar btn btn-success" controller="ProyectoFabricacion" action="aprobar" id="${it?.id}">Aprobar</g:link></td>
                  </g:if>
                  <g:elseif test="${!vistaProyecto}">
                    <g:link class="btn-actualizar btn btn-warning" controller="PedidoCotizacion" action="edit" id="${it?.id}">Editar</g:link></td>
                  </g:elseif>
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
                <g:paginate total="${pedidoCotizacionCount ?: 0}" />
            </div>
</div>
