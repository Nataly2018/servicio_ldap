package gob.mefp.reportes.app.reportes.dto.query;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class QueryPresupuestoPorTipoTareaArea {
    private String area;
    private BigDecimal presupuestoActividad;
    private BigDecimal presupuestoProyecto;
}
