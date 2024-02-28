package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class MisAcciones implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "precio")
    private Double precio;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "costoTotal")
    private Double costoTotal;
    @Column(name = "cambio")
    private Double cambio;
    @Column(name = "ganancia")
    private Double porcentaje;

    public MisAcciones() {

    }

    public MisAcciones(String nombre, String fecha, Double precio, Integer cantidad, Double costoTotal, Double cambio, Double porcentaje) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.precio = precio;
        this.cantidad = cantidad;
        this.costoTotal = costoTotal;
        this.cambio = cambio;
        this.porcentaje = porcentaje;

    }

    public static void guardarAcción(String nombreAccion, String fechaCompra, Double precio, Integer cantidad, Double costoTotal, Double cambio, Double ganancia) {
        EntityManager em = Persistence.createEntityManagerFactory("mis_acciones").createEntityManager();
        MisAcciones miAcción = new MisAcciones(nombreAccion, fechaCompra, precio, cantidad, costoTotal, cambio, ganancia);

        em.getTransaction().begin();
        em.persist(miAcción);
        em.getTransaction().commit();
    }

    public static List<MisAcciones> getAll() {
        EntityManager em = Persistence.createEntityManagerFactory("mis_acciones").createEntityManager();

        String consultaJPQL = "SELECT m.* FROM misacciones m";
        Query query = em.createNativeQuery(consultaJPQL, MisAcciones.class);
        List<MisAcciones> misacciones = (List<MisAcciones>) query.getResultList();

        return misacciones;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Double getCambio() {
        return cambio;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentage) {
        this.porcentaje = porcentage;
    }
}
