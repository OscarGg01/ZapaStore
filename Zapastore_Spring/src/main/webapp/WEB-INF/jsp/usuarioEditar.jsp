<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ZapaStore | Editar Usuario</title>
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
        <a href="${pageContext.request.contextPath}/admin" class="nav-item">
          <span class="material-symbols-outlined">dashboard</span>Métricas
        </a>
        <a href="${pageContext.request.contextPath}/catalogo/lista" class="nav-item">
          <span class="material-symbols-outlined">shopping_bag</span>Catálogo
        </a>
        <a href="${pageContext.request.contextPath}/producto/lista" class="nav-item">
          <span class="material-symbols-outlined">store</span>Productos
        </a>
        <a href="${pageContext.request.contextPath}/usuario/lista" class="nav-item is-active">
          <span class="material-symbols-outlined">group</span>Usuarios
        </a>
      </nav>
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
          <h2 class="page-title">Editar Usuario</h2>
          <button class="secondary-button-admin"
                  onclick="window.location.href='${pageContext.request.contextPath}/usuario/lista'">
            <span class="material-symbols-outlined">arrow_back</span>Volver a la lista
          </button>
        </div>

        <section class="crud-area">
        <form:form modelAttribute="usuario"
                   method="post"
                   action="${pageContext.request.contextPath}/usuario/actualizar"
                   class="crud-form">

            <!-- ⚙️ Campo oculto para mantener el ID -->
            <form:hidden path="idUsuario" />

            <div class="campo">
              <label for="nombre">Nombre completo</label>
              <form:input path="nombre" id="nombre" required="true" />
            </div>

            <div class="campo">
              <label for="correo">Correo electrónico</label>
              <form:input path="correo" id="correo" type="email" required="true" />
            </div>

            <div class="campo">
              <label for="telefono">Teléfono</label>
              <form:input path="telefono" id="telefono" type="tel" required="true" />
            </div>

            <div class="campo">
              <label for="rol">Rol del usuario</label>
              <form:select path="rol" id="rol" required="true">
                <form:option value="">Seleccione un rol</form:option>
                <form:option value="admin">Administrador</form:option>
                <form:option value="cliente">Cliente</form:option>
              </form:select>
            </div>

            <div class="campo">
              <label for="contrasena">Contraseña</label>
              <form:password path="contrasena" id="contrasena" />
            </div>

            <div class="botones-crud">
              <button type="submit" class="primary-button-admin">
                <span class="material-symbols-outlined">save</span>Actualizar Usuario
              </button>
            </div>

        </form:form>
        </section>
      </div>
    </main>
  </div>
</body>
</html>
