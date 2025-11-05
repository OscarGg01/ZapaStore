<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>ZapaStore | Regístrate</title>
    <link rel="stylesheet" href="/css/login.css">
</head>
<body class="light-mode">
<div class="page-container">

    <header class="main-header">
        <div class="header-inner">
            <div class="header-left">
                <a class="logo" href="/">
                    <img src="/img/logo.png" alt="Logo ZapaStore" class="logo-image" height="24">
                    <h1 class="logo-text">ZapaStore</h1>
                </a>
            </div>
        </div>
    </header>

    <main class="main-content">
        <section class="register-section">
            <div class="register-form-container">

                <div class="register-box form-box">
                    <h2 class="section-title login-title">Crear Cuenta</h2>

                    <form:form action="/register" method="post" modelAttribute="usuario" class="login-form">

                        <c:if test="${not empty error}">
                            <div class="error">${error}</div>
                        </c:if>

                        <div class="form-group">
                            <form:label path="nombre" class="form-label">Nombre completo</form:label>
                            <form:input path="nombre" class="form-input" placeholder="Nombre y Apellido"/>
                        </div>

                        <div class="form-group">
                            <form:label path="correo" class="form-label">Correo electrónico</form:label>
                            <form:input path="correo" class="form-input" placeholder="ejemplo@correo.com"/>
                        </div>

                        <div class="form-group">
                            <form:label path="telefono" class="form-label">Teléfono</form:label>
                            <form:input path="telefono" class="form-input" placeholder="+51 9XX-XXX-XXX"/>
                        </div>

                        <div class="form-group">
                            <form:label path="contrasena" class="form-label">Contraseña</form:label>
                            <form:password path="contrasena" class="form-input" placeholder="********"/>
                        </div>

                        <div class="form-group">
                            <label for="confirm-password" class="form-label">Confirmar Contraseña</label>
                            <input type="password" name="confirm-password" id="confirm-password" class="form-input" placeholder="********">
                        </div>

                        <button type="submit" class="login-button">Registrarse</button>

                        <p class="signup-link">
                            ¿Ya tienes cuenta?
                            <a href="/login" class="color-primary-link">Inicia Sesión</a>
                        </p>
                    </form:form>
                </div>

                <div class="register-box benefit-box">
                    <h2 class="section-title benefit-title">¿Por qué registrarte?</h2>
                    <ul class="benefit-list">
                        <li>Acceder a ofertas exclusivas.</li>
                        <li>Guardar tus pedidos y direcciones.</li>
                        <li>Recibir novedades de lanzamientos.</li>
                        <li>Comprar de manera rápida y segura.</li>
                    </ul>
                </div>

            </div>
        </section>
    </main>
</div>
</body>
</html>
