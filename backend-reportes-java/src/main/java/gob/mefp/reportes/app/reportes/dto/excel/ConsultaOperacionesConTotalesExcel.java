/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ConsultaOperacionesConTotales.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 20/9/2023 11:51
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.excel;

import gob.ypfb.nova.utils.excel.ExcelYpfbAnnotation;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Objects;


public class ConsultaOperacionesConTotalesExcel {
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
    @ExcelYpfbAnnotation(posicionColumna = 8)
    private String operCodigoOperacion;
    @ExcelYpfbAnnotation(posicionColumna = 9)
    private String operDescripcionOperacion;
    @ExcelYpfbAnnotation(posicionColumna = 10)
    private BigDecimal operPonderacionOperacion;


    @ExcelYpfbAnnotation(posicionColumna = 11)
    private String operTipoIndicadorOperacion;

    @ExcelYpfbAnnotation(posicionColumna = 12,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionEnero;
    @ExcelYpfbAnnotation(posicionColumna = 13,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionFebrero;
    @ExcelYpfbAnnotation(posicionColumna = 14,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionMarzo;
    @ExcelYpfbAnnotation(posicionColumna = 15,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionAbril;
    @ExcelYpfbAnnotation(posicionColumna = 16,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionMayo;
    @ExcelYpfbAnnotation(posicionColumna = 17,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionJunio;
    @ExcelYpfbAnnotation(posicionColumna = 18,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionJulio;
    @ExcelYpfbAnnotation(posicionColumna = 19,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionAgosto;
    @ExcelYpfbAnnotation(posicionColumna = 20,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionSeptiembre;
    @ExcelYpfbAnnotation(posicionColumna = 21,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionOctubre;
    @ExcelYpfbAnnotation(posicionColumna = 22,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionNoviembre;
    @ExcelYpfbAnnotation(posicionColumna = 23,cantidadDecimalesColumna = 2)
    private BigDecimal programacionOperacionDiciembre;
    @ExcelYpfbAnnotation(posicionColumna = 24,cantidadDecimalesColumna = 2)
    private BigDecimal gastoCorrienteOperacion;
    @ExcelYpfbAnnotation(posicionColumna = 25,cantidadDecimalesColumna = 2)
    private BigDecimal inversionOperacion;


    private Long operIdTipoValorOperacion;

    private String operTipoValorOperacion;


    private Integer operOrdenOperacion;

    private BigDecimal ponderacionOperacionMensualEnero;
    private BigDecimal ponderacionOperacionMensualFebrero;
    private BigDecimal ponderacionOperacionMensualMarzo;
    private BigDecimal ponderacionOperacionMensualAbril;
    private BigDecimal ponderacionOperacionMensualMayo;
    private BigDecimal ponderacionOperacionMensualJunio;
    private BigDecimal ponderacionOperacionMensualJulio;
    private BigDecimal ponderacionOperacionMensualAgosto;
    private BigDecimal ponderacionOperacionMensualSeptiembre;
    private BigDecimal ponderacionOperacionMensualOctubre;
    private BigDecimal ponderacionOperacionMensualNoviembre;
    private BigDecimal ponderacionOperacionMensualDiciembre;

    @Basic
    @Column(name = "poa_gestion")
    public Integer getPoaGestion() {
        return poaGestion;
    }

    public void setPoaGestion(Integer poaGestion) {
        this.poaGestion = poaGestion;
    }

    @Basic
    @Column(name = "poa_descripcion")
    public String getPoaDescripcion() {
        return poaDescripcion;
    }

    public void setPoaDescripcion(String poaDescripcion) {
        this.poaDescripcion = poaDescripcion;
    }

    @Basic
    @Column(name = "area_id_area")
    public Long getAreaIdArea() {
        return areaIdArea;
    }

    public void setAreaIdArea(Long areaIdArea) {
        this.areaIdArea = areaIdArea;
    }

    @Basic
    @Column(name = "area_codigo")
    public Integer getAreaCodigo() {
        return areaCodigo;
    }

    public void setAreaCodigo(Integer areaCodigo) {
        this.areaCodigo = areaCodigo;
    }

    @Basic
    @Column(name = "area_sigla")
    public String getAreaSigla() {
        return areaSigla;
    }

    public void setAreaSigla(String areaSigla) {
        this.areaSigla = areaSigla;
    }

    @Basic
    @Column(name = "area_descripcion")
    public String getAreaDescripcion() {
        return areaDescripcion;
    }

    public void setAreaDescripcion(String areaDescripcion) {
        this.areaDescripcion = areaDescripcion;
    }

    @Basic
    @Column(name = "area_sigla_gthc")
    public String getAreaSiglaGthc() {
        return areaSiglaGthc;
    }

    public void setAreaSiglaGthc(String areaSiglaGthc) {
        this.areaSiglaGthc = areaSiglaGthc;
    }

    @Basic
    @Column(name = "oper_id_poa_area")
    public Long getOperIdPoaArea() {
        return operIdPoaArea;
    }

    public void setOperIdPoaArea(Long operIdPoaArea) {
        this.operIdPoaArea = operIdPoaArea;
    }

    @Basic
    @Column(name = "oper_id_operacion")
    public Long getOperIdOperacion() {
        return operIdOperacion;
    }

    public void setOperIdOperacion(Long operIdOperacion) {
        this.operIdOperacion = operIdOperacion;
    }

    @Basic
    @Column(name = "oper_id_accion_corto_plazo")
    public Long getOperIdAccionCortoPlazo() {
        return operIdAccionCortoPlazo;
    }

    public void setOperIdAccionCortoPlazo(Long operIdAccionCortoPlazo) {
        this.operIdAccionCortoPlazo = operIdAccionCortoPlazo;
    }

    @Basic
    @Column(name = "oper_codigo_accion_corto_plazo")
    public Integer getOperCodigoAccionCortoPlazo() {
        return operCodigoAccionCortoPlazo;
    }

    public void setOperCodigoAccionCortoPlazo(Integer operCodigoAccionCortoPlazo) {
        this.operCodigoAccionCortoPlazo = operCodigoAccionCortoPlazo;
    }

    @Basic
    @Column(name = "oper_accion_corto_plazo")
    public String getOperAccionCortoPlazo() {
        return operAccionCortoPlazo;
    }

    public void setOperAccionCortoPlazo(String operAccionCortoPlazo) {
        this.operAccionCortoPlazo = operAccionCortoPlazo;
    }

    @Basic
    @Column(name = "oper_id_responsable_operacion")
    public Long getOperIdResponsableOperacion() {
        return operIdResponsableOperacion;
    }

    public void setOperIdResponsableOperacion(Long operIdResponsableOperacion) {
        this.operIdResponsableOperacion = operIdResponsableOperacion;
    }

    @Basic
    @Column(name = "oper_id_tipo_indicador_operacion")
    public Long getOperIdTipoIndicadorOperacion() {
        return operIdTipoIndicadorOperacion;
    }

    public void setOperIdTipoIndicadorOperacion(Long operIdTipoIndicadorOperacion) {
        this.operIdTipoIndicadorOperacion = operIdTipoIndicadorOperacion;
    }

    @Basic
    @Column(name = "oper_tipo_indicador_operacion")
    public String getOperTipoIndicadorOperacion() {
        return operTipoIndicadorOperacion;
    }

    public void setOperTipoIndicadorOperacion(String operTipoIndicadorOperacion) {
        this.operTipoIndicadorOperacion = operTipoIndicadorOperacion;
    }

    @Basic
    @Column(name = "oper_id_tipo_valor_operacion")
    public Long getOperIdTipoValorOperacion() {
        return operIdTipoValorOperacion;
    }

    public void setOperIdTipoValorOperacion(Long operIdTipoValorOperacion) {
        this.operIdTipoValorOperacion = operIdTipoValorOperacion;
    }

    @Basic
    @Column(name = "oper_tipo_valor_operacion")
    public String getOperTipoValorOperacion() {
        return operTipoValorOperacion;
    }

    public void setOperTipoValorOperacion(String operTipoValorOperacion) {
        this.operTipoValorOperacion = operTipoValorOperacion;
    }

    @Basic
    @Column(name = "oper_codigo_operacion")
    public String getOperCodigoOperacion() {
        return operCodigoOperacion;
    }

    public void setOperCodigoOperacion(String operCodigoOperacion) {
        this.operCodigoOperacion = operCodigoOperacion;
    }

    @Basic
    @Column(name = "oper_descripcion_operacion")
    public String getOperDescripcionOperacion() {
        return operDescripcionOperacion;
    }

    public void setOperDescripcionOperacion(String operDescripcionOperacion) {
        this.operDescripcionOperacion = operDescripcionOperacion;
    }

    @Basic
    @Column(name = "oper_ponderacion_operacion")
    public BigDecimal getOperPonderacionOperacion() {
        return operPonderacionOperacion;
    }

    public void setOperPonderacionOperacion(BigDecimal operPonderacionOperacion) {
        this.operPonderacionOperacion = operPonderacionOperacion;
    }

    @Basic
    @Column(name = "oper_orden_operacion")
    public Integer getOperOrdenOperacion() {
        return operOrdenOperacion;
    }

    public void setOperOrdenOperacion(Integer operOrdenOperacion) {
        this.operOrdenOperacion = operOrdenOperacion;
    }

    @Basic
    @Column(name = "programacion_operacion_enero")
    public BigDecimal getProgramacionOperacionEnero() {
        return programacionOperacionEnero;
    }

    public void setProgramacionOperacionEnero(BigDecimal programacionOperacionEnero) {
        this.programacionOperacionEnero = programacionOperacionEnero;
    }

    @Basic
    @Column(name = "programacion_operacion_febrero")
    public BigDecimal getProgramacionOperacionFebrero() {
        return programacionOperacionFebrero;
    }

    public void setProgramacionOperacionFebrero(BigDecimal programacionOperacionFebrero) {
        this.programacionOperacionFebrero = programacionOperacionFebrero;
    }

    @Basic
    @Column(name = "programacion_operacion_marzo")
    public BigDecimal getProgramacionOperacionMarzo() {
        return programacionOperacionMarzo;
    }

    public void setProgramacionOperacionMarzo(BigDecimal programacionOperacionMarzo) {
        this.programacionOperacionMarzo = programacionOperacionMarzo;
    }

    @Basic
    @Column(name = "programacion_operacion_abril")
    public BigDecimal getProgramacionOperacionAbril() {
        return programacionOperacionAbril;
    }

    public void setProgramacionOperacionAbril(BigDecimal programacionOperacionAbril) {
        this.programacionOperacionAbril = programacionOperacionAbril;
    }

    @Basic
    @Column(name = "programacion_operacion_mayo")
    public BigDecimal getProgramacionOperacionMayo() {
        return programacionOperacionMayo;
    }

    public void setProgramacionOperacionMayo(BigDecimal programacionOperacionMayo) {
        this.programacionOperacionMayo = programacionOperacionMayo;
    }

    @Basic
    @Column(name = "programacion_operacion_junio")
    public BigDecimal getProgramacionOperacionJunio() {
        return programacionOperacionJunio;
    }

    public void setProgramacionOperacionJunio(BigDecimal programacionOperacionJunio) {
        this.programacionOperacionJunio = programacionOperacionJunio;
    }

    @Basic
    @Column(name = "programacion_operacion_julio")
    public BigDecimal getProgramacionOperacionJulio() {
        return programacionOperacionJulio;
    }

    public void setProgramacionOperacionJulio(BigDecimal programacionOperacionJulio) {
        this.programacionOperacionJulio = programacionOperacionJulio;
    }

    @Basic
    @Column(name = "programacion_operacion_agosto")
    public BigDecimal getProgramacionOperacionAgosto() {
        return programacionOperacionAgosto;
    }

    public void setProgramacionOperacionAgosto(BigDecimal programacionOperacionAgosto) {
        this.programacionOperacionAgosto = programacionOperacionAgosto;
    }

    @Basic
    @Column(name = "programacion_operacion_septiembre")
    public BigDecimal getProgramacionOperacionSeptiembre() {
        return programacionOperacionSeptiembre;
    }

    public void setProgramacionOperacionSeptiembre(BigDecimal programacionOperacionSeptiembre) {
        this.programacionOperacionSeptiembre = programacionOperacionSeptiembre;
    }

    @Basic
    @Column(name = "programacion_operacion_octubre")
    public BigDecimal getProgramacionOperacionOctubre() {
        return programacionOperacionOctubre;
    }

    public void setProgramacionOperacionOctubre(BigDecimal programacionOperacionOctubre) {
        this.programacionOperacionOctubre = programacionOperacionOctubre;
    }

    @Basic
    @Column(name = "programacion_operacion_noviembre")
    public BigDecimal getProgramacionOperacionNoviembre() {
        return programacionOperacionNoviembre;
    }

    public void setProgramacionOperacionNoviembre(BigDecimal programacionOperacionNoviembre) {
        this.programacionOperacionNoviembre = programacionOperacionNoviembre;
    }

    @Basic
    @Column(name = "programacion_operacion_diciembre")
    public BigDecimal getProgramacionOperacionDiciembre() {
        return programacionOperacionDiciembre;
    }

    public void setProgramacionOperacionDiciembre(BigDecimal programacionOperacionDiciembre) {
        this.programacionOperacionDiciembre = programacionOperacionDiciembre;
    }

    @Basic
    @Column(name = "gasto_corriente_operacion")
    public BigDecimal getGastoCorrienteOperacion() {
        return gastoCorrienteOperacion;
    }

    public void setGastoCorrienteOperacion(BigDecimal gastoCorrienteOperacion) {
        this.gastoCorrienteOperacion = gastoCorrienteOperacion;
    }

    @Basic
    @Column(name = "inversion_operacion")
    public BigDecimal getInversionOperacion() {
        return inversionOperacion;
    }

    public void setInversionOperacion(BigDecimal inversionOperacion) {
        this.inversionOperacion = inversionOperacion;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_enero")
    public BigDecimal getPonderacionOperacionMensualEnero() {
        return ponderacionOperacionMensualEnero;
    }

    public void setPonderacionOperacionMensualEnero(BigDecimal ponderacionOperacionMensualEnero) {
        this.ponderacionOperacionMensualEnero = ponderacionOperacionMensualEnero;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_febrero")
    public BigDecimal getPonderacionOperacionMensualFebrero() {
        return ponderacionOperacionMensualFebrero;
    }

    public void setPonderacionOperacionMensualFebrero(BigDecimal ponderacionOperacionMensualFebrero) {
        this.ponderacionOperacionMensualFebrero = ponderacionOperacionMensualFebrero;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_marzo")
    public BigDecimal getPonderacionOperacionMensualMarzo() {
        return ponderacionOperacionMensualMarzo;
    }

    public void setPonderacionOperacionMensualMarzo(BigDecimal ponderacionOperacionMensualMarzo) {
        this.ponderacionOperacionMensualMarzo = ponderacionOperacionMensualMarzo;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_abril")
    public BigDecimal getPonderacionOperacionMensualAbril() {
        return ponderacionOperacionMensualAbril;
    }

    public void setPonderacionOperacionMensualAbril(BigDecimal ponderacionOperacionMensualAbril) {
        this.ponderacionOperacionMensualAbril = ponderacionOperacionMensualAbril;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_mayo")
    public BigDecimal getPonderacionOperacionMensualMayo() {
        return ponderacionOperacionMensualMayo;
    }

    public void setPonderacionOperacionMensualMayo(BigDecimal ponderacionOperacionMensualMayo) {
        this.ponderacionOperacionMensualMayo = ponderacionOperacionMensualMayo;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_junio")
    public BigDecimal getPonderacionOperacionMensualJunio() {
        return ponderacionOperacionMensualJunio;
    }

    public void setPonderacionOperacionMensualJunio(BigDecimal ponderacionOperacionMensualJunio) {
        this.ponderacionOperacionMensualJunio = ponderacionOperacionMensualJunio;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_julio")
    public BigDecimal getPonderacionOperacionMensualJulio() {
        return ponderacionOperacionMensualJulio;
    }

    public void setPonderacionOperacionMensualJulio(BigDecimal ponderacionOperacionMensualJulio) {
        this.ponderacionOperacionMensualJulio = ponderacionOperacionMensualJulio;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_agosto")
    public BigDecimal getPonderacionOperacionMensualAgosto() {
        return ponderacionOperacionMensualAgosto;
    }

    public void setPonderacionOperacionMensualAgosto(BigDecimal ponderacionOperacionMensualAgosto) {
        this.ponderacionOperacionMensualAgosto = ponderacionOperacionMensualAgosto;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_septiembre")
    public BigDecimal getPonderacionOperacionMensualSeptiembre() {
        return ponderacionOperacionMensualSeptiembre;
    }

    public void setPonderacionOperacionMensualSeptiembre(BigDecimal ponderacionOperacionMensualSeptiembre) {
        this.ponderacionOperacionMensualSeptiembre = ponderacionOperacionMensualSeptiembre;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_octubre")
    public BigDecimal getPonderacionOperacionMensualOctubre() {
        return ponderacionOperacionMensualOctubre;
    }

    public void setPonderacionOperacionMensualOctubre(BigDecimal ponderacionOperacionMensualOctubre) {
        this.ponderacionOperacionMensualOctubre = ponderacionOperacionMensualOctubre;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_noviembre")
    public BigDecimal getPonderacionOperacionMensualNoviembre() {
        return ponderacionOperacionMensualNoviembre;
    }

    public void setPonderacionOperacionMensualNoviembre(BigDecimal ponderacionOperacionMensualNoviembre) {
        this.ponderacionOperacionMensualNoviembre = ponderacionOperacionMensualNoviembre;
    }

    @Basic
    @Column(name = "ponderacion_operacion_mensual_diciembre")
    public BigDecimal getPonderacionOperacionMensualDiciembre() {
        return ponderacionOperacionMensualDiciembre;
    }

    public void setPonderacionOperacionMensualDiciembre(BigDecimal ponderacionOperacionMensualDiciembre) {
        this.ponderacionOperacionMensualDiciembre = ponderacionOperacionMensualDiciembre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultaOperacionesConTotalesExcel that = (ConsultaOperacionesConTotalesExcel) o;
        return Objects.equals(poaGestion, that.poaGestion) && Objects.equals(poaDescripcion, that.poaDescripcion) && Objects.equals(areaIdArea, that.areaIdArea) && Objects.equals(areaCodigo, that.areaCodigo) && Objects.equals(areaSigla, that.areaSigla) && Objects.equals(areaDescripcion, that.areaDescripcion) && Objects.equals(areaSiglaGthc, that.areaSiglaGthc) && Objects.equals(operIdPoaArea, that.operIdPoaArea) && Objects.equals(operIdOperacion, that.operIdOperacion) && Objects.equals(operIdAccionCortoPlazo, that.operIdAccionCortoPlazo) && Objects.equals(operCodigoAccionCortoPlazo, that.operCodigoAccionCortoPlazo) && Objects.equals(operAccionCortoPlazo, that.operAccionCortoPlazo) && Objects.equals(operIdResponsableOperacion, that.operIdResponsableOperacion) && Objects.equals(operIdTipoIndicadorOperacion, that.operIdTipoIndicadorOperacion) && Objects.equals(operTipoIndicadorOperacion, that.operTipoIndicadorOperacion) && Objects.equals(operIdTipoValorOperacion, that.operIdTipoValorOperacion) && Objects.equals(operTipoValorOperacion, that.operTipoValorOperacion) && Objects.equals(operCodigoOperacion, that.operCodigoOperacion) && Objects.equals(operDescripcionOperacion, that.operDescripcionOperacion) && Objects.equals(operPonderacionOperacion, that.operPonderacionOperacion) && Objects.equals(operOrdenOperacion, that.operOrdenOperacion) && Objects.equals(programacionOperacionEnero, that.programacionOperacionEnero) && Objects.equals(programacionOperacionFebrero, that.programacionOperacionFebrero) && Objects.equals(programacionOperacionMarzo, that.programacionOperacionMarzo) && Objects.equals(programacionOperacionAbril, that.programacionOperacionAbril) && Objects.equals(programacionOperacionMayo, that.programacionOperacionMayo) && Objects.equals(programacionOperacionJunio, that.programacionOperacionJunio) && Objects.equals(programacionOperacionJulio, that.programacionOperacionJulio) && Objects.equals(programacionOperacionAgosto, that.programacionOperacionAgosto) && Objects.equals(programacionOperacionSeptiembre, that.programacionOperacionSeptiembre) && Objects.equals(programacionOperacionOctubre, that.programacionOperacionOctubre) && Objects.equals(programacionOperacionNoviembre, that.programacionOperacionNoviembre) && Objects.equals(programacionOperacionDiciembre, that.programacionOperacionDiciembre) && Objects.equals(gastoCorrienteOperacion, that.gastoCorrienteOperacion) && Objects.equals(inversionOperacion, that.inversionOperacion) && Objects.equals(ponderacionOperacionMensualEnero, that.ponderacionOperacionMensualEnero) && Objects.equals(ponderacionOperacionMensualFebrero, that.ponderacionOperacionMensualFebrero) && Objects.equals(ponderacionOperacionMensualMarzo, that.ponderacionOperacionMensualMarzo) && Objects.equals(ponderacionOperacionMensualAbril, that.ponderacionOperacionMensualAbril) && Objects.equals(ponderacionOperacionMensualMayo, that.ponderacionOperacionMensualMayo) && Objects.equals(ponderacionOperacionMensualJunio, that.ponderacionOperacionMensualJunio) && Objects.equals(ponderacionOperacionMensualJulio, that.ponderacionOperacionMensualJulio) && Objects.equals(ponderacionOperacionMensualAgosto, that.ponderacionOperacionMensualAgosto) && Objects.equals(ponderacionOperacionMensualSeptiembre, that.ponderacionOperacionMensualSeptiembre) && Objects.equals(ponderacionOperacionMensualOctubre, that.ponderacionOperacionMensualOctubre) && Objects.equals(ponderacionOperacionMensualNoviembre, that.ponderacionOperacionMensualNoviembre) && Objects.equals(ponderacionOperacionMensualDiciembre, that.ponderacionOperacionMensualDiciembre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(poaGestion, poaDescripcion, areaIdArea, areaCodigo, areaSigla, areaDescripcion, areaSiglaGthc, operIdPoaArea, operIdOperacion, operIdAccionCortoPlazo, operCodigoAccionCortoPlazo, operAccionCortoPlazo, operIdResponsableOperacion, operIdTipoIndicadorOperacion, operTipoIndicadorOperacion, operIdTipoValorOperacion, operTipoValorOperacion, operCodigoOperacion, operDescripcionOperacion, operPonderacionOperacion, operOrdenOperacion, programacionOperacionEnero, programacionOperacionFebrero, programacionOperacionMarzo, programacionOperacionAbril, programacionOperacionMayo, programacionOperacionJunio, programacionOperacionJulio, programacionOperacionAgosto, programacionOperacionSeptiembre, programacionOperacionOctubre, programacionOperacionNoviembre, programacionOperacionDiciembre, gastoCorrienteOperacion, inversionOperacion, ponderacionOperacionMensualEnero, ponderacionOperacionMensualFebrero, ponderacionOperacionMensualMarzo, ponderacionOperacionMensualAbril, ponderacionOperacionMensualMayo, ponderacionOperacionMensualJunio, ponderacionOperacionMensualJulio, ponderacionOperacionMensualAgosto, ponderacionOperacionMensualSeptiembre, ponderacionOperacionMensualOctubre, ponderacionOperacionMensualNoviembre, ponderacionOperacionMensualDiciembre);
    }
}
