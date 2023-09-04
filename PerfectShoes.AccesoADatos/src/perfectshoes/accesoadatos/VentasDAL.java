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
import java.time.LocalDate;
import perfectshoes.entidadesdenegocio.*;

public class VentasDAL {
    static String obtenerCampos()
    {
        return "c.Id,c.IdClientes,c.Fecha";
    }
    
        private static String obtenerSelect(Ventas pVentas)
        {
            String sql;
            sql = "Select ";
            if(pVentas.getTop_aux() > 0 && 
            ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
            {
              sql += "Top " + pVentas.getTop_aux() + " ";  
            }
            sql += (obtenerCampos() + " From Ventas p");
            return sql;
        }
        private static String agregarOrderBy(Ventas pVentas)
        {
            String sql = " Order by c.Id Desc";
            if(pVentas.getTop_aux() > 0 && 
            ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
            {
               sql += "Limit " + pVentas.getTop_aux() + " ";
            }
            return sql;
        }
        public static int crear(Ventas pVentas) throws Exception
        {
            int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Insert Into Ventas(IdClientes,Fecha) Values(?)";
                try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
                {
                    st.setInt(1,pVentas.getIdClientes());
                    st.setString(2, pVentas.getFecha());
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
        public static int modificar(Ventas pVentas) throws Exception
        {
           int result;
           String sql;
           try(Connection conn = ComunDB.obtenerConexion();)
           {
                sql = "Update Ventas Set IdClientes = ?,Fecha = ?   Where Id = ?";
                try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
                {
                    ps.setInt(1, pVentas.getIdClientes());
                    ps.setString(2, pVentas.getFecha());
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
        public static int eliminar(Ventas pVentas) throws Exception
        {
            int result;
            String sql;
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                sql = "Delete From Ventas Where Id = ?";
                try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
                {
                    ps.setInt(1, pVentas.getId());
                
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
        static int asignarDatosResultSet(Ventas pVentas, ResultSet pResultSet, int pIndex) throws Exception
        {
             pIndex++;
            pVentas.setId(pResultSet.getInt(pIndex)); // index 1
            pIndex++;
            pVentas.setIdClientes(pResultSet.getInt(pIndex)); // index 2
            //MODIFICAR FECHA SI ES NECESARIO
            pVentas.setFecha(pResultSet.getString(pIndex));
            
            return pIndex;
        }
        private static void obtenerDatos(PreparedStatement pPS, ArrayList<Ventas> pVentas) throws Exception
        {
            try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
            {
                while(resultset.next())
                {
                    Ventas ventas = new Ventas();
                    asignarDatosResultSet(ventas,resultset,0);
                    pVentas.add(ventas);
                }
                resultset.close();
            }
            catch(SQLException ex)
            {
                throw ex; 
            }
        }
        public static Ventas obtenerPorId(Ventas pVentas) throws Exception
        {
            Ventas ventas = new Ventas();
            ArrayList<Ventas> venta = new ArrayList();
            try(Connection conn = ComunDB.obtenerConexion();)
            {
               String sql = obtenerSelect(pVentas);
               sql += " Where Id = ?";
               try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
               {
                 ps.setInt(1, pVentas.getId());
                 obtenerDatos(ps, venta);
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
            if(venta.size() > 0)
            {
                ventas = venta.get(0);
            }
            return ventas;
        }
        public static ArrayList<Ventas> obtenerTodos() throws Exception
        {
            Ventas ventas = new Ventas();
            ArrayList<Ventas> venta = new ArrayList();
            try(Connection conn = ComunDB.obtenerConexion();)
            {
                String sql = obtenerSelect(new Ventas());
                sql += agregarOrderBy(new Ventas());
                try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
                {
                    obtenerDatos(ps, venta);
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
            return venta;
        }
        
    static void querySelect(Ventas pVentas, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pVentas.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" p.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pVentas.getId());
            }
        }
        if(pVentas.getFecha() != null && 
           pVentas.getFecha().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" c.Nombre Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pVentas.getFecha() + "%");
            }
        }
    } 
    
    public static ArrayList<Ventas> buscar(Ventas pVentas) throws Exception
    {
        ArrayList<Ventas> venta = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pVentas);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pVentas, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pVentas);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pVentas, utilQuery);
                obtenerDatos(ps, venta);
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
        return venta;
    }   
}
