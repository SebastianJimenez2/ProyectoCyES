package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import clases.Unmarshalling;
import model.MisAcciones;

@WebServlet("/MisAccionesController")
public class MisAccionesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MisAccionesController() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.ruteador(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void ruteador(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ruta = (request.getParameter("ruta") != null) ? request.getParameter("ruta") : "ver";

        switch (ruta) {
            case "añadirAcción":
                this.añadirAcción(request, response);
                break;

            case "mostrarAcciones":
                this.mostrarAcciones(request, response);
                break;
        }
    }

    private void añadirAcción(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreAccion = request.getParameter("nombreAccion");
        Double precioActual = Unmarshalling.unmarshalling(nombreAccion);

        if (precioActual != 0.0) {
            String fechaCompra = request.getParameter("fechaCompra");
            Double precio = Double.valueOf(request.getParameter("precioCompra"));
            Integer cantidad = Integer.valueOf(request.getParameter("cantidadAccion"));
            Double costoTotal = precio * cantidad;

            Double gananciaOPerdida = precioActual * cantidad;
            Double cambioCálculo = (100.0 * (gananciaOPerdida /costoTotal)) - 100.0

            BigDecimal cambio = new BigDecimal(cambioCálculo);
            cambio = cambio.setScale(2, BigDecimal.ROUND_HALF_UP);

            MisAcciones.guardarAcción(nombreAccion, fechaCompra, precio, cantidad, costoTotal, cambio.doubleValue(), gananciaOPerdida);

            response.sendRedirect("MisAccionesController?ruta=mostrarAcciones");
        } else {
            boolean alerta = true;
            request.setAttribute("alerta", alerta);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    private void mostrarAcciones(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<MisAcciones> misAccionesList = MisAcciones.getAll();

        request.setAttribute("misAccionesList", misAccionesList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
