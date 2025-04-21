/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ConsultaTareasConTotales.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 21/9/2023 11:23
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.excel;

import gob.ypfb.nova.utils.excel.ExcelYpfbAnnotation;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Objects;

@Data
public class ConsultaTareasConTotalesExcel {
    @ExcelYpfbAnnotation(posicionColumna = 1)
    private Integer poaGestion;
    @ExcelYpfbAnnotation(posicionColumna = 2)
    private String poaDescripcion;
    private Long areaIdArea;
    @ExcelYpfbAnnotation(posicionColumna = 3)
    private Integer areaCodigo;
    @ExcelYpfbAnnotation(posicionColumna = 4)
    private String areaSigla;
    @ExcelYpfbAnnotation(posicionColumna = 5)
    private String areaDescripcion;
    private String areaSiglaGthc;
    private Long operIdPoaArea;
    private Long operIdOperacion;
    private Long operIdAccionCortoPlazo;
    @ExcelYpfbAnnotation(posicionColumna = 6)
    private Integer operCodigoAccionCortoPlazo;
    @ExcelYpfbAnnotation(posicionColumna = 7)
    private String operAccionCortoPlazo;
    private Long operIdResponsableOperacion;
    private Long operIdTipoIndicadorOperacion;
    private String operTipoIndicadorOperacion;
    private Long operIdTipoValorOperacion;
    private String operTipoValorOperacion;
    @ExcelYpfbAnnotation(posicionColumna = 8)
    private String operCodigoOperacion;
    @ExcelYpfbAnnotation(posicionColumna = 9)
    private String operDescripcionOperacion;
    private BigDecimal operPonderacionOperacion;
    private Integer operOrdenOperacion;
    private Long tarIdTarea;
    private Long tarIdResponsableTarea;
    private Long tarIdTipoTarea;
    @ExcelYpfbAnnotation(posicionColumna = 12)
    private String tarTipoTarea;
    private Long tarIdTipoIndicadorTarea;
    @ExcelYpfbAnnotation(posicionColumna = 17)
    private String tarTipoIndicadorTarea;
    private Long tarIdTipoFormatoTarea;
    @ExcelYpfbAnnotation(posicionColumna = 16)
    private String tarTipoFormato;
    @ExcelYpfbAnnotation(posicionColumna = 10)
    private String tarCodigoTarea;
    private Integer tarOrdenTarea;
    @ExcelYpfbAnnotation(posicionColumna = 11)
    private String tarDescripcionTarea;
    @ExcelYpfbAnnotation(posicionColumna = 14)
    private BigDecimal tarPonderacionPorcentualTarea;
    @ExcelYpfbAnnotation(posicionColumna = 15)
    private String tarUnidadMedidaTarea;
    @ExcelYpfbAnnotation(posicionColumna = 18)
    private String tarMedioVerificacion;
    @ExcelYpfbAnnotation(posicionColumna = 19)
    private String tarRequiereOtraAreaDescripcion;
    private Boolean tarRequiereOtraArea;
    @ExcelYpfbAnnotation(posicionColumna = 20,cantidadDecimalesColumna = 2)
    private BigDecimal tarAvanceEsperado;
    @ExcelYpfbAnnotation(posicionColumna = 21,cantidadDecimalesColumna = 2)
    private BigDecimal tarLineaBase;
    @ExcelYpfbAnnotation(posicionColumna = 13)
    private String tarAreaResponsable;
    @ExcelYpfbAnnotation(posicionColumna = 23,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaEnero;
    @ExcelYpfbAnnotation(posicionColumna = 24,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaFebrero;
    @ExcelYpfbAnnotation(posicionColumna = 25,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaMarzo;
    @ExcelYpfbAnnotation(posicionColumna = 26,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaAbril;
    @ExcelYpfbAnnotation(posicionColumna = 27,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaMayo;
    @ExcelYpfbAnnotation(posicionColumna = 28,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaJunio;
    @ExcelYpfbAnnotation(posicionColumna = 29,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaJulio;
    @ExcelYpfbAnnotation(posicionColumna = 30,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaAgosto;
    @ExcelYpfbAnnotation(posicionColumna = 31,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaSeptiembre;
    @ExcelYpfbAnnotation(posicionColumna = 32,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaOctubre;
    @ExcelYpfbAnnotation(posicionColumna = 33,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaNoviembre;
    @ExcelYpfbAnnotation(posicionColumna = 34,cantidadDecimalesColumna = 2)
    private BigDecimal programacionTareaDiciembre;
    private BigDecimal ponderacionTareaMensualEnero;
    private BigDecimal ponderacionTareaMensualFebrero;
    private BigDecimal ponderacionTareaMensualMarzo;
    private BigDecimal ponderacionTareaMensualAbril;
    private BigDecimal ponderacionTareaMensualMayo;
    private BigDecimal ponderacionTareaMensualJunio;
    private BigDecimal ponderacionTareaMensualJulio;
    private BigDecimal ponderacionTareaMensualAgosto;
    private BigDecimal ponderacionTareaMensualSeptiembre;
    private BigDecimal ponderacionTareaMensualOctubre;
    private BigDecimal ponderacionTareaMensualNoviembre;
    private BigDecimal ponderacionTareaMensualDiciembre;
    @ExcelYpfbAnnotation(posicionColumna = 35,cantidadDecimalesColumna = 2)
    private BigDecimal gastoCorrienteTarea;
    @ExcelYpfbAnnotation(posicionColumna = 36,cantidadDecimalesColumna = 2)
    private BigDecimal inversionTarea;
    @ExcelYpfbAnnotation(posicionColumna = 22,cantidadDecimalesColumna = 2)
    private BigDecimal metaanual;


}
