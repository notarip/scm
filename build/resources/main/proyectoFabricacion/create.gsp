<!DOCTYPE html>
<html>
    <%@ page import="scm.*"%>
    <head>
        <meta name="layout" content="header"/>
        <title>scm</title>
        <g:set var="entityName" value="${message(code: 'proyectoFabricacion.label', default: 'Movimiento CC Producto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <script>
            $(function() {
                $( "#datepicker_desde" ).datepicker();
                $( "#datepicker_hasta" ).datepicker();
                $("[id^=datepicker]").datepicker( "option", "dateFormat", "dd/mm/yy" );
                $("[id^=datepicker]").datepicker({ minDate: -20, maxDate: "+1M +10D" });
                //$("#accordion").accordion({active: 1});
                $( "#crearSesion" ).click(function() {
                    //postear la sesion
                    //agregar una linea de accion
                    alert(this);
                });
            });
        </script>
    <div class="container" role="navigation">
        <div class="crud-table panel panel-default">
            <div class="panel-heading">
                <h4 class="panel-title">Proyecto Fabricación</h4>
            </div>
                 <g:form  action="save">
                 <table>
                    <g:hiddenField name="version" value="${proyectoFabricacion?.version}" />
                    <g:hiddenField type="text" name="id" value="${proyectoFabricacion?.id}"/>
                    <fieldset class="form">
                        <tr class="prop">
                            <td valign="top" class="name">Nombre</td>
                            <td valign="top" class="value">
                            <g:field name='nombre'  value='' type='text'/>
                            </td>
                            <td valign="top" class="name">Producto</td>
                            <td valign="top" class="value">
                            <g:select name="idProducto" class='form-control select-material' from="${productos}" value="" optionKey="id" />
                            <td valign="top" class="name">Cantidad</td>
                            <td valign="top" class="value"><g:field name='cantidad' min="0" value='' type='number'/></td>
                            </td>
                            <td valign="top" class="name">Fecha Estimada</td>
                            <td><input type="text" name="fecha" id="datepicker_desde"></td>
                            </td>
                        </tr>

                        <g:link class="btn-guardar btn btn-info" action="index">${message(code: 'default.button.cancel.label', default: 'Cancelar')}</g:link>                        
                        <input class="btn-guardar btn btn-success" type="submit" value="${message(code: 'default.button.save.label', default: 'Guardar')}" />
                    </fieldset>
                    </table>
                </g:form>
        </div>
    </div>
        <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
        </g:if>
        </div>
    </body>
</html>