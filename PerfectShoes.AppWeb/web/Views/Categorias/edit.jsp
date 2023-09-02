<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Categorias" %>
<% 
    Categorias categorias = (Categorias) request.getAttribute("categorias");
%>

<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Editar Categoria</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Editar Categoria</h5>            
            <form action="Categorias" method="post" onsubmit="return validarFormulario()">
                value="<%=request.getAttribute("accion")%>" 
                    id="txtHidden">
                <input type="hidden" name="id" value="<%=categorias.getId()%>">
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" id="txtNombre" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=categorias.getNombrescategorias()%>"> 
                        <label for="txtNombre">Nombre</label>
                    </div>
                </div>
                    <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Productos/select.jsp" >
                           <jsp:param name="id" value="<%=categorias.getIdProductos()%>"/>
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

