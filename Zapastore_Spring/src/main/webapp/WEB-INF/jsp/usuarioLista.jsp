<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>ZapaStore | Lista de Usuarios</title>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>
  <link rel="stylesheet" href="/css/adminStyles.css">
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
        <a href="/admin" class="nav-item"><span class="material-symbols-outlined">dashboard</span>Métricas</a>
        <a href="/catalogo/lista" class="nav-item"><span class="material-symbols-outlined">shopping_bag</span>Catálogo</a>
        <a href="/producto/lista" class="nav-item"><span class="material-symbols-outlined">store</span>Productos</a>
        <a href="/usuario/lista" class="nav-item is-active"><span class="material-symbols-outlined">group</span>Usuarios</a>
      </nav>

      <div class="sidebar-profile">
        <div class="profile-info">
          <div class="profile-avatar" style='background-image: url("/img/user.png");'></div>
          <div>
            <p class="profile-name">Admin User</p>
            <p class="profile-email">admin@zapastore.com</p>
          </div>
        </div>
      </div>
    </aside>

    <!-- Panel principal -->
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
          <h2 class="page-title">Lista de Usuarios</h2>
          <a href="/usuario/crear" class="primary-button-admin">
            <span class="material-symbols-outlined">add</span>Nuevo Usuario
          </a>
        </div>

        <!-- Tabla -->
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
                    <span class="chip-rol ${usuario.rol == 'admin' ? 'admin' : 'cliente'}">
                      ${usuario.rol}
                    </span>
                  </td>
                  <td>
                    <a href="/usuario/editar/${usuario.idUsuario}" class="icon-button edit">
                      <span class="material-symbols-outlined">edit</span>
                    </a>
                    <a href="/usuario/eliminar/${usuario.idUsuario}" class="icon-button delete">
                      <span class="material-symbols-outlined">delete</span>
                    </a>
                  </td>
                </tr>
              </c:forEach>
            </tbody>
          </table>
        </section>
      </div>
    </main>
  </div>
</body>
</html>
