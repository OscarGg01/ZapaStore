<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="page" value="metricas" />

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ZapaStore | Métricas de Ventas</title>
    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminStyles.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body class="admin-body">
<div class="admin-layout">
    <jsp:include page="/WEB-INF/jsp/fragments/sidebar.jsp"/>
    <main class="main-panel">
        <jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>

        <div class="content-wrapper">
            <div class="page-header">
                <h2 class="page-title">
                    <span class="material-symbols-outlined icon-title">analytics</span>
                    Métricas de Ventas
                </h2>
            </div>

            <c:if test="${not empty mensaje}">
                <div class="alert success">${mensaje}</div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert error">${error}</div>
            </c:if>

            <section class="metrics-area">
                <div class="metrics-grid">

                    <!-- Panel Día -->
                    <div class="metric-panel">
                        <div class="panel-header">
                            <h2>Ventas por Día</h2>
                            <form action="/admin/metricas" method="get" class="panel-controls">
                                <input type="date" name="dia" value="${diaSeleccionado}" />
                                <input type="hidden" name="semana" value="${fechaSemana}" />
                                <input type="hidden" name="mesInicio" value="${mesInicio}" />
                                <input type="hidden" name="mesFin" value="${mesFin}" />
                                <input type="hidden" name="anio" value="${anio}" />
                                <button type="submit" class="secondary-button-admin">Filtrar</button>
                            </form>
                        </div>
                        <div class="chart-wrap">
                            <canvas id="graficoDia"></canvas>
                        </div>
                        <div class="panel-foot">
                            <div class="metric-total">
                                <span>Total día</span>
                                <span class="total-value">S/ <c:out value="${totalDia != null ? totalDia : 0}" /></span>
                            </div>
                            <div class="metric-total">
                                <span>Pedidos</span>
                                <span class="total-value"><c:out value="${pedidosDia != null ? pedidosDia : 0}" /></span>
                            </div>
                        </div>
                    </div>

                    <!-- Panel Semana -->
                    <div class="metric-panel">
                        <div class="panel-header">
                            <h2>Ventas por Semana</h2>
                            <form action="/admin/metricas" method="get" class="panel-controls">
                                <input type="date" name="semana" value="${fechaSemana}" />
                                <input type="hidden" name="dia" value="${diaSeleccionado}" />
                                <input type="hidden" name="mesInicio" value="${mesInicio}" />
                                <input type="hidden" name="mesFin" value="${mesFin}" />
                                <input type="hidden" name="anio" value="${anio}" />
                                <button type="submit" class="secondary-button-admin">Filtrar</button>
                            </form>
                        </div>
                        <div class="chart-wrap">
                            <canvas id="graficoSemana"></canvas>
                        </div>
                        <div class="panel-foot">
                            <div class="metric-total">
                                <span>Total semana</span>
                                <span class="total-value">S/ <c:out value="${totalSemana != null ? totalSemana : 0}" /></span>
                            </div>
                            <div class="metric-total">
                                <span>Pedidos</span>
                                <span class="total-value"><c:out value="${pedidosSemana != null ? pedidosSemana : 0}" /></span>
                            </div>
                        </div>
                    </div>

                    <!-- Panel Mes (grande) -->
                    <div class="metric-panel large-panel">
                        <div class="panel-header">
                            <h2>Ventas por Mes</h2>
                            <form action="/admin/metricas" method="get" class="panel-controls">
                                <input type="number" name="mesInicio" min="1" max="12" value="${mesInicio}" placeholder="Mes inicio" />
                                <input type="number" name="mesFin" min="1" max="12" value="${mesFin}" placeholder="Mes fin" />
                                <input type="number" name="anio" value="${anio}" placeholder="Año" />
                                <input type="hidden" name="dia" value="${diaSeleccionado}" />
                                <input type="hidden" name="semana" value="${fechaSemana}" />
                                <button type="submit" class="secondary-button-admin">Filtrar</button>
                            </form>
                        </div>
                        <div class="chart-wrap">
                            <canvas id="graficoMes"></canvas>
                        </div>
                        <div class="panel-foot">
                            <div class="metric-total">
                                <span>Total mes</span>
                                <span class="total-value">S/ <c:out value="${totalMes != null ? totalMes : 0}" /></span>
                            </div>
                            <div class="metric-total">
                                <span>Pedidos</span>
                                <span class="total-value"><c:out value="${pedidosMes != null ? pedidosMes : 0}" /></span>
                            </div>
                        </div>
                    </div>

                </div>
            </section>
        </div>
    </main>
</div>

<script>
    const pedidosDiaHoras = [<c:forEach var="p" items="${listaPedidosDia}" varStatus="status">'${p.hora}'<c:if test="${!status.last}">,</c:if></c:forEach>];
    const pedidosDiaTotales = [<c:forEach var="p" items="${listaPedidosDia}" varStatus="status">${p.total}<c:if test="${!status.last}">,</c:if></c:forEach>];

    new Chart(document.getElementById('graficoDia').getContext('2d'), {
        type: 'bar',
        data: { labels: pedidosDiaHoras, datasets: [{ label: 'Total por pedido S/.', data: pedidosDiaTotales, backgroundColor: 'rgba(54, 162, 235, 0.7)', borderColor: 'rgba(54, 162, 235, 1)', borderWidth: 1 }] },
        options: { responsive: true, maintainAspectRatio: false, scales: { y: { beginAtZero: true } } }
    });

    const diasSemana = [<c:forEach var="d" items="${listaDiasSemana}" varStatus="status">'${d.fecha}'<c:if test="${!status.last}">,</c:if></c:forEach>];
    const totalesSemana = [<c:forEach var="d" items="${listaDiasSemana}" varStatus="status">${d.total}<c:if test="${!status.last}">,</c:if></c:forEach>];

    new Chart(document.getElementById('graficoSemana').getContext('2d'), {
        type: 'line',
        data: { labels: diasSemana, datasets: [{ label: 'Total por día S/.', data: totalesSemana, fill: false, borderColor: 'rgba(255, 159, 64, 1)', tension: 0.2 }] },
        options: { responsive: true, maintainAspectRatio: false, scales: { y: { beginAtZero: true } } }
    });

    const meses = [<c:forEach var="m" items="${listaMeses}" varStatus="status">'${m.mes}'<c:if test="${!status.last}">,</c:if></c:forEach>];
    const totalesMes = [<c:forEach var="m" items="${listaMeses}" varStatus="status">${m.total}<c:if test="${!status.last}">,</c:if></c:forEach>];

    new Chart(document.getElementById('graficoMes').getContext('2d'), {
        type: 'line',
        data: { labels: meses, datasets: [{ label: 'Total mensual S/.', data: totalesMes, fill: true, backgroundColor: 'rgba(75, 192, 192, 0.3)', borderColor: 'rgba(75, 192, 192, 1)', tension: 0.2 }] },
        options: { responsive: true, maintainAspectRatio: false, scales: { y: { beginAtZero: true } } }
    });
</script>

</body>
</html>
