package com.example.proyecto_final_iot;

public class Equipo {

    private String sku;
    private String serie;
    private String marca;
    private String modelo;
    private  String descripcion;
    private String fechaRegistro;
    private String qrCodeUrl;
    private String id_codigodeSitio;
    private String dataImage_equipo;

    // Getter
    public String getId_codigodeSitio() {
        return id_codigodeSitio;
    }

    // Setter
    public void setId_codigodeSitio(String id_codigodeSitio) {
        this.id_codigodeSitio = id_codigodeSitio;
    }
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getDataImage_equipo() {
        return dataImage_equipo;
    }

    public void setDataImage_equipo(String dataImage_equipo) {
        this.dataImage_equipo = dataImage_equipo;
    }
}
