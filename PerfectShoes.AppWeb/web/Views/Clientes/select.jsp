<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Clientes" %>
<%@page import="perfectshoes.accesoadatos.ClientesDAL" %>
<%@page import="java.util.ArrayList" %>

<%
    ArrayList<Clientes> cliente = ClientesDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>

<select id="slClientes" name="idClientes">
    <option <%=(id == 0) ? "selected" : ""%> value="0">Seleccionar</option>
    <% 
        for(Clientes clientes:cliente)
        {
    %>
    <option <%=(id == clientes.getId()) ? "selected" : "" %>
        value="<%=clientes.getId()%>">
        <%=clientes.getNombre()%>
    </option>
    <% } %>
</select>
<label for="slClientes">Clientes</label>
