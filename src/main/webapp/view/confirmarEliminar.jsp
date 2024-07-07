<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Confirmar Eliminación</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2>Confirmar Eliminación</h2>
    <form method="POST" action="ProveedorServlet">
        <input type="hidden" name="action" value="eliminar">
        <!-- Campo oculto para el ID -->
        <input type="hidden" name="id" value="${proveedor.id}"> 

        <div class="form-group">
            <label>ID:</label>
            <!-- ID visible pero no editable -->
            <input type="text" class="form-control" value="${proveedor.id}" disabled> 
        </div>
        <div class="form-group">
            <label>Nombre:</label>
            <!-- Nombre visible pero no editable -->
            <input type="text" class="form-control" value="${proveedor.nombre}" disabled> 
        </div>
        <div class="form-group">
            <label>¿Estás seguro de que deseas eliminar este proveedor?</label>
        </div>
        <button type="submit" class="btn btn-danger">Eliminar</button>
        <a href="ProveedorServlet?accion=mostrarProveedores" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>
