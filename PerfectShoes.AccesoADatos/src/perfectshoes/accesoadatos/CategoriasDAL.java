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

public class CategoriasDAL {
    
     static String obtenerCampos()
    {
        return "c.Id,c.IdProductos,c.nombrescategorias,c.productos";
    }
     
      private static String obtenerSelect(Categorias pCategorias)
    {
        String sql;
        sql = "Select ";
        if(pCategorias.getTop_aux() > 0 && 
           ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Top " + pCategorias.getTop_aux() + " ";
        }
        sql += (obtenerCampos() + " From Categorias c");
        return sql;
    }
      
      private static String agregarOrderBy(Categorias pCategorias)
    {
        String sql = " Order by c.Id Desc";
        if(pCategorias.getTop_aux() > 0 && 
        ComunDB.TIPODB == ComunDB.TipoDB.SQLSERVER)
        {
            sql += "Limit " + pCategorias.getTop_aux() + " ";
        }
        return sql;
    }
      
       public static int crear(Categorias pCategorias) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Insert Into Categorias(idProductos,nombrescategorias,productos) Values(?)";
            try(PreparedStatement st = 
                ComunDB.createPreparedStatement(conn, sql);)
            {
                st.setInt(1, pCategorias.getIdProductos());
                st.setString(2, pCategorias.getNombrescategorias());
                st.setString(3, pCategorias.getProductos());      
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
       
        public static int modificar(Categorias pCategorias) throws Exception 
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Update Categorias Set idProductos = ? ,nombrescategorias = ?  Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pCategorias.getIdProductos());
                ps.setString(2, pCategorias.getNombrescategorias());
                ps.setString(3, pCategorias.getProductos());
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
         
    public static int eliminar(Categorias pCategorias) throws Exception
    {
        int result;
        String sql;
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            sql = "Delete From Categorias Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pCategorias.getId());
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
    
    static int asignarDatosResultSet(Categorias pCategorias, ResultSet pResultSet, int pIndex) throws Exception
    {
        pIndex++;
        pCategorias.setId(pResultSet.getInt(pIndex));
        pIndex++;
        pCategorias.setIdProductos(pResultSet.getInt(pIndex));
        pIndex++;
        pCategorias.setNombrescategorias(pResultSet.getString(pIndex));
        pIndex++;
        pCategorias.setProductos(pResultSet.getString(pIndex));

        return pIndex;
    }
    
     private static void obtenerDatos(PreparedStatement pPS, ArrayList<Categorias> pCategorias) throws Exception
    {
        try(ResultSet resultset = ComunDB.obtenerResulSet(pPS);)
        {
            while(resultset.next())
            {
                Categorias categorias = new Categorias();
                asignarDatosResultSet(categorias,resultset,0);
                pCategorias.add(categorias);
            }
            resultset.close();
        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
     
     public static Categorias obtenerPorId(Categorias pCategorias) throws Exception
    {
        Categorias categorias = new Categorias();
        ArrayList<Categorias> categoria = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pCategorias);
            sql += " Where Id = ?";
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                ps.setInt(1, pCategorias.getId());
                obtenerDatos(ps, categoria);
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
        if(categoria.size() > 0)
        {
            categorias = categoria.get(0);
        }
        return categorias;
    }
      
     public static ArrayList<Categorias> obtenerTodos() throws Exception
    {
        Categorias categorias = new Categorias();
        ArrayList<Categorias> categoria = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(new Categorias());
            sql += agregarOrderBy(new Categorias());
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                obtenerDatos(ps, categoria);
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
        
        return categoria;
    }     
        
       static void querySelect(Categorias pCategorias, ComunDB.UtilQuery pUtilQuery) throws Exception
    {
        PreparedStatement statement = pUtilQuery.getStatement();
        if(pCategorias.getId() > 0)
        {
            pUtilQuery.AgregarWhereAnd(" c.Id = ? ");
            if(statement != null)
            {
                statement.setInt(pUtilQuery.getNumWhere(), 
                        pCategorias.getId());
            }
        }
        
        if(pCategorias.getNombrescategorias()!= null && 
           pCategorias.getNombrescategorias().trim().isEmpty() == false)
        {
            pUtilQuery.AgregarWhereAnd(" c.nombrescategorias Like ? ");
            if(statement != null)
            {
                statement.setString(pUtilQuery.getNumWhere(), 
                        "%" + pCategorias.getNombrescategorias()+ "%");
            }
        }
    }
       
       public static ArrayList<Categorias> buscar(Categorias pCategorias) throws Exception
    {
        ArrayList<Categorias> categorias = new ArrayList();
        try(Connection conn = ComunDB.obtenerConexion();)
        {
            String sql = obtenerSelect(pCategorias);
            ComunDB comundb = new ComunDB();
            ComunDB.UtilQuery utilQuery = 
            comundb.new UtilQuery(sql,null,0);
            querySelect(pCategorias, utilQuery);
            sql = utilQuery.getSQL();
            sql += agregarOrderBy(pCategorias);
            try(PreparedStatement ps = ComunDB.createPreparedStatement(conn, sql);)
            {
                utilQuery.setStatement(ps);
                utilQuery.setSQL(null);
                utilQuery.setNumWhere(0);
                querySelect(pCategorias, utilQuery);
                obtenerDatos(ps, categorias);
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
        
        return categorias;
    }
    
    
}
