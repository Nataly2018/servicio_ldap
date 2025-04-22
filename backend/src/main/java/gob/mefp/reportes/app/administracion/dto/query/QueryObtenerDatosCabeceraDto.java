package gob.mefp.reportes.app.administracion.dto.query;

import lombok.Data;

@Data
public class QueryObtenerDatosCabeceraDto {
    private String sistema;
    private String versionPoa;
    private String codigoReporte;
    private String titulo;
    private String subTitulo;
    private String nombreUsuario;
    private String fechaImpresion;
    private String descripcion;

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaImpresion(String fechaImpresion) {
        this.fechaImpresion = fechaImpresion;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCodigoReporte(String codigoReporte) {
        this.codigoReporte = codigoReporte;
    }

    public void setVersionPoa(String versionPoa) {
        this.versionPoa = versionPoa;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }
}