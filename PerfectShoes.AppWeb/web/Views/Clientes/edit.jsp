<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Clientes" %>
<% 
    Clientes clientes = (Clientes) request.getAttribute("clientes");
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Cliente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Editar Cliente</h5>
            <form action="Clientes" method="post">
                <input type="hidden" name="accion" 
                       value="<%=request.getAttribute("accion")%>" 
                       id="txtHidden">
                <input type="hidden" name="id" value="<%=clientes.getId()%>">
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtNombre" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getNombre()%>"> 
                        <label for="txtNombre">Nombre</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtApellido" name ="apellido" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getApellido()%>"> 
                        <label for="txtApellido">Apellido</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtTelefono" name ="telefono" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getTelefono()%>"> 
                        <label for="txtTelefono">Telefono</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtDireccion" name ="direccion" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getDireccion()%>"> 
                        <label for="txtDireccion">Dirección</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtDui" name ="dui" required 
                               class="validate" maxlength="30"
                               value="<%=clientes.getDui()%>"> 
                        <label for="txtDui">DUI</label>
                    </div>
                </div>
                    <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Ventas/select.jsp" >
                           <jsp:param name="id" value="<%=clientes.getIdVentas()%>"/>
                       </jsp:include>
                        <span id="slVentas_error" 
                              style="color:red;font-weight: bold" class="helper-text">
                        </span>
                   </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">save</i>Guardar
                        </button>
                        <a href="Clientes" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">list</i>Cancelar
                        </a>
                    </div>
                </div>
            </form>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
        <script>     
                if(parseInt(txtVentas) == 0)
                {
                    $("#slVentas_error").empty();
                    $("#slVentas_error")
                    .append("La Venta el Obligatorio");
                    result = false;
                }
                else
                {
                    $("#slVentas_error").empty();
                }
                return result;
            }
        </script>
    </body>
</html>

