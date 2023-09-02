<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Productos" %>
<% 
    Productos productos = (Productos) request.getAttribute("productos");
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Producto</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Eliminar Producto</h5>
            <form action="Productos" method="post">
                <input type="text" name="accion" 
                       value="<%=request.getAttribute("accion")%>" 
                       id="txtHidden">
                <input type="hidden" name="id" value="<%=productos.getId()%>">
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtNombre" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getNombre()%>"> 
                        <label for="txtNombre">Nombre</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtCantidad" name ="cantidad" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getCantidad()%>"> 
                        <label for="txtCantidad">Cantidad</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtPrecio" name ="precio" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getPrecio()%>"> 
                        <label for="txtPrecio">Precio</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtTalla" name ="talla" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getTalla()%>"> 
                        <label for="txtTalla">Talla</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtMarcas" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getMarcas().getNombre()%>" disabled> 
                        <label for="txtNombre">Marca</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtProveedor" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=productos.getProveedor().getNombre()%>" disabled> 
                        <label for="txtNombre">Proveedor</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">delete</i>Eliminar
                        </button>
                        <a href="Productos" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">list</i>Cancelar
                        </a>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />

    </body>
</html>

