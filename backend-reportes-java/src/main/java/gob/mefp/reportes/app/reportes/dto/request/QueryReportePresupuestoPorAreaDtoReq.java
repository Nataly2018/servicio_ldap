package gob.mefp.reportes.app.reportes.dto.request;

import lombok.Data;

@Data
public class QueryReportePresupuestoPorAreaDtoReq {
        private String[] area;
        private String[] tipoTarea;
        private Double[] gastoCorriente;
        private Double[] inversion;
}
