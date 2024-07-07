<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <!-- Bootstrap CSS-->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-danger">Ha ocurrido un error</h1>
        <p>Lo sentimos, ha ocurrido un error procesando tu solicitud.</p>
        <p><%= request.getAttribute("error") %></p>
        <a href="/DesafioProveedores2/view/index.jsp" class="btn btn-primary">Volver al inicio</a>
    </div>
</body>
</html>
