
<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingOne">
      <h4 class="panel-title">
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
          Pedidos de Productos
        </a>
      </h4>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
      <div class="panel-body">
        <g:render template="../pedidoProducto/grid" model="['pedidoProductoList':pedidoProductoList]"/>
      </div>
    </div>
  </div>


  <div class="panel panel-default">
    <div class="panel-heading" role="tab" id="headingTwo">
      <h4 class="panel-title">
        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
          Pedidos de Cotizacion
        </a>
      </h4>
    </div>
    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
      <div class="panel-body">
        <g:render template="../pedidoCotizacion/grid" model="['pedidoCotizacionList':pedidoCotizacionList]"/>
      </div>
    </div>
  </div>


<div class="panel panel-default">
  <div class="panel-heading" role="tab" id="headingThree">
    <h4 class="panel-title">
      <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
        Ordenes de Fabricacion
      </a>
    </h4>
  </div>
  <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
    <div class="panel-body">
          <g:render template="../pedidoCotizacion/grid" model="['pedidoCotizacionList':pedidoCotizacionList]"/>
    </div>
  </div>
</div>

</div>
