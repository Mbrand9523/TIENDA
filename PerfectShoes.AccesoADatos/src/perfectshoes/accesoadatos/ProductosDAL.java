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

public class ProductosDAL {
 static String obtenerCampos()
    {
        return "p.id,p.idProveedor,p.Idmarca,p.nombre,p,.cantidad,p.precio,p.talla,p.";
    }
     
      private static String obtenerSelect(Productos pProductos)
    {
        String sql;
        sql = "Select ";
        if(pProductos.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pProductos.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Productos p");
        return sql;
    }
      
        private static String agregarOrderBy(Productos pProductos)
    {
        String sql = " Order by c.Id Desc";
        if(pProductos.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Limit " + pProductos.getTop_aux() + " ";
        }
        return sql;
    }
        
         public static int crear(Productos pProductos) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into Productos(IdProveedor,Idmarca,nombre,cantidad,precio,talla) Values(?)";
            try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
            {
                st.setInt(1, pProductos.getIdProveedor());
                st.setInt(2, pProductos.getIdmarca());
                st.setString(3, pProductos.getNombre());
                st.setString(4, pProductos.getCantidad());
                st.setDouble(5, pProductos.getPrecio());
                st.setString(6, pProductos.getTalla());
                st.setString(4, pProductos.getCantidad());
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
    
          public static int modificar(Productos pProductos) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Productos Set idProveedor = ? ,idMarca = ? ,Nombre = ? ,Cantidad = ? ,Precio = ? ,Talla = ?   Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pProductos.getIdProveedor());
                ps.setInt(2, pProductos.getIdmarca());
                ps.setString(3, pProductos.getNombre());
                ps.setString(4, pProductos.getCantidad());
                ps.setDouble(5, pProductos.getPrecio());
                ps.setString(6, pProductos.getTalla());
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
          
          public static int eliminar(Productos pProductos) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Productos Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pProductos.getId());
                
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
          
          static int asignarDatosResultSet(Productos pProductos, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pProductos.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pProductos.setIdProveedor(pResultSet.getInt(pIndex));
        pIndex++;
        pProductos.setIdmarca(pResultSet.getInt(pIndex));
        pIndex++;
        pProductos.setNombre(pResultSet.getString(pIndex));
        pIndex++;
        pProductos.setCantidad(pResultSet.getString(pIndex));
        pIndex++;
        pProductos.setPrecio(pResultSet.getDouble(pIndex));
        pIndex++;
        pProductos.setTalla(pResultSet.getString(pIndex));
        pIndex++;
               
        return pIndex;
    }
          
    private static void obtenerDatosIncluirProveedor(PreparedStatement pPS, ArrayList<Productos> pProductos) throws Exception {
        try (ResultSet resultSet = ComunDB.obtenerResulSet(pPS);) { 
            HashMap<Integer, Proveedor> proveedorMap = new HashMap(); 
            while (resultSet.next()) { 
                Productos productos = new Productos();
                 
                int index = asignarDatosResultSet(productos, resultSet, 0);
                if (proveedorMap.containsKey(productos.getIdProveedor()) == false) { 
                    Proveedor proveedor = new Proveedor();
                    
                    ProveedorDAL.asignarDatosResultSet(proveedor, resultSet, index);
                    proveedorMap.put(proveedor.getId(), proveedor); 
                    productos.setProveedor(proveedor); 
                } else {
                    
                    productos.setProveedor(proveedorMap.get(productos.getIdProveedor())); 
                }
                pProductos.add(productos); 
            }
            resultSet.close(); 
        } catch (SQLException ex) {
            throw ex; 
        }
    }      
    
     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Productos> pProductos) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                Productos productos = new Productos();
                asignarDatosResultSet(productos,resultset,0);
                pProductos.add(productos);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
     
      public static Productos obtenerPorId(Productos pProductos) throws Exception
    {
        Productos productos = new Productos();
        ArrayList<Productos> producto = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pProductos);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pProductos.getId());
                obtenerDatos(ps, producto);
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
        if(producto.size() > 0)
        {
            productos = producto.get(0);
        }
        return productos;
    }
      
      public static ArrayList<Productos> obtenerTodos() throws Exception
    {
        Productos productos = new Productos();
        ArrayList<Productos> producto = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Productos());
            sql += agregarOrderBy(new Productos());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, producto);
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
        
        return producto;
    }
      
      static void querySelect(Productos pProductos, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pProductos.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" p.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pProductos.getId());
            }
        }
        
        if(pProductos.getNombre() != null && 
           pProductos.getNombre().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" r.Nombre Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pProductos.getNombre() + "%");
            }
        }
    }       
        public static ArrayList<Productos> buscar(Productos pProductos) throws Exception
    {
        ArrayList<Productos> producto = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pProductos);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pProductos, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pProductos);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pProductos, utilQuery);
                obtenerDatos(ps, producto);
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
        
        return producto;
    }
}
