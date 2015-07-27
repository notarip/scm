        </div>
        <table class="table table-bordered">
            <thead>
              <tr>
                <th>#</th>
                <th>Nombre</th>
                <th>Producto</th>
                <th>Cantidad</th>
                <th>Fecha</th>
              </tr>
            </thead>
            <tbody>
            <g:each in="${proyectoFabricacionList}">
            <tr>
                <td>${it.id}</td>
                <td>${it.nombre}</td>
                <td><g:link action="show" id="${it.producto.id}">${it.producto}</g:link></td>
                <td>${it.cantidad}</td>
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
                <g:paginate total="${proyectoFabricacionCount ?: 0}" />
            </div>
            </div>
          </div>