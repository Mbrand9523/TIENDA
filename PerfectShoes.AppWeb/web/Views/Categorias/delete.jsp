<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Categorias" %>
<%
    Categorias categorias = (Categorias) request.getAttribute("categorias");
%>

<!DOCTYPE html>
<html>
    <head>
           <jsp:include page="/Views/Shared/title.jsp" />
        <title>Eliminar Categorias</title>
    </head>
    <body>
            <jsp:include page="/Views/Shared/headerBody.jsp" />
        <main>
            <h5>Eliminar Categoria</h5>
            <form action="Categoria" method="post">
                <input type="text" name="accion" 
                       value="<%=request.getAttribute("accion")%>" 
                       id="txtHidden">
                <input type="hidden" name="id" value="<%=categorias.getId()%>">
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtNombre" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=categorias.getNombrescategorias()%>"> 
                        <label for="txtNombre">Nombre</label>
                    </div>
                </div>
                <div class="row">
                    <div class="input-field col 14 s12">
                        <input type="text" disabled id="txtProductos" name ="nombre" required 
                               class="validate" maxlength="30"
                               value="<%=categorias.getProductos()%>"> 
                        <label for="txtNombre">Productos</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col 112 s12">
                        <button type="submit" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">delete</i>Eliminar
                        </button>
                        <a href="Categorias" class="waves-effect waves-light btn blue">
                            <i class="material-icons right">list</i>Cancelar
                        </a>
                    </div>
                </div>
            </form>            
        </main>
     
         </head>
        <body>
            <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>

