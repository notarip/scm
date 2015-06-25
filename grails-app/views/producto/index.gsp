<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header"/>
        <g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
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
            <f:table class="table table-condensed" collection="${productoList}" />

            <div class="pagination">
                <g:paginate total="${productoCount ?: 0}" />
            </div>
        </div>
    </body>
</html>