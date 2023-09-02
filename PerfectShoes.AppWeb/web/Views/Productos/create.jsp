<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Productos" %>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Crear Producto</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Crear Producto</h5>
            <form action="Productos" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="accion" 
                       value="<%=request.getAttribute("accion")%>" id="txtHidden">
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtNombre" name ="nombre" required 
                               class="validate" maxlength="30">
                        <label for="txtNombre">Nombre</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtCantidad" name ="cantidad" required 
                               class="validate" maxlength="30">
                        <label for="txtCantidad">Cantidad</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtPrecio" name ="precio" required 
                               class="validate" maxlength="30">
                        <label for="txtPrecio">Precio</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtTalla" name ="talla" required 
                               class="validate" maxlength="30">
                        <label for="txtTalla">Talla</label>
                    </div>
                </div>
                <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Marcas/select.jsp" >
                           <jsp:param name="id" value="0"/>
                       </jsp:include>
                        <span id="slMarcas_error" 
                              style="color:red;font-weight: bold" class="helper-text">
                        </span>
                   </div>
                 <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Proveedor/select.jsp" >
                           <jsp:param name="id" value="0"/>
                       </jsp:include>
                        <span id="slProveedor_error" 
                              style="color:red;font-weight: bold" class="helper-text">
                        </span>
                   </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">save</i>Guardar
                        </button>
                        <a href="Productos" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">list</i>Cancelar
                        </a>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
        <script>
        function validarFormulario()
        {
            var result = true;
            var txtProveedor = $("#slProveedor").val();
            var txtMarcas = $("#slMarcas").val();
                
            if(parseInt(txtMarcas) == 0)
                {
                    $("#slMarcas_error").empty();
                    $("#slMarcas_error")
                    .append("La Marca el Obligatorio");
                    result = false;
                }
                else
                {
                    $("#slMarcas_error").empty();
                }
            if(parseInt(txtProveedor) == 0)
                {
                    $("#slProveedor_error").empty();
                    $("#slProveedor_error")
                    .append("El Proveedor el Obligatorio");
                    result = false;
                }
                else
                {
                    $("#slProveedor_error").empty();
                }
                return result;
        }
        </script>
    </body>
</html>

