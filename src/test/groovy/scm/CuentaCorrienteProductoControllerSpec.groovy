package scm

import grails.test.mixin.*
import spock.lang.*

@TestFor(CuentaCorrienteProductoController)
@Mock(CuentaCorrienteProducto)
class CuentaCorrienteProductoControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.cuentaCorrienteProductoList
            model.cuentaCorrienteProductoCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.cuentaCorrienteProducto!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def cuentaCorrienteProducto = new CuentaCorrienteProducto()
            cuentaCorrienteProducto.validate()
            controller.save(cuentaCorrienteProducto)

        then:"The create view is rendered again with the correct model"
            model.cuentaCorrienteProducto!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            cuentaCorrienteProducto = new CuentaCorrienteProducto(params)

            controller.save(cuentaCorrienteProducto)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/cuentaCorrienteProducto/show/1'
            controller.flash.message != null
            CuentaCorrienteProducto.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def cuentaCorrienteProducto = new CuentaCorrienteProducto(params)
            controller.show(cuentaCorrienteProducto)

        then:"A model is populated containing the domain instance"
            model.cuentaCorrienteProducto == cuentaCorrienteProducto
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def cuentaCorrienteProducto = new CuentaCorrienteProducto(params)
            controller.edit(cuentaCorrienteProducto)

        then:"A model is populated containing the domain instance"
            model.cuentaCorrienteProducto == cuentaCorrienteProducto
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/cuentaCorrienteProducto/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def cuentaCorrienteProducto = new CuentaCorrienteProducto()
            cuentaCorrienteProducto.validate()
            controller.update(cuentaCorrienteProducto)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.cuentaCorrienteProducto == cuentaCorrienteProducto

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            cuentaCorrienteProducto = new CuentaCorrienteProducto(params).save(flush: true)
            controller.update(cuentaCorrienteProducto)

        then:"A redirect is issued to the show action"
            cuentaCorrienteProducto != null
            response.redirectedUrl == "/cuentaCorrienteProducto/show/$cuentaCorrienteProducto.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/cuentaCorrienteProducto/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def cuentaCorrienteProducto = new CuentaCorrienteProducto(params).save(flush: true)

        then:"It exists"
            CuentaCorrienteProducto.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(cuentaCorrienteProducto)

        then:"The instance is deleted"
            CuentaCorrienteProducto.count() == 0
            response.redirectedUrl == '/cuentaCorrienteProducto/index'
            flash.message != null
    }
}
