const API_URL = 'http://localhost:8080/api/v1/admin/categorias';
const tablaCategorias = document.getElementById('tabla-categorias');

// ----------------------------------------------------
// READ (Leer todas)
// ----------------------------------------------------

async function cargarCategorias() {
    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error('Error al cargar las categorías');
        
        const categorias = await response.json();
        tablaCategorias.innerHTML = ''; // Limpiar tabla
        
        categorias.forEach(cat => {
            const row = tablaCategorias.insertRow();
            
            // 1. ID de la Categoría
            row.insertCell().textContent = cat.categoriaId;

            // 2. Nombre de la Categoría
            row.insertCell().textContent = cat.nombre;

            // 3. Estado (Aplicando estilos de tu CSS)
            const estadoCell = row.insertCell();
            const estadoActivo = cat.estado === 'Activo';
            const color = estadoActivo ? 'green' : 'red';
            const simbolo = estadoActivo ? 'check_circle' : 'cancel';
            
            estadoCell.innerHTML = `
                <span class="material-symbols-outlined" style="color:${color}; font-size: 1.1rem; vertical-align: middle;">${simbolo}</span>
                <span class="estado-texto" style="color:${color};">${cat.estado}</span>
            `;

            // 4. Acciones (SOLO EDITAR)
            const actionsCell = row.insertCell();
            
            // Botón Editar (Lápiz)
            const btnEditar = document.createElement('button');
            btnEditar.className = 'icon-button edit';
            btnEditar.title = 'Editar o Desactivar categoría';
            btnEditar.innerHTML = '<span class="material-symbols-outlined">edit</span>';
            btnEditar.onclick = () => prepararActualizacion(cat);
            actionsCell.appendChild(btnEditar);

            // *** SE ELIMINÓ EL CÓDIGO DEL BOTÓN ELIMINAR ***
        });

    } catch (error) {
        console.error('Fetch Error:', error);
        // Si hay un error, mostrar un mensaje en la tabla
        tablaCategorias.innerHTML = `<tr><td colspan="4" style="text-align:center; color:red;">Error al cargar: ${error.message}. Asegúrate de que Spring Boot esté corriendo.</td></tr>`;
    }
}

// ----------------------------------------------------
// CREATE (Crear)
// ----------------------------------------------------

async function crearCategoria() {
    const nombre = document.getElementById('nombre-crear').value;
    const estado = document.getElementById('estado-crear').value; // 'Activo' o 'Inactivo' desde el select
    
    if (!nombre) {
        alert("El nombre es obligatorio.");
        return;
    }

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nombre, estado }),
        });

        if (response.status === 201) {
            document.getElementById('nombre-crear').value = '';
            cargarCategorias(); // Recargar la lista
        } else {
             const errorText = await response.text();
             throw new Error(`Error al crear: ${response.status}. Mensaje: ${errorText.substring(0, 100)}...`);
        }

    } catch (error) {
        console.error('Error al crear:', error);
        alert('Error al crear la categoría. Revisa la consola para más detalles.');
    }
}

// ----------------------------------------------------
// UPDATE (Actualizar)
// ----------------------------------------------------

function prepararActualizacion(cat) {
    // Muestra el formulario de actualización
    document.getElementById('form-actualizar').style.display = 'flex';
    document.getElementById('form-crear').style.display = 'none';
    
    // Llena los campos con los datos de la categoría seleccionada
    document.getElementById('id-actualizar').value = cat.categoriaId;
    document.getElementById('nombre-actualizar').value = cat.nombre;
    document.getElementById('estado-actualizar').value = cat.estado; // El valor del select coincidirá
}

function cancelarActualizacion() {
    // Oculta el formulario de actualización
    document.getElementById('form-actualizar').style.display = 'none';
    document.getElementById('form-crear').style.display = 'flex';
}

async function guardarActualizacion() {
    const id = document.getElementById('id-actualizar').value;
    const nombre = document.getElementById('nombre-actualizar').value;
    const estado = document.getElementById('estado-actualizar').value;
    
    if (!nombre) {
        alert("El nombre es obligatorio.");
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ nombre, estado }),
        });

        if (response.status === 200) {
            cancelarActualizacion(); // Ocultar formulario
            cargarCategorias(); // Recargar la lista
        } else {
             const errorText = await response.text();
             throw new Error(`Error al actualizar: ${response.status}. Mensaje: ${errorText.substring(0, 100)}...`);
        }

    } catch (error) {
        console.error('Error al actualizar:', error);
        alert('Error al actualizar la categoría. Revisa la consola para más detalles.');
    }
}

// ----------------------------------------------------
// DELETE (Funciones de eliminación/desactivación eliminadas)
// ----------------------------------------------------

// Inicializar: Cargar la lista al cargar la página
window.onload = cargarCategorias;