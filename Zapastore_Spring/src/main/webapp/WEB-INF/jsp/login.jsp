<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión | ZapaStore</title>
    <style>
        /* ====== RESET ====== */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background: linear-gradient(135deg, #2c3e50, #4ca1af);
        }

        .login-container {
            background-color: #fff;
            width: 360px;
            padding: 2rem;
            border-radius: 10px;
            box-shadow: 0 8px 16px rgba(0,0,0,0.2);
        }

        .login-container h2 {
            text-align: center;
            color: #333;
            margin-bottom: 1.5rem;
        }

        .form-group {
            margin-bottom: 1rem;
        }

        label {
            display: block;
            color: #555;
            margin-bottom: 0.4rem;
            font-weight: 600;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 0.6rem;
            border: 1px solid #ccc;
            border-radius: 5px;
            outline: none;
            transition: border 0.3s;
        }

        input[type="text"]:focus,
        input[type="password"]:focus {
            border-color: #4ca1af;
        }

        button {
            width: 100%;
            background-color: #4ca1af;
            color: #fff;
            border: none;
            padding: 0.7rem;
            font-size: 1rem;
            font-weight: 600;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #2c3e50;
        }

        .error {
            margin-top: 1rem;
            color: #e74c3c;
            background-color: #fdecea;
            border: 1px solid #f5c2c0;
            padding: 0.5rem;
            text-align: center;
            border-radius: 5px;
        }

        .footer {
            margin-top: 1.5rem;
            text-align: center;
            color: #777;
            font-size: 0.85rem;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Iniciar Sesión</h2>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="correo">Correo electrónico:</label>
            <input type="text" id="correo" name="correo" value="${usuario.correo}" required>
        </div>

        <div class="form-group">
            <label for="contrasena">Contraseña:</label>
            <input type="password" id="contrasena" name="contrasena" required>
        </div>

        <button type="submit">Ingresar</button>

        <c:if test="${not empty error}">
            <div class="error">${error}</div>
        </c:if>
        <c:if test="${param.logout != null}">
            <div class="error" style="color:green; border-color:#b6e2b6; background:#eafbea;">
                Sesión cerrada correctamente.
            </div>
        </c:if>
    </form>

    <div class="footer">
        © 2025 ZapaStore - Sistema de Administración
    </div>
</div>

</body>
</html>
