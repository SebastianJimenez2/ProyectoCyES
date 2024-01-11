<%@ page import="com.example.proyectocyes.model.MisAccionesEntity" %>
<%@ page import="java.util.List" %>
<script src="https://kit.fontawesome.com/40e27e6718.js" crossorigin="anonymous"></script>

<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proyecto CyES</title>
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
             src="https://proxy.spigotmc.org/f37ef23756c6cd721a2c0eeaadb8b71da2dfc427?url=https%3A%2F%2Fcdn.discordapp.com%2Fattachments%2F1031548493938040874%2F1034326592765313145%2Fstonks.png"
             alt="Logo">
    </div>

    <div>
        <i class="fas fa-cog tuerquita"></i>
    </div>
</header>
<main>
    <div class="tabla">
        <table>
            <thead>
            <tr>
                <th>Nombre de la accion</th>
                <th>Fecha de compra</th>
                <th>Precio de compra por accion</th>
                <th>Cantidad de acciones</th>
                <th>Costo total de compra</th>
            </tr>
            </thead>
            <tbody>
            <form action="/aniadir-accion" method="POST">
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
                    <div class="boton">
                        <input class="botonAnimado" type="submit" value="Agregar">
                    </div>
                </tr>
            </form>

            <div class>
                <form action="/mostrar-acciones" method="POST">
                    <div class="boton">
                        <input class="botonAnimado" type="submit" value="Mostrar">
                    </div>
                </form>
            </div>

            <%
                List<MisAccionesEntity> listaDeAcciones = (List<MisAccionesEntity>) request.getAttribute("listaDeAcciones");
                if (listaDeAcciones != null && !listaDeAcciones.isEmpty()) {
                    for (MisAccionesEntity acciones : listaDeAcciones) {
            %>
            <tr>
                <td><%= acciones.getNombreAccion() %>
                </td>
                <td><%= acciones.getFechaCompra() %>
                </td>
                <td><%= acciones.getPrecioCompra() %>
                </td>
                <td><%= acciones.getCantidadAcciones() %>
                </td>
                <td><%= acciones.getCostoTotal() %>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="5">Presione el boton 'mostrar' para ver sus acciones</td>
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
</body>
</html>
