package com.example.proyectocyes.controller;

import com.example.proyectocyes.model.MisAccionesEntity;
import com.example.proyectocyes.repository.MisAccionesRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MisAccionesController {
    private final EntityManager entityManager;

    @Autowired
    public MisAccionesRepository misAccionesRepository;

    public MisAccionesController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/")
    public String mostrarFormulario(Model model) {
        model.addAttribute("administrador", new MisAccionesEntity());
        return "index";
    }

    @PostMapping("/aniadir-accion")
    public String añadirAccion(@RequestParam String nombreAccion,
                               @RequestParam Date fechaCompra,
                               @RequestParam int precioCompra,
                               @RequestParam int cantidadAccion,
                               Model model) {
        MisAccionesEntity acciones = new MisAccionesEntity();

        guardarAccionesEnBDD(acciones, nombreAccion, fechaCompra, precioCompra, cantidadAccion, model);

        List<MisAccionesEntity> listaDeAcciones = obtenerAcciones();
        if (listaDeAcciones != null) {
            model.addAttribute("listaDeAcciones", listaDeAcciones);
        }
        return "index";
    }

    @PostMapping("/mostrar-acciones")
    public String añadirAccion(Model model) {
        MisAccionesEntity acciones = new MisAccionesEntity();

        List<MisAccionesEntity> listaDeAcciones = obtenerAcciones();
        if (listaDeAcciones != null) {
            model.addAttribute("listaDeAcciones", listaDeAcciones);
        }
        return "index";
    }

    public void guardarAccionesEnBDD(MisAccionesEntity acciones,
                                     String nombreAccion,
                                     Date fechaCompra,
                                     int precioCompra,
                                     int cantidadAccion,
                                     Model model){
        acciones.setNombreAccion(nombreAccion);
        acciones.setFechaCompra(fechaCompra);
        acciones.setPrecioCompra((double) precioCompra);
        acciones.setCantidadAcciones(cantidadAccion);

        double calculoCostoTotal = (precioCompra * cantidadAccion);
        model.addAttribute("calculoCosto", calculoCostoTotal);

        acciones.setCostoTotal(calculoCostoTotal);

        misAccionesRepository.save(acciones);
    }

    public List<MisAccionesEntity> obtenerAcciones() {
        try {
            TypedQuery<MisAccionesEntity> query = entityManager.createQuery("SELECT a FROM MisAccionesEntity a ORDER BY a.idCompra DESC", MisAccionesEntity.class);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
