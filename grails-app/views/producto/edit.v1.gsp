<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="header">
        <g:set var="entityName" value="${message(code: 'producto.label', default: 'Producto')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-producto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-producto" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${producto}">
            <ul class="errors" role="alert">
                <g:eachError bean="${producto}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${producto}" method="PUT">
                <g:hiddenField name="version" value="${producto?.version}" />
                <fieldset class="form">
                    <!-- f:all bean="producto"/ -->
                    <g:hiddenField type="text" name="id" value="${producto?.id}"/>
                    Nombre<g:field type="text" name="nombre" value="${producto?.nombre}"/>
                    Descripción<g:field type="text" name="descripcion" value="${producto?.descripcion}"/>
                    <!-- f:all bean="producto"/ -->
                    <tr class="prop">
                        <td valign="top" class="name"><label for="books">materiales:</label></td>
                        <g:render template="materiales" model="['producto':producto]" />
                        </td>
                    </tr>                    
                </fieldset>
                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
