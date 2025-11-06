<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



<!DOCTYPE html>

<html lang="es">

<head>

    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>ZapaStore | Panel Admin - Métricas</title>



    <%-- Recursos Estáticos y Tipografía --%>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700&display=swap" rel="stylesheet">

    <link href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" rel="stylesheet"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/adminStyles.css">



</head>

<body class="admin-body">

<div class="admin-layout">



    <!-- Sidebar -->

    <jsp:include page="/WEB-INF/jsp/fragments/sidebar.jsp"/>



    <main class="main-panel">

        <!-- Header -->

        <jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>



        <div class="content-wrapper">

            <div class="page-header">

                <h2 class="page-title">

                    <span class="material-symbols-outlined icon-title">bar_chart</span>

                    Métricas de Ventas

                </h2>

                <button class="primary-button-admin">

                    <span class="material-symbols-outlined">add</span>

                    Generar Reporte

                </button>

            </div>



            <section class="metrics-area">

                <div class="metrics-grid">



                    <%-- PANEL 1: Ventas por día --%>

                    <section class="metric-panel">

                        <div class="panel-header">

                            <h2>Ventas por día</h2>

                            <div class="panel-controls">

                                <label for="select-day">Día:</label>

                                <input id="select-day" type="date" aria-label="Seleccionar día" value="2025-10-10" class="input-control">

                            </div>

                        </div>



                        <div class="panel-body">

                            <div class="panel-sub">Ejemplo de ventas hora a hora</div>

                            <div class="chart-wrap">

                                <img src="${pageContext.request.contextPath}/img/ventasdia.jpg" alt="Ejemplo ventas por día" class="chart-example">

                            </div>

                            <div class="panel-foot">

                                <div class="metric-total">

                                    <span>Total día</span>

                                    <strong class="total-value">S/ 3,450</strong>

                                </div>

                            </div>

                        </div>

                    </section>



                    <%-- PANEL 2: Ventas 7 días --%>

                    <section class="metric-panel">

                        <div class="panel-header">

                            <h2>Ventas por semana</h2>

                        </div>



                        <div class="panel-subcontrols">

                            <div class="control-item">

                                <label for="week-month">Mes</label>

                                <select id="week-month" class="input-control">

                                    <option selected>Junio</option>

                                </select>

                            </div>



                            <div class="control-item">

                                <label for="week-number">Semana</label>

                                <select id="week-number" class="input-control">

                                    <option>Semana 1</option>

                                    <option selected>Semana 2</option>

                                </select>

                            </div>

                        </div>



                        <div class="panel-body">

                            <div class="panel-sub">Ventas de los últimos 7 días</div>

                            <div class="chart-wrap">

                                <img src="${pageContext.request.contextPath}/img/ventassemana.jpg" alt="Ejemplo ventas por semana" class="chart-example">

                            </div>

                            <div class="panel-foot">

                                <div class="metric-total">

                                    <span>Total semana</span>

                                    <strong class="total-value">S/ 28,900</strong>

                                </div>

                            </div>

                        </div>

                    </section>



                    <%-- PANEL 3: Ventas por mes --%>

                    <section class="metric-panel large-panel">

                        <div class="panel-header">

                            <h2>Ventas por mes</h2>

                            <div class="panel-controls">

                                <label for="select-month">Mes:</label>

                                <select id="select-month" class="input-control">

                                    <option selected>Junio</option>

                                </select>

                                <label for="select-year">Año:</label>

                                <select id="select-year" class="input-control">

                                    <option selected>2025</option>

                                </select>

                            </div>

                        </div>



                        <div class="panel-body">

                            <div class="panel-sub">Ejemplo de ventas semanales en el mes</div>

                            <div class="chart-wrap">

                                <img src="${pageContext.request.contextPath}/img/ventasmes.jpg" alt="Ejemplo ventas por mes" class="chart-example">

                            </div>

                            <div class="panel-foot">

                                <div class="metric-total">

                                    <span>Total mes</span>

                                    <strong class="total-value">S/ 43,300</strong>

                                </div>

                            </div>

                        </div>

                    </section>

                </div>

            </section>

        </div>

    </main>



</div>

</body>

</html>