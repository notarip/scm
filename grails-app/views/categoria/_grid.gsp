        </div>
        <table class="table table-bordered">
            <thead>
              <tr>
                <th>Nombre</th>
                <th>Costo Fabricacion</th>
              </tr>
            </thead>
            <tbody>
            <g:each in="${categoriaList}">
            <tr>
                <td>${it.nombre}</td>
                <td>${it.costoFabricacion}</td>
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
