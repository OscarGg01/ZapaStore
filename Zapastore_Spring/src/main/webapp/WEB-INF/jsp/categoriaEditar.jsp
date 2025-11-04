<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZapaStore | Editar Categoría</title>
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminStyles.css">
</head>
<body class="admin-body">
    <div class="admin-layout">

        <aside class="sidebar">
            <div class="sidebar-header">
                <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo ZapaStore" class="logo-icon" height="48">
                <h1 class="logo-title">ZapaStore Admin</h1>
            </div>
            <nav class="sidebar-nav">
                <a href="${pageContext.request.contextPath}/admin/dashboard" class="nav-item">
                    <span class="material-symbols-outlined">dashboard</span> Métricas
                </a>
                <a href="${pageContext.request.contextPath}/admin/categorias/lista" class="nav-item is-active">
                    <span class="material-symbols-outlined">category</span> Catálogos
                </a>
                <a href="${pageContext.request.contextPath}/admin/productos/lista" class="nav-item">
                    <span class="material-symbols-outlined">store</span> Productos
                </a>
                <a href="${pageContext.request.contextPath}/admin/usuarios/lista" class="nav-item">
                    <span class="material-symbols-outlined">group</span> Usuarios
                </a>
            </nav>
            <div class="sidebar-profile">
                <div class="profile-info">
                    <div class="profile-avatar" style='background-image: url("${pageContext.request.contextPath}/img/user.png");'></div>
                    <div>
                        <p class="profile-name">Admin User</p>
                        <p class="profile-email">admin@zapastore.com</p>
                    </div>
                </div>
            </div>
        </aside>

        <main class="main-panel">
            <header class="main-header-admin">
                <div class="header-actions">
                    <button class="icon-button"><span class="material-symbols-outlined">notifications</span></button>
                    <button class="icon-button"><span class="material-symbols-outlined">settings</span></button>
                    <div class="profile-avatar profile-avatar-small" style='background-image: url("${pageContext.request.contextPath}/img/user.png");'></div>
                </div>
            </header>

            <div class="content-wrapper">
                <div class="page-header">
                    <h2 class="page-title">Editar Categoría: ${categoria.nombre}</h2>
                    <a href="${pageContext.request.contextPath}/admin/categorias/lista" class="secondary-button-admin">
                        <span class="material-symbols-outlined">arrow_back</span>
                        Volver a Categorías
                    </a>
                </div>

                <section class="crud-area">
                    <form class="crud-form" style="max-width:600px; margin:auto;" action="${pageContext.request.contextPath}/admin/categorias/actualizar" method="POST">

                        <input type="hidden" name="categoriaId" value="${categoria.categoriaId}">

                        <div class="campo" style="margin-bottom:1.2rem;">
                            <label for="nombreCategoria" class="label">Nombre de la Categoría:</label>
                            <input id="nombreCategoria" type="text" class="input-text" 
                                   placeholder="Ingrese el nombre de la categoría" 
                                   name="nombre"
                                   value="${categoria.nombre}" required>
                        </div>

                        <div class="campo" style="display:flex; align-items:center; gap:1rem; margin-bottom:1.5rem;">
                            <label for="estadoCategoria" class="label" style="margin:0;">Estado:</label>
                            <select id="estadoCategoria" class="input-select" style="max-width:150px;" name="estado">
                                <option value="Activo" <c:if test="${categoria.estado eq 'Activo'}">selected</c:if>>Activo</option>
                                <option value="Inactivo" <c:if test="${categoria.estado eq 'Inactivo'}">selected</c:if>>Inactivo</option>
                            </select>
                        </div>

                        <div style="display:flex; gap:1rem; justify-content:flex-end;">
                            <a href="${pageContext.request.contextPath}/admin/categorias/lista" class="secondary-button-admin">
                                Cancelar
                            </a>
                            <button type="submit" class="primary-button-admin">
                                <span class="material-symbols-outlined">save</span>
                                Guardar Cambios
                            </button>
                        </div>
                    </form>
                </section>
            </div>
        </main>
    </div>
</body>
</html>