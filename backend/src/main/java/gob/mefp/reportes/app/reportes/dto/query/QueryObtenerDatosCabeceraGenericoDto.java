package gob.mefp.reportes.app.reportes.dto.query;

import lombok.Data;

@Data
public class QueryObtenerDatosCabeceraGenericoDto {
    private String nombreUnidad;
    private String nombreSistema;
    private String codigoReporte;
    private String tituloReporte;
    private String usuario;
    private String fechaImpresion;

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public String getNombreSistema() {
        return nombreSistema;
    }

    public String getCodigoReporte() {
        return codigoReporte;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getFechaImpresion() {
        return fechaImpresion;
    }


    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public void setCodigoReporte(String codigoReporte) {
        this.codigoReporte = codigoReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setFechaImpresion(String fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }
}
