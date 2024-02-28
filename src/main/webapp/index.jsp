<%@ page import="model.MisAcciones" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://kit.fontawesome.com/40e27e6718.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proyecto CyES</title>
    <script src="https://code.iconify.design/iconify-icon/1.0.7/iconify-icon.min.js"></script>
    <style>
        html, body {
            height: 100%;
            width: 100%;
            margin: 0;
        }

        body {
            font-family: 'Montserrat', sans-serif;
            background-color: rgba(51, 51, 51, 0.15);
            display: flex;
            flex-direction: column;
        }

        main {
            margin: 0 100px;
            flex: 1;
        }

        header {
            background-color: #333;
            color: #fff;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        footer {
            background-color: #333;
            color: #fff;
            padding: 10px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 30px;
        }

        table {
            border-collapse: collapse;
            margin: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
        }

        th, td {
            border: 1px solid #e0e0e0;
            text-align: center;
            padding: 12px;
            width: 20%;
        }

        th {
            background-color: #333;
            color: #fff;
        }

        input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #333;
            width: 120px;
            color: #fff;
            padding: 10px;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }

        input::placeholder {
            font-size: 12px;
        }

        .redesSociales {
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 15px
        }

        .aumentarTamaño {
            width: 120px;
        }

        .tabla {
            display: flex;
            justify-content: center;
            gap: 20px;
        }

        .botonAnimado {
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            transition: transform 0.3s ease;
        }

        .botonAnimado:hover {
            transform: translateY(5px);
        }

        .boton {
            margin-top: 20px;
            order: 2;
        }

        .letraPequenia {
            font-size: 12px;=
        }

        .tuerquita {
            transition: transform 0.3s ease-in-out, scale 0.3s ease-in-out;
        }

        .tuerquita:hover {
            transform: scale(1.8) rotate(360deg);
        }
    </style>
</head>
<body>

<header>
    <div>
        <img class="aumentarTamaño"
             src="https://assets-global.website-files.com/621d9c4840897da5db537578/6359a6499fb3aec09b09c927_Original%20Stonks%20Gold%20Horiz.png"
             alt="Logo">
    </div>

    <div>
        <i class="fas fa-cog tuerquita"></i>
    </div>
</header>
<main>
    <div class="tabla">
        <table id="miTabla">
            <thead>
            <tr>
                <th>Nombre de la accion</th>
                <th>
                    Fecha de compra
                    <a href="#" onclick="ordenarTabla(1); return false;">
                        <iconify-icon icon="icon-park-solid:sort" width="25" style="text-decoration: none; color: #FFF"></iconify-icon>
                    </a>
                </th>
                <th>Precio de compra por accion</th>
                <th>Cantidad de acciones</th>
                <th>Costo total de compra</th>
                <th>Cambio (%)</th>
                <th>Ganancia / Perdida</th>
            </tr>
            </thead>
            <tbody>
            <form action="MisAccionesController?ruta=añadirAcción" method="POST">
                <tr>
                    <td>
                        <input type="text" id="nombreAccion" name="nombreAccion" placeholder="Nombre de la accion" required>
                    </td>
                    <td>
                        <input type="date" id="fechaCompra" name="fechaCompra" placeholder="Fecha de compra" required>
                    </td>
                    <td>
                        <input type="text" id="precioCompra" name="precioCompra" placeholder="Precio de compra por accion" required>
                    </td>
                    <td>
                        <input type="text" id="cantidadAccion" name="cantidadAccion" placeholder="Cantidad de acciones" required>
                    </td>
                    <td>
                        <p class="letraPequenia">Este valor se calcula luego de insertar los datos </p>
                    </td>
                    <td>
                        <input type="text" id="cambio" name="cambio" placeholder="Cambio" required>
                    </td>
                    <td>
                        <p class="letraPequenia">Este valor se calcula luego de insertar los datos </p>
                    </td>
                    <div class="boton">
                        <input class="botonAnimado" type="submit" value="Agregar">
                    </div>
                </tr>
            </form>

            <div class>
                <form action="MisAccionesController?ruta=mostrarAcciones" method="POST">
                    <div class="boton">
                        <input class="botonAnimado" type="submit" value="Mostrar">
                    </div>
                </form>
            </div>

            <%
                List<MisAcciones> listaDeAcciones = (List<MisAcciones>) request.getAttribute("misAccionesList");
                if (listaDeAcciones != null && !listaDeAcciones.isEmpty()) {
                    for (MisAcciones acciones : listaDeAcciones) {
            %>
            <tr>
                <td><%= acciones.getNombre() %>
                </td>
                <td><%= acciones.getFecha() %>
                </td>
                <td><%= acciones.getPrecio() %>
                </td>
                <td><%= acciones.getCantidad() %>
                </td>
                <td><%= acciones.getCostoTotal() %>
                </td>
                <td><%= acciones.getCambio() %>
                </td>
                <td><%= acciones.getPorcentaje() %>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="7">Presione el boton 'mostrar' para ver sus acciones</td>
            </tr>
            <%
                }
            %>

            </tbody>
        </table>
    </div>
</main>

<footer>
    <div>
        stonks@epn.edu.ec
    </div>

    <div class="redesSociales">
        <i class="fa-brands fa-facebook mr-4"></i>
        <i class="fa-brands fa-twitter mr-4"></i>
        <i class="fa-brands fa-instagram mr-4"></i>
    </div>
</footer>

<script>
    function ordenarTabla(columna) {
        const table = document.getElementById('miTabla');
        const tbody = table.querySelector('tbody');
        const rows = [].slice.call(tbody.querySelectorAll('tr'));

        rows.slice(1).sort(function(a, b) {
            const aValue = a.cells[columna].textContent.trim().toLowerCase();
            const bValue = b.cells[columna].textContent.trim().toLowerCase();
            return bValue.localeCompare(aValue);
        }).forEach(function(row) {
            tbody.appendChild(row);
        });
    }
</script>
</body>
</html>
