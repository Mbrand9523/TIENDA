/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perfectshoes.accesoadatos;

/**
 *
 * @author Carmen Estela
 */
import java.util.*;
import java.sql.*;
import perfectshoes.entidadesdenegocio.*;

public class DetalleVentaDAL {
    static String obtenerCampos()
    {
       return "p.id,p.idVentas,p.idProductos,p.idCategorías,p,.Nombre,p.Marca,p.Cantidad,p.Precio,p.Subtotal,p.Total"; 
    }
    
      private static String obtenerSelect(DetalleVenta pDetalleVenta)
    {
        String sql;
        sql = "Select ";
        if(pDetalleVenta.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
        sql += "Top " + pDetalleVenta.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Productos p");
        return sql;
    }
          private static String agregarOrderBy(DetalleVenta pDetalleVenta)
    {
        String sql = " Order by c.Id Desc";
        if(pDetalleVenta.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Limit " + pDetalleVenta.getTop_aux() + " ";
        }
        return sql;
    }
          public static int crear(DetalleVenta pDetalleVenta) throws Exception
    {
         int result;
         String sql;
         try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into Productos(IdVentas,IdProductos,IdCategorías,Nombre,Marca,Cantidad,Precio,Subtotal,Total) Values(?)";
            try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
            {
                st.setInt(1, pDetalleVenta.getIdVentas());
                st.setInt(2, pDetalleVenta.getIdProductos());
                st.setInt(3, pDetalleVenta.getIdCategorias());
                st.setString(4, pDetalleVenta.getNombre());
                st.setString(5, pDetalleVenta.getMarca());
                st.setDouble(6, pDetalleVenta.getCantidad());
                st.setDouble(7, pDetalleVenta.getPrecio());
                st.setDouble(8, pDetalleVenta.getSubtotal());
                st.setDouble(9, pDetalleVenta.getTotal());
                result = st.executeUpdate();
                st.close();
            }
            catch(SQLException ex)
            {
                throw ex;
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        return result;
        }
          
              public static int modificar(DetalleVenta pDetalleVenta) throws Exception
        {
            int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Productos(IdVentas,IdProductos,IdCategorías,Nombre,Marca,Cantidad,Precio,Subtotal,Total) Values(?)";
                try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
                {
                    ps.setInt(1, pDetalleVenta.getIdVentas());
                    ps.setInt(2, pDetalleVenta.getIdProductos());
                    ps.setInt(3, pDetalleVenta.getIdCategorias());
                    ps.setString(4, pDetalleVenta.getNombre());
                    ps.setString(5, pDetalleVenta.getMarca());
                    ps.setDouble(6, pDetalleVenta.getCantidad());
                    ps.setDouble(7, pDetalleVenta.getPrecio());
                    ps.setDouble(8, pDetalleVenta.getSubtotal());
                    ps.setDouble(9, pDetalleVenta.getTotal());
                    result = ps.executeUpdate();
                    ps.close();
                }
                catch(Exception ex)
                {
                     throw ex;
                }
            }
            catch(SQLException ex)
            {
                throw ex;
            }
            return result;
    }
            public static int eliminar(DetalleVenta pDetalleVenta) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Productos Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pDetalleVenta.getId());
                
                result = ps.executeUpdate();
                ps.close();
            }
            catch(SQLException ex)
            {
                throw ex;
            }
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        return result;
    }
            
        
            static int asignarDatosResultSet(DetalleVenta pDetalleVenta, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pDetalleVenta.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pDetalleVenta.setIdVentas(pResultSet.getInt(pIndex)); // index 1
        pIndex++;
        pDetalleVenta.setIdProductos(pResultSet.getInt(pIndex)); // index 2
        pIndex++;
        pDetalleVenta.setIdCategorias(pResultSet.getInt(pIndex)); // index 3
        pIndex++;
        pDetalleVenta.setNombre(pResultSet.getString(pIndex)); // index 4
        pIndex++;
        pDetalleVenta.setMarca(pResultSet.getString(pIndex)); // index 5
        pIndex++;
        pDetalleVenta.setCantidad(pResultSet.getDouble(pIndex)); // index 6
        pIndex++;
        pDetalleVenta.setPrecio(pResultSet.getDouble(pIndex)); // index 7
        pIndex++;
        pDetalleVenta.setSubtotal(pResultSet.getDouble(pIndex)); // index 8
        pIndex++;
        pDetalleVenta.setTotal(pResultSet.getDouble(pIndex)); // index 9
        pIndex++;
               
        return pIndex;
    }
        private static void obtenerDatosIncluirVentas(PreparedStatement pPS, ArrayList<DetalleVenta> pDetalleVenta) throws Exception{
            try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);){
                HashMap<Integer, Ventas> ventasMap = new HashMap(); 
                while (resultSet.next()){
                  DetalleVenta detalleVenta = new DetalleVenta();
                  
                    int index = asignarDatosResultSet(detalleVenta, resultSet, 0);
                    if (ventasMap.containsKey(detalleVenta.getIdVentas()) == false) { 
                        Ventas ventas = new Ventas();
                    
                        VentasDAL.asignarDatosResultSet(ventas, resultSet, index);
                        ventasMap.put(ventas.getId(), ventas); 
                        detalleVenta.setVentas(ventas); 
                    }else {
                        detalleVenta.setVentas(ventasMap.get(detalleVenta.getIdVentas()));
                    }
                    pDetalleVenta.add(detalleVenta); 
                }
                resultSet.close();
            } catch (SQLException ex){
                throw ex;
            }
        }
        
        private static void obtenerDatosIncluirProductos(PreparedStatement pPS, ArrayList<DetalleVenta> pDetalleVenta) throws Exception{
            try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);){
                HashMap<Integer, Productos> productosMap = new HashMap(); 
                while (resultSet.next()){
                  DetalleVenta detalleVenta = new DetalleVenta();
                  
                    int index = asignarDatosResultSet(detalleVenta, resultSet, 0);
                    if (productosMap.containsKey(detalleVenta.getIdProductos()) == false) { 
                        Productos productos = new Productos();
                    
                        ProductosDAL.asignarDatosResultSet(productos, resultSet, index);
                        productosMap.put(productos.getId(), productos); 
                        detalleVenta.setProductos(productos); 
                    }else {
                        detalleVenta.setProductos(productosMap.get(detalleVenta.getIdProductos()));
                    }
                    pDetalleVenta.add(detalleVenta); 
                }
                resultSet.close();
            } catch (SQLException ex){
                throw ex;
            }
        }
        private static void obtenerDatosIncluirCategoria(PreparedStatement pPS, ArrayList<DetalleVenta> pDetalleVenta) throws Exception{
            try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);){
                HashMap<Integer, Categorias> categoriaMap = new HashMap(); 
                while (resultSet.next()){
                  DetalleVenta detalleVenta = new DetalleVenta();
                  
                    int index = asignarDatosResultSet(detalleVenta, resultSet, 0);
                    if (categoriaMap.containsKey(detalleVenta.getIdCategorias()) == false) { 
                        Categorias categoria = new Categorias();
                    
                        CategoriasDAL.asignarDatosResultSet(categoria, resultSet, index);
                        categoriaMap.put(detalleVenta.getId(), categoria); 
                        detalleVenta.setCategoria(categoria); 
                    }else {
                        detalleVenta.setCategoria(categoriaMap.get(detalleVenta.getIdProductos()));
                    }
                    pDetalleVenta.add(detalleVenta); 
                }
                resultSet.close();
            }   catch (SQLException ex){
                throw ex;
            }
        }
        
        private static void obtenerDatos(PreparedStatement pPS, ArrayList<DetalleVenta> pDetalleVenta) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                DetalleVenta detalleVenta = new DetalleVenta();
                asignarDatosResultSet(detalleVenta,resultset,0);
                pDetalleVenta.add(detalleVenta);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
}
