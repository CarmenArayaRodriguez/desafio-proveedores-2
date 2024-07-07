package cl.praxis.controller;

import cl.praxis.model.Proveedor;
import cl.praxis.model.dao.ProveedorDAO;
import cl.praxis.model.dao.ProveedorDAOImpl;
import cl.praxis.model.dto.ProveedorDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet({"/ProveedorServlet", "/"})
public class ProveedorServlet extends HttpServlet {
	
    private boolean validarProveedor(Proveedor proveedor) {
        if (proveedor.getNombre() == null || proveedor.getNombre().trim().isEmpty() ||
            proveedor.getRut() == null || proveedor.getRut().trim().isEmpty() ||
            proveedor.getDireccion() == null || proveedor.getDireccion().trim().isEmpty() ||
            proveedor.getCorreo() == null || proveedor.getCorreo().trim().isEmpty() ||
            proveedor.getTelefono() == null || proveedor.getTelefono().trim().isEmpty() ||
            proveedor.getContacto() == null || proveedor.getContacto().trim().isEmpty() ||
            proveedor.getTelefonoContacto() == null || proveedor.getTelefonoContacto().trim().isEmpty()) {
            return false;
        }
        return true;
    }
    
    @Override
    protected void doPost(HttpServletRequest solicitud, HttpServletResponse respuesta) throws ServletException, IOException {
        solicitud.setCharacterEncoding("UTF-8");
        respuesta.setCharacterEncoding("UTF-8");

        String accion = solicitud.getParameter("action");
        if (accion == null) {
            respuesta.sendRedirect("view/index.jsp");
            return;
        }

        if (accion.equals("eliminar")) {
            eliminarProveedor(solicitud, respuesta);
            return;
        }

        String nombre = solicitud.getParameter("nombre");
        String rut = solicitud.getParameter("rut");
        String direccion = solicitud.getParameter("direccion");
        String correo = solicitud.getParameter("correo");
        String telefono = solicitud.getParameter("telefono");
        String contacto = solicitud.getParameter("contacto");
        String telefonoContacto = solicitud.getParameter("telefono_contacto");

        Proveedor proveedor = new Proveedor(nombre, rut, direccion, correo, telefono, contacto, telefonoContacto);

        if (!validarProveedor(proveedor)) {
            solicitud.setAttribute("error", "Todos los campos son obligatorios y no pueden estar vacíos.");
            solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
            return;
        }

        switch (accion) {
            case "insertar":
                insertarProveedor(solicitud, respuesta);
                break;
            case "actualizar":
                actualizarProveedor(solicitud, respuesta);
                break;
            default:
                solicitud.getRequestDispatcher("/view/index.jsp").forward(solicitud, respuesta);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest solicitud, HttpServletResponse respuesta) throws ServletException, IOException {
        solicitud.setCharacterEncoding("UTF-8");
        respuesta.setCharacterEncoding("UTF-8");

        String accion = solicitud.getParameter("accion");
        if (accion == null) {
            respuesta.sendRedirect("view/index.jsp");
            return;
        }

        switch (accion) {
            case "mostrarProveedores":
                mostrarProveedores(solicitud, respuesta);
                break;
            case "editar":
                mostrarFormularioEdicion(solicitud, respuesta);
                break;
            case "confirmarEliminar":
                mostrarConfirmacionEliminacion(solicitud, respuesta);
                break;
            case "eliminar":
                eliminarProveedor(solicitud, respuesta);
                break;
            default:
                respuesta.sendRedirect("view/index.jsp");
                break;
        }
    }

    private void mostrarProveedores(HttpServletRequest solicitud, HttpServletResponse respuesta) throws ServletException, IOException {
        ProveedorDAO daoProveedor = new ProveedorDAOImpl();
        List<ProveedorDTO> proveedores = daoProveedor.getAllProveedoresDTO();
        solicitud.setAttribute("proveedores", proveedores);
        solicitud.getRequestDispatcher("/view/proveedores.jsp").forward(solicitud, respuesta);
    }

    private void insertarProveedor(HttpServletRequest solicitud, HttpServletResponse respuesta) throws IOException, ServletException {
        String nombre = solicitud.getParameter("nombre");
        String rut = solicitud.getParameter("rut");
        String direccion = solicitud.getParameter("direccion");
        String correo = solicitud.getParameter("correo");
        String telefono = solicitud.getParameter("telefono");
        String contacto = solicitud.getParameter("contacto");
        String telefonoContacto = solicitud.getParameter("telefono_contacto");

        Proveedor proveedor = new Proveedor(nombre, rut, direccion, correo, telefono, contacto, telefonoContacto);
        ProveedorDAO daoProveedor = new ProveedorDAOImpl();

        try {
            boolean exito = daoProveedor.addProveedor(proveedor);
            if (exito) {
                respuesta.sendRedirect("ProveedorServlet?accion=mostrarProveedores");
            } else {
                solicitud.setAttribute("error", "No se pudo registrar el proveedor.");
                solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
            solicitud.setAttribute("error", "Error del servidor: " + e.getMessage());
            solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
        }
    }

    private void mostrarFormularioEdicion(HttpServletRequest solicitud, HttpServletResponse respuesta) throws ServletException, IOException {
        int id = Integer.parseInt(solicitud.getParameter("id"));
        ProveedorDAO daoProveedor = new ProveedorDAOImpl();
        Proveedor proveedor = daoProveedor.getProveedorById(id);

        if (proveedor != null) {
            solicitud.setAttribute("proveedor", proveedor);
            solicitud.getRequestDispatcher("/view/edicion.jsp").forward(solicitud, respuesta);
        } else {
            solicitud.setAttribute("error", "Proveedor no encontrado");
            solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
        }
    }

    private void mostrarConfirmacionEliminacion(HttpServletRequest solicitud, HttpServletResponse respuesta) throws ServletException, IOException {
        int id = Integer.parseInt(solicitud.getParameter("id"));
        ProveedorDAO daoProveedor = new ProveedorDAOImpl();
        Proveedor proveedor = daoProveedor.getProveedorById(id);

        if (proveedor != null) {
            solicitud.setAttribute("proveedor", proveedor);
            solicitud.getRequestDispatcher("/view/confirmarEliminar.jsp").forward(solicitud, respuesta);
        } else {
            solicitud.setAttribute("error", "Proveedor no encontrado");
            solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
        }
    }

    private void actualizarProveedor(HttpServletRequest solicitud, HttpServletResponse respuesta) throws IOException, ServletException {
        String idStr = solicitud.getParameter("id");
        String nombre = solicitud.getParameter("nombre");
        String rut = solicitud.getParameter("rut");
        String direccion = solicitud.getParameter("direccion");
        String correo = solicitud.getParameter("correo");
        String telefono = solicitud.getParameter("telefono");
        String contacto = solicitud.getParameter("contacto");
        String telefonoContacto = solicitud.getParameter("telefono_contacto");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                Proveedor proveedor = new Proveedor(id, nombre, rut, direccion, correo, telefono, contacto, telefonoContacto);
                ProveedorDAO daoProveedor = new ProveedorDAOImpl();

                boolean exito = daoProveedor.updateProveedor(proveedor);
                if (exito) {
                    respuesta.sendRedirect("ProveedorServlet?accion=mostrarProveedores");
                } else {
                    solicitud.setAttribute("error", "No se pudo actualizar el proveedor.");
                    solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                solicitud.setAttribute("error", "Formato de ID inválido.");
                solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
            } catch (Exception e) {
                e.printStackTrace();
                solicitud.setAttribute("error", "Error del servidor: " + e.getMessage());
                solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
            }
        } else {
            solicitud.setAttribute("error", "ID no proporcionado.");
            solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
        }
    }

    private void eliminarProveedor(HttpServletRequest solicitud, HttpServletResponse respuesta) throws IOException, ServletException {
        String idStr = solicitud.getParameter("id");
        if (idStr == null || idStr.isEmpty()) {
            solicitud.setAttribute("error", "ID no proporcionado.");
            solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
            return;
        }

        try {
            int id = Integer.parseInt(idStr);
            ProveedorDAO daoProveedor = new ProveedorDAOImpl();
            boolean exito = daoProveedor.deleteProveedor(id);
            if (exito) {
                respuesta.sendRedirect("ProveedorServlet?accion=mostrarProveedores");
            } else {
                solicitud.setAttribute("error", "No se pudo eliminar el proveedor porque no existe.");
                solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
            }
        } catch (NumberFormatException e) {
            solicitud.setAttribute("error", "Formato de ID inválido: " + e.getMessage());
            solicitud.getRequestDispatcher("/view/error.jsp").forward(solicitud, respuesta);
        }
    }


}

