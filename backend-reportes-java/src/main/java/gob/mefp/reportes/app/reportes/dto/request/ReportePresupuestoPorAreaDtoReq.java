package gob.mefp.reportes.app.reportes.dto.request;

import lombok.Data;

import java.util.List;
@Data
public class ReportePresupuestoPorAreaDtoReq {
    private Long idVersion;
    private List<Long> idPoaArea;
    private List<Long> idTipoGasto;
    private List<Long> idTipoTarea;
    private List<String> grupoPartida;
    private List<Long> idPartidaPresupuestaria;
    private List<Long> idFuenteOrganismoFinanciador;
    private List<String> codigoUnidadEjecutora;
}
