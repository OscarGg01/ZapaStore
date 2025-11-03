<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>ZapaStore | Carrito de Compras</title>
    <link rel="preconnect" href="https://fonts.gstatic.com/" crossorigin>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Be+Vietnam+Pro:wght@400;500;700;900&family=Noto+Sans:wght@400;500;700;900&display=swap">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
</head>
<body class="light-mode">
    <div class="page-container">
        
        <header class="main-header">
            <div class="container header-inner">
                <div class="header-left">
                    <a class="logo" href="main.html">
                      <img src="${pageContext.request.contextPath}/img/logo.png" alt="Logo ZapaStore" class="logo-image" height="24">
                      <h1 class="logo-text">ZapaStore</h1>
                    </a>
                    
                </div>
                <div class="header-right">
                    <button class="secondary-button">Mi Cuenta</button> 
                    
                    <a href="carrito.html" class="cart-icon-link" aria-label="Carrito de Compras" style="color: var(--color-primary);">
                        <i class="fa-solid fa-cart-shopping cart-icon"></i>
                    </a>

                    <div class="profile-pic" style='background-image: url("img/user.png");' aria-label="Perfil de Usuario"></div>
                </div>
            </div>
        </header>
        <main class="main-content">
            <section class="container cart-layout section-padding">
                
                <h1 class="section-title" style="margin-bottom: var(--space-8);">Mi Carrito de Compras</h1>
                
                <div class="cart-content-grid">
                    
                    <div class="cart-product-list">
                        
                        <div class="cart-item">
                            <img src="${pageContext.request.contextPath}/img/zapatilla1.jpg" alt="Nike Air Max" class="cart-item-image">
                            
                            <div class="cart-item-info">
                                <h3 class="cart-item-title">Nike Air Max</h3>
                                <p class="cart-item-category">Running | Talla 42</p>
                                <p class="cart-item-price">Precio Unitario: <span class="price-value">S/ 450.00</span></p>
                            </div>

                            <div class="cart-item-quantity">
                                <label for="qty-1">Cantidad:</label>
                                <input type="number" id="qty-1" value="1" min="1" class="quantity-input">
                            </div>

                            <div class="cart-item-subtotal">
                                <p>Subtotal</p>
                                <p class="subtotal-value">S/ 450.00</p>
                            </div>

                            <div class="cart-item-actions">
                                <button class="delete-button" title="Eliminar Producto"><i class="fa-solid fa-trash"></i></button>
                            </div>
                        </div>
                        
                        <div class="cart-item">
                            <img src="${pageContext.request.contextPath}/img/zapatilla2.jpg" alt="Adidas Ultraboost" class="cart-item-image">
                            
                            <div class="cart-item-info">
                                <h3 class="cart-item-title">Adidas Ultraboost</h3>
                                <p class="cart-item-category">Urbanas | Talla 40</p>
                                <p class="cart-item-price">Precio Unitario: <span class="price-value">S/ 499.00</span></p>
                            </div>

                            <div class="cart-item-quantity">
                                <label for="qty-2">Cantidad:</label>
                                <input type="number" id="qty-2" value="2" min="1" class="quantity-input">
                            </div>

                            <div class="cart-item-subtotal">
                                <p>Subtotal</p>
                                <p class="subtotal-value">S/ 998.00</p>
                            </div>

                            <div class="cart-item-actions">
                                <button class="delete-button" title="Eliminar Producto"><i class="fa-solid fa-trash"></i></button>
                            </div>
                        </div>

                    </div>
                    
                    <div class="cart-summary-box card">
                        <h3 class="summary-heading">Resumen del Pedido</h3>
                        
                        <div class="summary-line">
                            <span>Productos (3)</span>
                            <span>S/ 1,448.00</span>
                        </div>
                        <div class="summary-line">
                            <span>Costo de Envío</span>
                            <span class="free-shipping">¡Gratis!</span>
                        </div>
                        
                        <div class="summary-total">
                            <span class="total-label">Total a Pagar</span>
                            <span class="total-value">S/ 1,448.00</span>
                        </div>

                        <button class="primary-button checkout-button">
                            <i class="fa-solid fa-lock"></i> Proceder al Pago
                        </button>
                        
                        <a href="catalogo-postlogin.html" class="secondary-link">
                            <i class="fa-solid fa-reply"></i> Seguir comprando
                        </a>
                    </div>
                </div>

            </section>
        </main>
        
        </div>
</body>
</html>