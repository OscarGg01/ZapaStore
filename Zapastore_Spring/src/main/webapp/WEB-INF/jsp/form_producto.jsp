<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%-- Título dinámico según si es Crear o Editar --%>
    <title>ZapaStore | ${producto.id == null ? 'Nuevo Producto' : 'Editar Producto'}</title> 
    
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>
    <link rel="stylesheet" href="/css/adminStyles.css">
</head>
<body class="admin-body">
    <div class="admin-layout">
        
        <%-- Incluir Sidebar (puedes mover este bloque a un JSP de fragmento más tarde) --%>
        <jsp:include page="fragments/sidebar.jsp" /> 

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
                    <%-- Título dinámico --%>
                    <h2 class="page-title">${producto.id == null ? 'Registrar Nuevo Producto' : 'Editar Producto'}</h2> 
                    <button class="secondary-button-admin" onclick="window.location.href='/admin/productos/web'">
                        <span class="material-symbols-outlined">arrow_back</span>
                        Volver a la lista
                    </button>
                </div>

                <section class="crud-area">
                    <%-- Mapea al objeto 'producto' y envía al @PostMapping en el controlador --%>
                    <form:form class="crud-form" method="POST" action="/admin/productos/web" modelAttribute="producto">
                        
                        <%-- Campo ID oculto: Necesario para que Spring sepa si es CREATE (ID nulo) o UPDATE (ID existente) --%>
                        <form:hidden path="id"/>

                        <div class="form-grid">
                            <div class="campo">
                                <label for="nombre">Nombre del producto</label>
                                <form:input path="nombre" id="nombre" placeholder="Ej. Nike Air Zoom" required="true"/>
                            </div>

                            <div class="campo categoria-select">
                                <label for="categoriaID">Categoría ID</label>
                                <div class="categoria-flex">
                                    <%-- Usamos input text para Categoría ID, ya que es la clave foránea. 
                                         Idealmente sería un <select> poblado por el backend. --%>
                                    <form:input path="categoriaID" type="number" id="categoriaID" required="true"/>
                                    
                                    <a href="/admin/categorias/lista" class="secondary-button-admin small-btn" style="display:inline-flex;align-items:center;text-decoration:none;">
                                        <span class="material-symbols-outlined">tune</span>
                                        Administrar categorías
                                    </a>
                                </div>
                            </div>

                            <div class="campo">
                                <label for="precio">Precio (S/)</label>
                                <form:input path="precio" type="number" id="precio" placeholder="Ej. 350" min="0" step="0.01" required="true"/>
                            </div>
                            
                            <div class="campo campo-full">
                                <label for="imagen_url">Imagen del producto (URL)</label>
                                <form:input path="imagen_url" type="url" id="imagen_url" placeholder="https://..." required="true"/>
                            </div>
                            
                            <div class="campo campo-full">
                                <label for="descripcion">Descripción</label>
                                <form:textarea path="descripcion" id="descripcion" rows="4"/>
                            </div>
                        </div>

                        <div class="form-actions">
                            <button type="submit" class="primary-button-admin">
                                <span class="material-symbols-outlined">save</span>
                                <%-- Texto del botón dinámico --%>
                                ${producto.id == null ? 'Guardar Producto' : 'Actualizar Producto'}
                            </button>
                        </div>
                    </form:form>
                </section>
            </div>
        </main>
    </div>
</body>
</html>