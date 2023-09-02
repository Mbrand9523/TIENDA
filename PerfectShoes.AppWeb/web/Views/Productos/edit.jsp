
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Productos" %>
<% 
    Productos productos = (Productos) request.getAttribute("productos");
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Producto</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Editar Producto</h5>
            <form action="Productos" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="accion" 
                       value="<%=request.getAttribute("accion")%>" 
                       id="txtHidden">
                <input type="hidden" name="id" value="<%=productos.getId()%>">
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtNombre" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getNombre()%>"> 
                        <label for="txtNombre">Nombre</label>
                    </div>
                </div>      
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtCantidad" name ="cantidad" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getCantidad()%>"> 
                        <label for="txtCantidad">Cantidad</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtPrecio" name ="precio" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getPrecio()%>"> 
                        <label for="txtPrecio">Precio</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtTalla" name ="talla" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getTalla()%>"> 
                        <label for="txtTalla">Talla</label>
                    </div>
                </div>
                <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Marcas/select.jsp" >
                           <jsp:param name="id" value="<%=productos.getIdMarcas()%>"/>
                       </jsp:include>
                        <span id="slMarcas_error" 
                              style="color:red;font-weight: bold" class="helper-text">
                        </span>
                </div>
                <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Proveedor/select.jsp" >
                           <jsp:param name="id" value="<%=productos.getIdProveedor()%>"/>
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
                        <a href="Productos" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">list</i>Cancelar
                        </a>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
        <script>
   
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

