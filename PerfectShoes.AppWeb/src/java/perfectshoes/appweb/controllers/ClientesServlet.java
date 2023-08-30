/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package perfectshoes.appweb.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import perfectshoes.accesoadatos.ClientesDAL;
import perfectshoes.entidadesdenegocio.Clientes;
import perfectshoes.appweb.utils.*;

/**
 *
 * @author Carmen Estela
 */
@WebServlet(name = "ClientesServlet", urlPatterns = {"/ClientesServlet"})
public class ClientesServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   private Clientes obtenerClientes(HttpServletRequest request)
        {
            String accion = Utilidad.getParameter(request, "accion", "index");
            Clientes clientes = new Clientes();
            clientes.setNombre(Utilidad.getParameter(request, "nombre", 
                    ""));
            clientes.setApellido(Utilidad.getParameter(request, "apellido", 
                    ""));
            clientes.setTelefono((Utilidad.getParameter(request, "telefono", 
                    "")));
            clientes.setDireccion(Utilidad.getParameter(request, "dirección", 
                    ""));
            clientes.setIdVentas(Integer.parseInt(Utilidad.getParameter(request,
                    "idProveedor",
                    "0")));

            if(accion.equals("create") == false)
            {
                //Obtiene el parametro de Id del request y asigna el valor a la propiedad 
                //Id de la instancia
                clientes.setId(Integer.parseInt(Utilidad.getParameter(request, "id",
                        "0")));
            }
            clientes.setNombre(Utilidad.getParameter(request, "nombre", ""));
            if(accion.equals("index"))
            {
                clientes.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                        "top_aux", "10")));
                clientes.setTop_aux(clientes.getTop_aux() == 0 ? Integer.MAX_VALUE: clientes.getTop_aux());
            }
            return clientes;
        }
   
       protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Clientes clientes = new Clientes();
            clientes.setTop_aux(10);
            ArrayList<Clientes> cliente = ClientesDAL.buscar(clientes);
            request.setAttribute("clientes", cliente);
            request.setAttribute("top_aux", clientes.getTop_aux());
            request.getRequestDispatcher("Views/Clientes/index.jsp")
                    .forward(request, response);
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
   
       protected void doPostRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Clientes clientes = obtenerClientes(request);
            ArrayList<Clientes> cliente = ClientesDAL.buscar(clientes);
            request.setAttribute("clientes", cliente);
            request.setAttribute("top_aux", clientes.getTop_aux());
            request.getRequestDispatcher("Views/Clientes/index.jsp")
             .forward(request, response);
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
       
       protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("Views/Clientes/create.jsp")
                    .forward(request, response);
    }
       
       protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Clientes clientes = obtenerClientes(request);
            int result = ClientesDAL.crear(clientes);
            if(result != 0)
            {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            }
            else
            {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
       
       protected void requestObtenerPorId(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Clientes clientes = obtenerClientes(request);
            Clientes clientes_result = ClientesDAL.obtenerPorId(clientes);
            if(clientes_result.getId() > 0)
            {
                request.setAttribute("clientes", clientes_result);
            }
            else
            {
                Utilidad.enviarError("El id: " + clientes.getId() + " no existe en la tabla clientes", 
                        request, response);
            }
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
       
       protected void doGetRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
            requestObtenerPorId(request, response);
            request.getRequestDispatcher("Views/Productos/edit.jsp")
                    .forward(request, response);
    }
    
    protected void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Clientes clientes = obtenerClientes(request);
            int result = ClientesDAL.modificar(clientes);
            if(result != 0)
            {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            }
            else
            {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
        protected void doGetRequestDetails(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
                requestObtenerPorId(request, response);
                request.getRequestDispatcher("Views/Clientes/details.jsp")
                        .forward(request, response);
        }
        
     protected void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            requestObtenerPorId(request, response);
            request.getRequestDispatcher("Views/Clientes/delete.jsp")
                    .forward(request, response);
    }
    
    protected void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Clientes clientes = obtenerClientes(request);
            int result = ClientesDAL.eliminar(clientes);
            if(result != 0)
            {
                request.setAttribute("accion", "index");
                doGetRequestIndex(request, response);
            }
            else
            {
                Utilidad.enviarError("Error al Guardar el Regisgtro", request, response);
            }

        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String accion = Utilidad.getParameter(request, "accion", "index");
            
             switch(accion)
                {
                case "index":
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doGetRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doGetRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doGetRequestDelete(request, response);
                    break;
                case "details":
                    request.setAttribute("accion", accion);
                    doGetRequestDetails(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                }            


    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String accion = Utilidad.getParameter(request, "accion", "index");
            
            switch(accion)
                {
                case "index":
                    request.setAttribute("accion", accion);
                    doPostRequestIndex(request, response);
                    break;
                case "create":
                    request.setAttribute("accion", accion);
                    doPostRequestCreate(request, response);
                    break;
                case "edit":
                    request.setAttribute("accion", accion);
                    doPostRequestEdit(request, response);
                    break;
                case "delete":
                    request.setAttribute("accion", accion);
                    doPostRequestDelete(request, response);
                    break;
                default:
                    request.setAttribute("accion", accion);
                    doGetRequestIndex(request, response);
                    break;
                }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
