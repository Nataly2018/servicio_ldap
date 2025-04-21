package gob.mefp.reportes.app.reportes.dto.response;

import gob.ypfb.nova.app.administracion.dto.query.QueryObtenerDatosCabeceraDto;
import lombok.Data;

import java.util.List;
@Data
public class ReportePresupuestoPorAcpDtoResp {
    /*private String sistema;
    private String versionPoa;
    private String codigoReporte;
    private String titulo;
    private String subTitulo;
    private String nombreUsuario;
    private String fechaImpresion;
    private String descripcion;

     */
    private QueryObtenerDatosCabeceraDto cabecera;
    private List<ListaPresupuestoPorAcpDto> datos;
}
