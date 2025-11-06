<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="page" value="usuarios" />

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZapaStore | Lista de Usuarios</title>

    <!-- Fuentes e íconos -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>
    
    <!-- Estilos principales -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminStyles.css">
</head>

<body class="admin-body">
    <div class="admin-layout">
        <!-- Sidebar fijo -->
        <jsp:include page="/WEB-INF/jsp/fragments/sidebar.jsp" />

        <!-- Panel principal -->
        <main class="main-panel">
            <!-- Header fijo -->
            <jsp:include page="/WEB-INF/jsp/fragments/header.jsp" />

            <!-- Contenido -->
            <div class="content-wrapper">
                <div class="page-header">
                    <h2 class="page-title">
                        <span class="material-symbols-outlined icon-title">group</span>
                        Lista de Usuarios
                    </h2>
                    <a href="${pageContext.request.contextPath}/admin/usuarios/crear" class="primary-button-admin">
                        <span class="material-symbols-outlined">add_circle</span>
                        Nuevo Usuario
                    </a>
                </div>

                <!-- Mensajes de éxito o error -->
                <c:if test="${not empty mensaje}">
                    <div class="alert success">
                        <span class="material-symbols-outlined">check_circle</span>
                        ${mensaje}
                    </div>
                </c:if>

                <c:if test="${not empty error}">
                    <div class="alert error">
                        <span class="material-symbols-outlined">error</span>
                        ${error}
                    </div>
                </c:if>

                <!-- Tabla de usuarios -->
                <section class="crud-lista">
                    <table class="crud-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Correo</th>
                                <th>Teléfono</th>
                                <th>Rol</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usuario" items="${usuarios}">
                                <tr>
                                    <td>${usuario.idUsuario}</td>
                                    <td>${usuario.nombre}</td>
                                    <td>${usuario.correo}</td>
                                    <td>${usuario.telefono}</td>
                                    <td>
                                        <span class="chip chip-rol ${usuario.rol == 'admin' ? 'chip-admin' : 'chip-cliente'}">
                                            ${usuario.rol}
                                        </span>
                                    </td>
                                    <td class="actions-cell">
                                        <a href="${pageContext.request.contextPath}/admin/usuarios/editar/${usuario.idUsuario}" 
                                           class="icon-button edit" title="Editar">
                                            <span class="material-symbols-outlined">edit</span>
                                        </a>
                                        <a href="${pageContext.request.contextPath}/admin/usuarios/eliminar/${usuario.idUsuario}" 
                                           class="icon-button delete" title="Eliminar"
                                           onclick="return confirm('¿Estás seguro de eliminar este usuario?');">
                                            <span class="material-symbols-outlined">delete</span>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <!-- En caso de lista vacía -->
                    <c:if test="${empty usuarios}">
                        <p class="no-data-msg">
                            No hay usuarios registrados aún.
                        </p>
                    </c:if>
                </section>
            </div>
        </main>
    </div>
</body>
</html>
