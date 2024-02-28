package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
        String fechaCompra = request.getParameter("fechaCompra");
        Double precio = Double.valueOf(request.getParameter("precioCompra"));
        Integer cantidad = Integer.valueOf(request.getParameter("cantidadAccion"));
        Double costoTotal = precio * cantidad;
        Double cambio = Double.valueOf(request.getParameter("cambio"));
        boolean cambioNegativo = cambio < 0;

        if (cambioNegativo) {
            Double perdida = costoTotal - (costoTotal * (-cambio));
            MisAcciones.guardarAcción(nombreAccion, fechaCompra, precio, cantidad, costoTotal, cambio, perdida);
        } else {
            Double ganancia = costoTotal + (costoTotal * cambio);
            MisAcciones.guardarAcción(nombreAccion, fechaCompra, precio, cantidad, costoTotal, cambio, ganancia);
        }

        response.sendRedirect("MisAccionesController?ruta=mostrarAcciones");
    }

    private void mostrarAcciones(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<MisAcciones> misAccionesList = MisAcciones.getAll();

        request.setAttribute("misAccionesList", misAccionesList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

}
