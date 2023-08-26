/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perfectshoes.accesoadatos;

/**
 *
 * @author  Carmen Estela
 */
import java.sql.*;
import java.util.*;
import perfectshoes.entidadesdenegocio.Usuario;
import perfectshoes.entidadesdenegocio.Rol;
import perfectshoes.entidadesdenegocio.Proveedor;

public class ProveedorDAL {
     public static String encriptarMD5(String txt) throws Exception 
    {
     try {
          StringBuffer sb;
          java.security.MessageDigest md = java.security.MessageDigest
                .getInstance("MD5");
          byte[] array = md.digest(txt.getBytes());
          sb = new StringBuffer();
          for (int i = 0; i < array.length; ++i) 
          {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
          .substring(1, 3));
         }
         return sb.toString();
        } catch (java.security.NoSuchAlgorithmException ex) {
        throw ex;
        }
    }
     static String obtenerCampos()
    {
        return "u.Id, u.Nombre, u.Apellido, u.razon_social"
                + "u.tellefono, u.direccion";
    }
    
     private static String obtenerSelect(Proveedor pProveedor)
    {
        String sql;
        sql = "Select ";
        if(pProveedor.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pProveedor.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Proveedor p");
        return sql;
    }
    private static String agregarOrderBy(Proveedor pProveedor)
    {
        String sql = " Order by p.Id Desc";
        if(pProveedor.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.MYSQL)
        {
            sql += "Limit " + pProveedor.getTop_aux() + " ";
        }
        return sql;
    }
   
    public static int crear(Proveedor pProveedor) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into Proveedor (Nombre) Values(?)";
            try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
            {
                st.setString(1, pProveedor.getNombre());
                st.setString(2, pProveedor.getApellido());
                st.setString(3, pProveedor.getRazon_social());
                st.setString(4, pProveedor.getTelefono());
                st.setString(5, pProveedor.getDireccion());
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
    public static int modificar(Proveedor pProveedor) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Proveedor Set Nombre = ? Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setString(1, pProveedor.getNombre());
                ps.setInt(2, pProveedor.getId());
                ps.setString(3, pProveedor.getApellido());
                ps.setString(4, pProveedor.getRazon_social());
                ps.setString(5, pProveedor.getTelefono());
                ps.setString(6, pProveedor.getDireccion());
                
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
    public static int eliminar(Proveedor pProveedor) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Proveedor Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pProveedor.getId());
                ps.setString(2, pProveedor.getNombre());
                ps.setString(3, pProveedor.getApellido());
                ps.setString(4, pProveedor.getRazon_social());
                ps.setString(5, pProveedor.getTelefono());
                ps.setString(6, pProveedor.getDireccion());
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
     static int asignarDatosResultSet(Proveedor pProveedor, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pProveedor.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pProveedor.setNombre(pResultSet.getString(pIndex));
        pIndex++;
        pProveedor.setApellido(pResultSet.getString(pIndex));
        pIndex++;
        pProveedor.setRazon_social(pResultSet.getString(pIndex));
        pIndex++;
        pProveedor.setTelefono(pResultSet.getString(pIndex));
        pIndex++;
        pProveedor.setDireccion(pResultSet.getString(pIndex));
        return pIndex;
    }
     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Proveedor> pProveedor) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                Proveedor proveedor = new Proveedor();
                asignarDatosResultSet(proveedor,resultset,0);
                pProveedor.add(proveedor);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
     public static Proveedor obtenerPorId(Proveedor pProveedor) throws Exception
    {
        Proveedor proveedor = new Proveedor();
        ArrayList<Proveedor> proveedores = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pProveedor);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pProveedor.getId());
                obtenerDatos(ps, proveedores);
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
        if(proveedores.size() > 0)
        {
            proveedor = proveedores.get(0);
        }
        return proveedor;
    }
     public static ArrayList<Proveedor> obtenerTodos() throws Exception
    {
        Proveedor proveedor = new Proveedor();
        ArrayList<Proveedor> proveedores = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Proveedor());
            sql += agregarOrderBy(new Proveedor());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, proveedores);
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
        
        return proveedores;
    }
     static void querySelect(Proveedor pProveedor, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pProveedor.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" p.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pProveedor.getId());
            }
        }
        
        if(pProveedor.getNombre() != null && 
           pProveedor.getNombre().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" p.Nombre Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pProveedor.getNombre() + "%");
            }
        }
    }
    
   public static ArrayList<Proveedor> buscar(Proveedor pProveedor) throws Exception
    {
        ArrayList<Proveedor> proveedores = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pProveedor);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pProveedor, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pProveedor);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pProveedor, utilQuery);
                obtenerDatos(ps, proveedores);
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
        
        return proveedores;
    }
}
