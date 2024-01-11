package com.example.proyectocyes.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "mis_acciones", schema = "public", catalog = "ProyectoCyES")
public class MisAccionesEntity {
    @Id
    @SequenceGenerator(name = "mis_acciones_id_compra_seq", sequenceName = "mis_acciones_id_compra_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mis_acciones_id_compra_seq")
    @Column(name = "id_compra", nullable = false)
    private Integer idCompra;
    @Basic
    @Column(name = "nombre_accion", nullable = true, length = -1)
    private String nombreAccion;
    @Basic
    @Column(name = "fecha_compra", nullable = true)
    private Date fechaCompra;
    @Basic
    @Column(name = "precio_compra", nullable = true, precision = 0)
    private Double precioCompra;
    @Basic
    @Column(name = "cantidad_acciones", nullable = true)
    private Integer cantidadAcciones;
    @Basic
    @Column(name = "costo_total", nullable = true, precision = 0)
    private Double costoTotal;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getNombreAccion() {
        return nombreAccion;
    }

    public void setNombreAccion(String nombreAccion) {
        this.nombreAccion = nombreAccion;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getCantidadAcciones() {
        return cantidadAcciones;
    }

    public void setCantidadAcciones(Integer cantidadAcciones) {
        this.cantidadAcciones = cantidadAcciones;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MisAccionesEntity that = (MisAccionesEntity) o;

        if (idCompra != null ? !idCompra.equals(that.idCompra) : that.idCompra != null) return false;
        if (nombreAccion != null ? !nombreAccion.equals(that.nombreAccion) : that.nombreAccion != null) return false;
        if (fechaCompra != null ? !fechaCompra.equals(that.fechaCompra) : that.fechaCompra != null) return false;
        if (precioCompra != null ? !precioCompra.equals(that.precioCompra) : that.precioCompra != null) return false;
        if (cantidadAcciones != null ? !cantidadAcciones.equals(that.cantidadAcciones) : that.cantidadAcciones != null)
            return false;
        if (costoTotal != null ? !costoTotal.equals(that.costoTotal) : that.costoTotal != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCompra != null ? idCompra.hashCode() : 0;
        result = 31 * result + (nombreAccion != null ? nombreAccion.hashCode() : 0);
        result = 31 * result + (fechaCompra != null ? fechaCompra.hashCode() : 0);
        result = 31 * result + (precioCompra != null ? precioCompra.hashCode() : 0);
        result = 31 * result + (cantidadAcciones != null ? cantidadAcciones.hashCode() : 0);
        result = 31 * result + (costoTotal != null ? costoTotal.hashCode() : 0);
        return result;
    }
}
