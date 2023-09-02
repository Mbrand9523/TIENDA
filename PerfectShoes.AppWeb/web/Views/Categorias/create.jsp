<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Categorias"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Categoria</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Crear Categoria</h5>
            <form action="Categorias" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="accion" >
                   value="<%=request.getAttribute("accion")%>" id="txtHidden">
                       <div class="row">
                        <div class="input-field col 14 s12">
                            <input type="text" id="txtNombre" name ="nombre" required 
                                   class="validate" maxlength="30">
                            <label for="txtNombre">Nombre</label>
                        </div>
                       </div>
                       <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Productos/select.jsp" >
                           <jsp:param name="id" value="0"/>
                       </jsp:include>
                        <span id="slProductos_error" 
                              style="color:red;font-weight: bold" class="helper-text">
                        </span>
                   </div>
                         <div class="row">
                        <div class="col 112 s12">
                            <button type="submit" class="waves-effect waves-light btn blue">
                                <i class="material-icons right">save</i>Guardar
                            </button>
                            <a href="Categorias" class="waves-effect waves-light btn blue">
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
            var txtProductos = $("#slProductos").val();
                
            if(parseInt(txtProductos) == 0)
                {
                    $("#slProductos_error").empty();
                    $("#slProductos_error")
                    .append("El Producto el Obligatorio");
                    result = false;
                }
                else
                {
                    $("#slProductos_error").empty();
                }
                return result;
        }
        </script>
    </body>
</html>

