<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
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
    <%-- ... Layout (sidebar, header) ... --%>
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
                    <h2 class="page-title">Administrar Categorías</h2>
                    <a href="${pageContext.request.contextPath}/admin/dashboard" class="secondary-button-admin">
                        <span class="material-symbols-outlined">arrow_back</span>
                        Volver
                    </a>
                </div>
                
                <%-- Mensaje de Éxito después de Guardar/Eliminar --%>
                <c:if test="${not empty mensaje}">
                    <div class="alert success" style="margin-bottom: 1rem; padding: 10px; border: 1px solid green; color: green; background-color: #e6ffe6; border-radius: 4px;">
                        ${mensaje}
                    </div>
                </c:if>

                <section class="crud-area">
                    
                    <%-- FORMULARIO DE CREACIÓN --%>
                    <form class="crud-form" action="${pageContext.request.contextPath}/admin/categorias/guardar" method="POST">
                        <div class="campo categoria-flex" style="display:flex; gap:0.75rem; align-items:center;">
                            <%-- Campo para el Nombre --%>
                            <input type="text" placeholder="Nueva categoría..." required class="input-text" style="flex:1;" name="nombre">
                            
                            <%-- Campo para el Estado (activo es el nombre de la propiedad en el formulario) --%>
                            <select class="input-select" style="max-width:150px;" name="activo">
                                <option value="true">Activo</option>
                                <option value="false">Inactivo</option>
                            </select>

                            <button type="submit" class="primary-button-admin small-btn">
                                <span class="material-symbols-outlined">add</span>
                                Agregar
                            </button>
                        </div>
                    </form>

                    <%-- TABLA DE LISTADO DINÁMICO --%>
                    <div class="categorias-lista" style="margin-top:1.5rem;">
                        <table class="crud-table">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nombre de la Categoría</th>
                                    <th>Estado</th>
                                    <th>Acción</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="cat" items="${categorias}">
                                    <tr>
                                        <td>${cat.categoriaId}</td> 
                                        <td>${cat.nombre}</td> 
                                        <td>
                                            <c:choose>
                                                <c:when test="${cat.activo}">
                                                    <span class="material-symbols-outlined" style="color:green;">check_circle</span>
                                                    <span class="estado-texto" style="color:green;">Activo</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="material-symbols-outlined" style="color:red;">cancel</span>
                                                    <span class="estado-texto" style="color:red;">Inactivo</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <%-- Enlace a Editar --%>
                                            <a href="${pageContext.request.contextPath}/admin/categorias/editar?id=${cat.categoriaId}" class="icon-button edit" title="Editar categoría">
                                                <span class="material-symbols-outlined">edit</span>
                                            </a>
                                            <%-- Enlace a Eliminar (Soft Delete) --%>
                                            <a href="${pageContext.request.contextPath}/admin/categorias/eliminar?id=${cat.categoriaId}" class="icon-button delete" title="Desactivar categoría" onclick="return confirm('¿Está seguro de que desea desactivar la categoría ${cat.nombre}?');">
                                                <span class="material-symbols-outlined">delete</span>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty categorias}">
                                    <tr>
                                        <td colspan="4" style="text-align: center; padding: 15px; color: #6c757d;">
                                            No hay categorías para mostrar. Agregue una usando el formulario de arriba.
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