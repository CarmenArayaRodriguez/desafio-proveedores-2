<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Editar Proveedor</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Editar Proveedor</h2>
    <form method="POST" action="${pageContext.request.contextPath}/ProveedorServlet">
        <input type="hidden" name="action" value="actualizar">
        
        <!-- Campo oculto para el ID -->
        <input type="hidden" name="id" value="${proveedor.id}"> 

        <div class="form-group">
            <label>ID:</label>
            <!-- ID visible pero no editable -->
            <input type="text" class="form-control" value="${proveedor.id}" disabled> 
        </div>
        <div class="form-group">
            <label>Nombre:</label>
            <input type="text" name="nombre" required class="form-control" value="${proveedor.nombre}">
        </div>
        <div class="form-group">
            <label>RUT:</label>
            <input type="text" name="rut" required class="form-control" value="${proveedor.rut}">
        </div>
        <div class="form-group">
            <label>Dirección:</label>
            <input type="text" name="direccion" required class="form-control" value="${proveedor.direccion}">
        </div>
        <div class="form-group">
            <label>Correo:</label>
            <input type="email" name="correo" required class="form-control" value="${proveedor.correo}">
        </div>
        <div class="form-group">
            <label>Teléfono:</label>
            <input type="text" name="telefono" required class="form-control" value="${proveedor.telefono}">
        </div>
        <div class="form-group">
            <label>Contacto:</label>
            <input type="text" name="contacto" required class="form-control" value="${proveedor.contacto}">
        </div>
        <div class="form-group">
            <label>Teléfono de Contacto:</label>
            <input type="text" name="telefono_contacto" required class="form-control" value="${proveedor.telefonoContacto}">
        </div>
        <button type="submit" class="btn btn-primary">Actualizar</button>
    </form>
</div>
</body>
</html>
