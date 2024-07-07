<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="cl.praxis.model.dto.ProveedorDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Proveedores</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Proveedores Registrados</h2>
    <table class="table table-striped">
        <thead class="thead-dark">
            <tr>
                <th>#</th>
                <th>Id</th>
                <th>Nombre</th>
                <th>RUT</th>
                <th>Dirección</th>
                <th>Correo</th>
                <th>Teléfono</th>
                <th>Contacto</th>
                <th>Teléfono de Contacto</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<ProveedorDTO> proveedores = (List<ProveedorDTO>) request.getAttribute("proveedores");
            int count = 1;
            if (proveedores != null && !proveedores.isEmpty()) {
                for (ProveedorDTO proveedor : proveedores) {
            %>
            <tr>
                <td><%= count++ %></td>
                <td><%= proveedor.getId() %></td>
                <td><%= proveedor.getNombre() %></td>
                <td><%= proveedor.getRut() %></td>
                <td><%= proveedor.getDireccion() %></td>
                <td><%= proveedor.getCorreo() %></td>
                <td><%= proveedor.getTelefono() %></td>
                <td><%= proveedor.getContacto() %></td>
                <td><%= proveedor.getTelefonoContacto() %></td>
                <td>
                    <a href="ProveedorServlet?accion=editar&id=<%= proveedor.getId() %>" class="btn btn-primary btn-sm">Editar</a>
                </td>
                <td>
                    <a href="ProveedorServlet?accion=confirmarEliminar&id=<%= proveedor.getId() %>" class="btn btn-danger btn-sm">Eliminar</a>
                </td>
            </tr>
            <% 
                }
            } else {
            %>
            <tr>
                <td colspan="11">No hay proveedores registrados.</td>
            </tr>
            <% 
            }
            %>
        </tbody>
    </table>
</div>
</body>
</html>

