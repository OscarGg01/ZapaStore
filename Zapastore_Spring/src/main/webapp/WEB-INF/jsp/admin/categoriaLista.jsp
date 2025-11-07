<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="page" value="categorias" />

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZapaStore | Administrar Categorías</title>
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminStyles.css">
</head>

<body class="admin-body">
<div class="admin-layout">
    <!-- Sidebar -->
    <jsp:include page="/WEB-INF/jsp/fragments/sidebar.jsp"/>

    <main class="main-panel">
        <!-- Header -->
        <jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>

        <!-- Contenido -->
        <div class="content-wrapper">
            <div class="page-header">
                <h2 class="page-title">
                    <span class="material-symbols-outlined icon-title">category</span>
                    Administrar Categorías
                </h2>
            </div>

            <!-- Mensajes de éxito o error -->
            <c:if test="${not empty mensaje}">
                <div class="alert success">${mensaje}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert error">${error}</div>
            </c:if>

            <!-- Formulario de agregar categoría -->
            <section class="crud-area">
                <form class="crud-form" action="${pageContext.request.contextPath}/admin/categorias/guardar" method="POST">
                    <div class="form-grid">
                        <div class="campo">
                            <label for="nombre">Nueva categoría</label>
                            <input type="text" name="nombre" id="nombre" placeholder="Ej. Deportivos" required class="input-text">
                        </div>

                        <div class="campo">
                            <label for="activo">Estado</label>
                            <select name="estado" id="activo" class="input-select">
                                <option value="true">Activo</option>
                                <option value="false">Inactivo</option>
                            </select>
                        </div>

                        <div class="campo form-actions">
                            <button type="submit" class="primary-button-admin small-btn">
                                <span class="material-symbols-outlined">add</span> Agregar
                            </button>
                        </div>
                    </div>
                </form>

                <!-- Tabla de categorías -->
                <div class="crud-lista">
                    <table class="crud-table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre de la Categoría</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="cat" items="${categorias}">
                            <tr>
                                <td>${cat.categoriaId}</td>
                                <td>${cat.nombre}</td>
                                <td>
                                    <span class="chip ${cat.activo ? 'chip-activo' : 'chip-inactivo'}">
                                        ${cat.activo ? 'Activo' : 'Inactivo'}
                                    </span>
                                </td>
                                <td class="actions-cell">
                                    <a href="${pageContext.request.contextPath}/admin/categorias/editar?id=${cat.categoriaId}"
                                       class="icon-button edit" title="Editar">
                                        <span class="material-symbols-outlined">edit</span>
                                    </a>
                                    <a href="${pageContext.request.contextPath}/admin/categorias/eliminar?id=${cat.categoriaId}"
                                       class="icon-button delete" title="Eliminar"
                                       onclick="return confirm('¿Está seguro de desactivar la categoría ${cat.nombre}?');">
                                        <span class="material-symbols-outlined">delete</span>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty categorias}">
                            <tr>
                                <td colspan="4" class="tabla-vacia">
                                    No hay categorías registradas aún.
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </section>
        </div>
    </main>
</div>
</body>
</html>
