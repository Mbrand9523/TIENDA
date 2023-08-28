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

public class ClientesDAL {
 static String obtenerCampos()
    {
        return "c.id,c.idVentas,c.nombre,c.apellido,c.telefono,c.direccion,c.dui";
    }
     
           private static String obtenerSelect(Clientes pClientes)
    {
        String sql;
        sql = "Select ";
        if(pClientes.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pClientes.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Productos p");
        return sql;
    }
           
        private static String agregarOrderBy(Clientes pClientes)
    {
        String sql = " Order by c.Id Desc";
        if(pClientes.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Limit " + pClientes.getTop_aux() + " ";
        }
        return sql;
    }
        
       public static int crear(Clientes pClientes) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into Clientes(IdVentas,nombre,apellido,telefono,direccion,dui) Values(?)";
            try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
            {
                st.setInt(1, pClientes.getIdVentas());
                st.setString(2, pClientes.getNombre());
                st.setString(3, pClientes.getApellido());
                st.setString(4, pClientes.getTelefono());
                st.setString(5, pClientes.getDireccion());
                st.setString(6, pClientes.getDui());
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
       
        public static int modificar(Clientes pClientes) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Clientes Set idVentas = ?,Nombre = ? ,Apellido = ? ,Telefono = ? ,Direccion = ? ,Dui = ?   Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pClientes.getIdVentas());
                ps.setString(2, pClientes.getNombre());
                ps.setString(3, pClientes.getApellido());
                ps.setString(4, pClientes.getTelefono());
                ps.setString(5, pClientes.getDireccion());
                ps.setString(6, pClientes.getDui());
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
        
      public static int eliminar(Clientes pClientes) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Clientes Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pClientes.getId());
                
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
      
    static int asignarDatosResultSet(Clientes pClientes, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pClientes.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pClientes.setIdVentas(pResultSet.getInt(pIndex));
        pIndex++;
        pClientes.setNombre(pResultSet.getString(pIndex));
        pIndex++;
        pClientes.setApellido(pResultSet.getString(pIndex));
        pIndex++;
        pClientes.setTelefono(pResultSet.getString(pIndex));
        pIndex++;
        pClientes.setDireccion(pResultSet.getString(pIndex));
        pIndex++;
        pClientes.setDui(pResultSet.getString(pIndex));
        pIndex++;
               
        return pIndex;
    }
    
    private static void obtenerDatos(PreparedStatement pPS, ArrayList<Clientes> pClientes) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                Clientes clientes = new Clientes();
                asignarDatosResultSet(clientes,resultset,0);
                pClientes.add(clientes);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
    
     public static Clientes obtenerPorId(Clientes pClientes) throws Exception
    {
        Clientes clientes = new Clientes();
        ArrayList<Clientes> cliente = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pClientes);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pClientes.getId());
                obtenerDatos(ps, cliente);
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
        if(cliente.size() > 0)
        {
            clientes = cliente.get(0);
        }
        return clientes;
    }
     
     public static ArrayList<Clientes> obtenerTodos() throws Exception
    {
        Clientes clientes = new Clientes();
        ArrayList<Clientes> cliente = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Clientes());
            sql += agregarOrderBy(new Clientes());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, cliente);
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
        
        return cliente;
    }
     
    static void querySelect(Clientes pClientes, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pClientes.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" p.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pClientes.getId());
            }
        }
        
        if(pClientes.getNombre() != null && 
           pClientes.getNombre().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" c.Nombre Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pClientes.getNombre() + "%");
            }
        }
    } 
    
    public static ArrayList<Clientes> buscar(Clientes pClientes) throws Exception
    {
        ArrayList<Clientes> cliente = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pClientes);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pClientes, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pClientes);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pClientes, utilQuery);
                obtenerDatos(ps, cliente);
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
        
        return cliente;
    }   
}
