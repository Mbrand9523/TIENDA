<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Clientes"%>
<%@page import="perfectshoes.entidadesdenegocio.Ventas"%>
<%@page import="java.util.ArrayList" %>

<%ArrayList<Clientes> cliente = (ArrayList<Clientes>) request
        .getAttribute("cliente");
    int numPage = 1;
    int numReg = 10;
    int countReg = 0;
    if(cliente == null)
    {
        cliente = new ArrayList();
    }
    else
        if(cliente.size() > numReg)
        {
            double divNumPage = (double) cliente.size() / (double) numReg;
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
        <title>Buscar Cliente</title>
    </head>
    <body>
        <jsp:include page="/Views/Shared/headerBody.jsp" /> 
<main class="container">
           <h5>Buscar Cliente</h5>
           <form action="Cliente" method="post">
               <input type="hidden" name="accion" value="<%request.getAttribute("accion");%>">
               <div class="row">
                   <div class="input-field col 14 s12">
                       <input type="text" id="txtNombre" name="nombre">
                       <label for="txtNombre">Nombre</label>
                   </div>
                   <div class="input-field col 14 s12">
                       <input type="text" id="txtApellido" name="apellido">
                       <label for="txtApellido">Apellido</label>
                   </div>
                   <div class="input-field col 14 s12">
                       <input type="text" id="txtTelefono" name="telefono">
                       <label for="txtTelefono">Telefono</label>
                   </div>
                   <div class="input-field col 14 s12">
                       <input type="text" id="txtDireccion" name="direccion">
                       <label for="txtDireccion">Dirección</label>
                   </div>
                   <div class="input-field col 14 s12">
                       <input type="text" id="txtDui" name="dui">
                       <label for="txtDui">DUI</label>
                   </div>
                   <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Ventas/select.jsp" >
                           <jsp:param name="id" value="0"/>
                       </jsp:include>
                   </div>
                   <div class="input-field col 14 s12">
                       <jsp:include page="/Views/Shared/selectTop.jsp">
                           <jsp:param name="top_aux" value="<%=top_aux%>"/>
                       </jsp:include>
                   </div>
               </div>
               <div class="row">
                   <div class="input-field col 16 s12">
                       <button type="submit" class="waves-effect waves-ligth btn blue">Buscar</button>
                       <a href="Clientes?accion=create" class="waves-effect waves-ligth btn blue">Nuevo</a>
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
                                    <th>Apellido</th>
                                    <th>Telefono</th>
                                    <th>Dirección</th>
                                    <th>DUI</th>
                                    <th>Ventas</th>                                    
                                    <th>Acciones</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                for(Clientes clientes:cliente)
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
                                        <td><%=clientes.getNombre()%></td>
                                        <td><%=clientes.getApellido()%></td>
                                        <td><%=clientes.getTelefono()%></td>
                                        <td><%=clientes.getDireccion()%></td>
                                        <td><%=clientes.getDui()%></td>                                       
                                        <td><%=clientes.getVentas().getNombre()%></td>
                                        
                                        <td>
                                            <div style="display: flex">
                                                <a href="Clientes?accion=edit&id=<%=clientes.getId()%>" 
                                                   title="Mofificar" class="waves-effect waves-light btn green">
                                                    <i class="material-icons">edit</i>
                                                </a>
                                                <a href="Clientes?accion=details&id=<%=clientes.getId()%>" 
                                                   title="Ver" class="waves-effect waves-light btn blue">
                                                    <i class="material-icons">description</i>
                                                </a>
                                                <a href="Clientes?accion=delete&id=<%=clientes.getId()%>" 
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
    </body>
</html>

