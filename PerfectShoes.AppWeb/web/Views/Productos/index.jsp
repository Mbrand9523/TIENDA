<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Productos"%>
<%@page import="perfectshoes.entidadesdenegocio.Marca"%>
<%@page import="perfectshoes.entidadesdenegocio.Proveedor"%>
<%@page import="java.util.ArrayList" %>

<%ArrayList<Productos> producto = (ArrayList<Productos>) request.getAttribute("producto");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if(producto == null)
    {
        producto = new ArrayList();
    }
    else
        if(producto.size() > numReg)
        {
            double divNumPage = (double) producto.size() / (double) numReg;
            numPage = (int) Math.ceil(divNumPage);
        }
    String strTop_aux = request.getParameter("top_aux");
    int top_aux = 10;
    if(strTop_aux != null && strTop_aux.trim().length() > 0)
    {
        top_aux = Integer.parseInt(strTop_aux);
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="/Views/Shared/title.jsp" />
        <title>Buscar Producto</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" /> 
        <main class="container">
           <h5>Buscar Producto</h5>
           <form action="Productos" method="post">
               <input type="hidden" name="accion" value="<%request.getAttribute("accion");%>">
               <div class="row">
                   <div class="input-field col 16 s12">
                       <input type="text" id="txtNombre" name="nombre">
                       <label for="txtNombre">Nombre</label>
                   </div>
                   <div class="input-field col 13 s12">
                       <jsp:include page="/Views/Shared/selectTop.jsp">
                           <jsp:param name="top_aux" value="<%=top_aux%>"/>
                       </jsp:include>
                   </div>
                   <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Marcas/select.jsp" >
                           <jsp:param name="id" value="0"/>
                       </jsp:include>
                   </div>
                   <div class="input-field col 14 s12">
                       <jsp:include page="/Views/proveedor/selectTop.jsp">
                           <jsp:param name="top_aux" value="<%=top_aux%>"/>
                       </jsp:include>
                   </div>
               </div>
               <div class="row">
                   <div class="input-field col 16 s12">
                       <button type="submit" class="waves-effect waves-ligth btn blue">Buscar</button>
                       <a href="Productos?accion=create" class="waves-effect waves-ligth btn blue">Nuevo</a>
                   </div>
               </div>
           </form>
               
            <div class="row">
                <div class="col 112 s12">
                    <div style="overflow: auto;">
                        <table class="paginationjs">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Cantidad</th>
                                    <th>Precio</th>
                                    <th>Talla</th>
                                    <th>Marcas</th>
                                    <th>Proveedor</th>                                                                        
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                for(Productos productos:producto)
                                {
                                   int tempNumPage = numPage;
                                   if(numPage > 1)
                                   {
                                        countReg++;
                                        double divTempNumPage = (double) countReg / (double) numReg;
                                        tempNumPage = (int) Math.ceil(divTempNumPage);
                                   }
                                %>
                                    <tr data-page="<%=tempNumPage%>">
                                        <td><%=productos.getNombre()%></td>
                                        <td><%=productos.getCantidad()%></td>
                                        <td><%=productos.getPrecio()%></td>
                                        <td><%=productos.getTalla()%></td>
                                        <td><%=productos.getMarcas().getNombre()%></td>
                                        <td><%=productos.getProveedor().getNombre()%></td>
                                        
                                        <td>
                                            <div style="display: flex">
                                                <a href="Productos?accion=edit&id=<%=productos.getId()%>" 
                                                   title="Mofificar" class="waves-effect waves-light btn green">
                                                    <i class="material-icons">edit</i>
                                                </a>
                                                <a href="Productos?accion=details&id=<%=productos.getId()%>" 
                                                   title="Ver" class="waves-effect waves-light btn blue">
                                                    <i class="material-icons">description</i>
                                                </a>
                                                <a href="Productos?accion=delete&id=<%=productos.getId()%>" 
                                                   title="Eliminar" class="waves-effect waves-light btn red">
                                                    <i class="material-icons">delete</i>
                                                </a>
                                            </div>
                                        </td>
                                    </tr> 
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col 112 s12">
                    <jsp:include page="/Views/Shared/paginacion.jsp">
                        <jsp:param name="numPage" value="<%=numPage%>"/>
                    </jsp:include> 
                </div>
            </div>
        </main>
        <jsp:include page="/Views/Shared/footerBody.jsp" />
    </body>
</html>

