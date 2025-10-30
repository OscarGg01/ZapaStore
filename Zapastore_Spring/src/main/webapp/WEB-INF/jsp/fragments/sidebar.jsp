<%-- /WEB-INF/jsp/fragments/sidebar.jsp --%>

<aside class="sidebar">
    <div class="sidebar-header">
        <img src="img/logo.png" alt="Logo ZapaStore" class="logo-icon" height="48">
        <h1 class="logo-title">ZapaStore Admin</h1>
    </div>

    <nav class="sidebar-nav">
        <%-- Aquí puedes usar JSTL o lógica JSP para manejar la clase 'is-active' --%>
        
        <a href="admin.html" class="nav-item">
            <span class="material-symbols-outlined">dashboard</span>Metricas
        </a>
        <a href="catalogoLista.html" class="nav-item">
            <span class="material-symbols-outlined">shopping_bag</span>Catalogo
        </a>
        <%-- **Nota:** La clase 'is-active' probablemente debe ser dinámica, pero la dejamos activa aquí como en tus ejemplos HTML. --%>
        <a href="productoLista.html" class="nav-item is-active">
            <span class="material-symbols-outlined">store</span>Productos
        </a>
        <a href="usuarioCrear.html" class="nav-item">
            <span class="material-symbols-outlined">group</span>Usuarios
        </a>
    </nav>

    <div class="sidebar-profile">
        <div class="profile-info">
            <div class="profile-avatar" style='background-image: url("img/user.png");'></div>
            <div>
                <p class="profile-name">Admin User</p>
                <p class="profile-email">admin@zapastore.com</p>
            </div>
        </div>
    </div>
</aside>