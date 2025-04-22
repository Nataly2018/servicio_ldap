package gob.mefp.reportes.app.reportes.dto.query;

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
}
