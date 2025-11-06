<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZapaStore | Iniciar Sesión</title>
    <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700;900&family=Noto+Sans:wght@400;500;700;900&display=swap">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body class="light-mode">

<div class="page-container">

    <!-- HEADER -->
    <header class="main-header">
        <div class="container header-inner">
            <div class="header-left">
                <a class="logo" href="${pageContext.request.contextPath}/home">
                    <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo ZapaStore" class="logo-image" height="24">
                    <h1 class="logo-text">ZapaStore</h1>
                </a>
            </div>
            <div class="header-right">
                <!-- Opcional: carrito u otros enlaces -->
            </div>
        </div>
    </header>

    <!-- MAIN CONTENT -->
    <main class="main-content">
        <section class="container login-section">
            <div class="login-form-container">
                <h2 class="section-title login-title">Iniciar Sesión</h2>

                <form action="${pageContext.request.contextPath}/login" method="post" class="login-form">
                    <div class="form-group">
                        <label for="correo" class="form-label">Correo electrónico</label>
                        <input type="email" name="correo" id="correo" class="form-input" placeholder="ejemplo@correo.com" value="${usuario.correo}" required>
                    </div>

                    <div class="form-group">
                        <label for="contrasena" class="form-label">Contraseña</label>
                        <input type="password" name="contrasena" id="contrasena" class="form-input" placeholder="********" required>
                    </div>

                    <button type="submit" class="primary-button login-button">Ingresar</button>

                    <c:if test="${not empty error}">
                        <div class="error">${error}</div>
                    </c:if>
                    <c:if test="${param.logout != null}">
                        <div class="error" style="color:green; border-color:#b6e2b6; background:#eafbea;">
                            Sesión cerrada correctamente.
                        </div>
                    </c:if>
                </form>

                <p class="signup-link">
                    ¿No tienes cuenta? 
                    <a href="${pageContext.request.contextPath}/registrar" class="color-primary-link">Regístrate</a>
                </p>
            </div>
        </section>
    </main>

</div>

</body>
</html>
