<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Clientes" %>
<% 
    Clientes clientes = (Clientes) request.getAttribute("clientes");
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Cliente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Eliminar Cliente</h5>
            <form action="Clientes" method="post">
                <input type="text" name="accion" 
                       value="<%=request.getAttribute("accion")%>" 
                       id="txtHidden">
                <input type="hidden" name="id" value="<%=clientes.getId()%>">
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtNombre" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getNombre()%>"> 
                        <label for="txtNombre">Nombre</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtApellido" name ="apellido" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getApellido()%>"> 
                        <label for="txtApellido">Apellido</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtTelefono" name ="telefono" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getTelefono()%>"> 
                        <label for="txtTelefono">Telefono</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtDireccion" name ="direccion" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getDireccion()%>"> 
                        <label for="txtDireccion">Dirección</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtDui" name ="dui" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getDui()%>"> 
                        <label for="txtDui">DUI</label>
                    </div>
                </div>
                <div class="input-field col 14 s12">
                       <input type="text" id="txtVentas" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getVentas().getNombre()%>" disabled>
                        <label for="txtNombre">Ventas</label>
                   </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">delete</i>Eliminar
                        </button>
                        <a href="Clientes" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">list</i>Cancelar
                        </a>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>

