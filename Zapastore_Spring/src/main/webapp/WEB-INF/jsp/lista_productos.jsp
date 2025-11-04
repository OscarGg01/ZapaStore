<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZapaStore | Lista de Productos</title>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>

    <link rel="stylesheet" href="/css/adminStyles.css"> 
</head>
<body class="admin-body">
    <div class="admin-layout">

        <%-- Incluir Sidebar (puedes mover este bloque a un JSP de fragmento más tarde) --%>
        <%@include file="fragments/sidebar.jsp" %>

        <main class="main-panel">
            <header class="main-header-admin">
                 <div class="header-actions">
                    <button class="icon-button"><span class="material-symbols-outlined">notifications</span></button>
                    <button class="icon-button"><span class="material-symbols-outlined">settings</span></button>
                    <div class="profile-avatar profile-avatar-small" style='background-image: url("/img/user.png");'></div>
                </div>
            </header>

            <div class="content-wrapper">
                <div class="page-header">
                    <h2 class="page-title">Lista de Productos</h2>
                    <%-- Botón que llama al método GET /admin/productos/web/new --%>
                    <button class="primary-button-admin" onclick="window.location.href='/admin/productos/web/new'">
                        <span class="material-symbols-outlined">add</span>Nuevo Producto
                    </button>
                </div>

                <%-- Mensajes de Éxito/Error --%>
                <c:if test="${not empty mensaje}">
                    <p style="color: green; font-weight: bold; padding: 10px; background: #e6ffe6; border-radius: 5px;">${mensaje}</p>
                </c:if>
                <c:if test="${not empty error}">
                    <p style="color: red; font-weight: bold; padding: 10px; background: #ffe6e6; border-radius: 5px;">${error}</p>
                </c:if>

                <div class="search-bar">
                    <input type="text" placeholder="Buscar producto..." class="input-search">
                    <button class="icon-button search-button">
                        <span class="material-symbols-outlined">search</span>
                    </button>
                </div>

                <section class="crud-lista">
                    <table class="crud-table">
                        <thead>
                            <tr>
                                <th>Imagen</th>
                                <th>Nombre</th>
                                <th>Categoría (ID)</th>
                                <th>Precio (S/)</th>
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%-- Iteración dinámica sobre la lista de productos del modelo --%>
                            <c:forEach var="producto" items="${productos}">
                            <tr>
                                <td><img src="${producto.imagen_url}" alt="${producto.nombre}" class="tabla-img"></td>
                                <td>${producto.nombre}</td>
                                <td>${producto.categoriaID}</td>
                                <td>${producto.precio}</td>
                                <td>
                                    <%-- Botón Editar: Llama al método GET /admin/productos/web/edit/{id} --%>
                                    <button class="icon-button edit" onclick="window.location.href='/admin/productos/web/edit/${producto.id}'">
                                        <span class="material-symbols-outlined">edit</span>
                                    </button>
                                    <%-- Botón Eliminar: Llama al método GET /admin/productos/web/delete/{id} --%>
                                    <button class="icon-button delete" onclick="if(confirm('¿Seguro que quieres eliminar ${producto.nombre}?')) { window.location.href='/admin/productos/web/delete/${producto.id}'; }">
                                        <span class="material-symbols-outlined">delete</span>
                                    </button>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <%-- Mensaje si no hay productos --%>
                    <c:if test="${empty productos}">
                         <p style="text-align: center; margin-top: 20px;">No hay productos registrados en la base de datos.</p>
                    </c:if>
                </section>
            </div>
        </main>
    </div>
</body>
</html>