<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ZapaStore | Nuevo Usuario</title>
  <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminStyles.css">
</head>
<body class="admin-body">
  <div class="admin-layout">
    <!-- Sidebar -->
    <aside class="sidebar">
      <div class="sidebar-header">
        <img src="/img/logo.png" alt="Logo ZapaStore" class="logo-icon" height="48">
        <h1 class="logo-title">ZapaStore Admin</h1>
      </div>

      <nav class="sidebar-nav">
        <a href="${pageContext.request.contextPath}/admin" class="nav-item"><span class="material-symbols-outlined">dashboard</span>Métricas</a>
        <a href="${pageContext.request.contextPath}/admin/categorias/lista" class="nav-item"><span class="material-symbols-outlined">shopping_bag</span>Categorias</a>
        <a href="${pageContext.request.contextPath}/admin/productos/lista" class="nav-item"><span class="material-symbols-outlined">store</span>Productos</a>
        <a href="${pageContext.request.contextPath}/admin/usuarios/lista" class="nav-item is-active"><span class="material-symbols-outlined">group</span>Usuarios</a>
      </nav>
    </aside>

    <!-- Panel principal -->
    <main class="main-panel">
      <header class="main-header-admin">
        <div class="header-actions">
          <button class="icon-button" type="button"><span class="material-symbols-outlined">notifications</span></button>
          <button class="icon-button" type="button"><span class="material-symbols-outlined">settings</span></button>
          <div class="profile-avatar profile-avatar-small" style='background-image: url("${pageContext.request.contextPath}/resources/img/user.png");'></div>
        </div>
      </header>

      <div class="content-wrapper">
        <div class="page-header">
          <h2 class="page-title">Registrar Nuevo Usuario</h2>
          <a class="secondary-button-admin" href="${pageContext.request.contextPath}/usuario/lista">
            <span class="material-symbols-outlined">arrow_back</span>Volver a la lista
          </a>
        </div>

        <!-- Formulario para registrar usuario -->
        <section class="crud-area">
          <form class="crud-form" action="${pageContext.request.contextPath}/usuario/crear" method="post">
            <div class="campo">
              <label for="nombre">Nombre completo</label>
              <input type="text" id="nombre" name="nombre" value="${usuario != null ? usuario.nombre : ''}" placeholder="Ej. Juan Pérez" required>
            </div>

            <div class="campo">
              <label for="correo">Correo electrónico</label>
              <input type="email" id="correo" name="correo" value="${usuario != null ? usuario.correo : ''}" placeholder="usuario@correo.com" required>
            </div>

            <div class="campo">
              <label for="telefono">Teléfono</label>
              <input type="tel" id="telefono" name="telefono" value="${usuario != null ? usuario.telefono : ''}" placeholder="+51 999 999 999">
            </div>

            <div class="campo">
                <label for="contrasena">Contraseña</label>
                <input type="password" id="contrasena" name="contrasena" placeholder="Ingrese una contraseña" required>
            </div>

            <div class="campo">
              <label for="rol">Rol del usuario</label>
              <select id="rol" name="rol" required>
                <option value="">Seleccione un rol</option>
                <option value="admin" ${usuario != null && usuario.rol == 'admin' ? 'selected' : ''}>Administrador</option>
                <option value="cliente" ${usuario != null && usuario.rol == 'cliente' ? 'selected' : ''}>Cliente</option>
              </select>
            </div>

            <button type="submit" class="primary-button-admin">
              <span class="material-symbols-outlined">save</span>Guardar Usuario
            </button>
          </form>
        </section>
      </div>
    </main>
  </div>
</body>
</html>
