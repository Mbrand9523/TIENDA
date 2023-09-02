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
import perfectshoes.accesoadatos.ProveedorDAL;
import perfectshoes.entidadesdenegocio.Proveedor;
import perfectshoes.appweb.utils.*;

/**
 *
 * @author Carmen Estela
 */
@WebServlet(name = "ProveedorServlet", urlPatterns = {"/Proveedor"})
public class ProveedorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     private Proveedor obtenerProveedor(HttpServletRequest request)
    {
        String accion = Utilidad.getParameter(request, "accion", "index");
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre(Utilidad.getParameter(request, "nombre", ""));
        proveedor.setApellido(Utilidad.getParameter(request, "napellido", ""));
        proveedor.setRazon_social(Utilidad.getParameter(request, "razon_social", ""));
        proveedor.setTelefono(Utilidad.getParameter(request, "telefono", ""));
       proveedor.setDireccion(Utilidad.getParameter(request, "direccion", "")); 
        if(accion.equals("index"))
        {
            proveedor.setTop_aux(Integer.parseInt(Utilidad.getParameter(request, 
                    "top_aux", "10")));
            proveedor.setTop_aux(proveedor.getTop_aux() == 0 ? Integer.MAX_VALUE: proveedor.getTop_aux());
        }
        else
        {
            proveedor.setId(Integer.parseInt(Utilidad.getParameter(request, 
                    "id",
                    "0")));
        }
        return proveedor;
    }
      protected void doGetRequestIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Proveedor proveedor = new Proveedor();
            proveedor.setTop_aux(10);
            ArrayList<Proveedor> proveedores = ProveedorDAL.buscar(
                    proveedor);
            request.setAttribute("proveedores", proveedores);
            request.setAttribute("top_aux", proveedor.getTop_aux());
            request.getRequestDispatcher("Views/Proveedor/index.jsp")
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
            Proveedor proveedor = obtenerProveedor(request);
            proveedor.setTop_aux(10);
            ArrayList<Proveedor> proveedores = ProveedorDAL.buscar(
                    proveedor);
            request.setAttribute("proveedor", proveedores);
            request.setAttribute("top_aux", proveedor.getTop_aux());
            request.getRequestDispatcher("Views/Proveedor/index.jsp")
                    .forward(request, response);
        }
        catch(Exception ex)
        {
            Utilidad.enviarError(ex.getMessage(), request, response);
        }
    }
     protected void doGetRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.getRequestDispatcher("Views/Proveedor/create.jsp")
                    .forward(request, response);
    }
    
    protected void doPostRequestCreate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Proveedor proveedor = obtenerProveedor(request);
            int result = ProveedorDAL.crear(proveedor);
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
            Proveedor proveedor = obtenerProveedor(request);
            Proveedor proveedor_result = ProveedorDAL.obtenerPorId(proveedor);
            if(proveedor_result.getId() > 0)
            {
                request.setAttribute("proveedor", proveedor_result);
            }
            else
            {
                Utilidad.enviarError("El id: " + proveedor.getId() + " no existe en la tabla Proveedor", 
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
            request.getRequestDispatcher("Views/Proveedor/edit.jsp")
                    .forward(request, response);
    }
    
    protected void doPostRequestEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Proveedor proveedor = obtenerProveedor(request);
            int result = ProveedorDAL.modificar(proveedor);
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
            request.getRequestDispatcher("Views/Proveedor/details.jsp")
                    .forward(request, response);
    }
    
    protected void doGetRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            requestObtenerPorId(request, response);
            request.getRequestDispatcher("Views/Proveedor/delete.jsp")
                    .forward(request, response);
    }
    
    protected void doPostRequestDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            Proveedor proveedor = obtenerProveedor(request);
            int result = ProveedorDAL.eliminar(proveedor);
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
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, 
                    "accion", "index");
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
        }//);
   
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //SessionUser.authorize(request, response, () -> {
            String accion = Utilidad.getParameter(request, 
                    "accion", "index");
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
        //});
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

   
  