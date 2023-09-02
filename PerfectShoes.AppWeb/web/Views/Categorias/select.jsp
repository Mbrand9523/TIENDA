<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="perfectshoes.entidadesdenegocio.Categorias" %>
<%@page import="perfectshoes.accesoadatos.CategoriasDAL" %>
<%@page import="java.util.ArrayList" %>

<%
    ArrayList<Categorias> categoria = CategoriasDAL.obtenerTodos();
    int id = Integer.parseInt(request.getParameter("id"));
%>
<select id="slCategorias" name="idCategorias">
    <option <%=(id == 0) ? "selected" : ""%> value="0">Seleccionar</option>
    <% 
        for(Categorias categorias:categoria)
        {
    %>
    <option <%=(id == categorias.getId()) ? "selected" : "" %>
        value="<%=categorias.getId()%>">
        <%=categorias.getNombrescategorias()%>
    </option>
    <% } %>
</select>
<label for="slCategorias">Categorias</label>
