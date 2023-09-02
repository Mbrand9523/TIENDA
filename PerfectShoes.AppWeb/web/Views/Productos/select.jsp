<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Productos" %>
<%@page import="perfectshoes.accesoadatos.ProductosDAL" %>
<%@page import="java.util.ArrayList" %>

<%
    ArrayList<Productos> producto = ProductosDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slProductos" name="idProductos">
    <option <%=(id == 0) ? "selected" : ""%> value="0">Seleccionar</option>
    <% 
        for(Productos productos:producto)
        {
    %>
    <option <%=(id == productos.getId()) ? "selected" : "" %>
        value="<%=productos.getId()%>">
        <%=productos.getNombre()%>
    </option>
    <% } %>
</select>
<label for="slProductos">Productos</label>


