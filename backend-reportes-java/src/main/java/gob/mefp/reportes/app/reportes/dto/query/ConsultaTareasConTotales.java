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
package gob.mefp.reportes.app.reportes.dto.query;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;


public class ConsultaTareasConTotales {
    private Integer poaGestion;
    private String poaDescripcion;
    private Long areaIdArea;
    private Integer areaCodigo;
    private String areaSigla;
    private String areaDescripcion;
    private String areaSiglaGthc;
    private Long operIdPoaArea;
    private Long operIdOperacion;
    private Long operIdAccionCortoPlazo;
    private Integer operCodigoAccionCortoPlazo;
    private String operAccionCortoPlazo;
    private Long operIdResponsableOperacion;
    private Long operIdTipoIndicadorOperacion;
    private String operTipoIndicadorOperacion;
    private Long operIdTipoValorOperacion;
    private String operTipoValorOperacion;
    private String operCodigoOperacion;
    private String operDescripcionOperacion;
    private BigDecimal operPonderacionOperacion;
    private Integer operOrdenOperacion;
    private Long tarIdTarea;
    private Long tarIdResponsableTarea;
    private Long tarIdTipoTarea;
    private String tarTipoTarea;
    private Long tarIdTipoIndicadorTarea;
    private String tarTipoIndicadorTarea;
    private Long tarIdTipoFormatoTarea;
    private String tarTipoFormato;
    private String tarCodigoTarea;
    private Integer tarOrdenTarea;
    private String tarDescripcionTarea;
    private BigDecimal tarPonderacionPorcentualTarea;
    private String tarUnidadMedidaTarea;
    private String tarMedioVerificacion;
    private Boolean tarRequiereOtraArea;
    private BigDecimal tarAvanceEsperado;
    private BigDecimal tarLineaBase;
    private String tarAreaResponsable;
    private BigDecimal programacionTareaEnero;
    private BigDecimal programacionTareaFebrero;
    private BigDecimal programacionTareaMarzo;
    private BigDecimal programacionTareaAbril;
    private BigDecimal programacionTareaMayo;
    private BigDecimal programacionTareaJunio;
    private BigDecimal programacionTareaJulio;
    private BigDecimal programacionTareaAgosto;
    private BigDecimal programacionTareaSeptiembre;
    private BigDecimal programacionTareaOctubre;
    private BigDecimal programacionTareaNoviembre;
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
    private BigDecimal gastoCorrienteTarea;
    private BigDecimal inversionTarea;
    private BigDecimal metaanual;

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
    @Column(name = "tar_id_tarea")
    public Long getTarIdTarea() {
        return tarIdTarea;
    }

    public void setTarIdTarea(Long tarIdTarea) {
        this.tarIdTarea = tarIdTarea;
    }

    @Basic
    @Column(name = "tar_id_responsable_tarea")
    public Long getTarIdResponsableTarea() {
        return tarIdResponsableTarea;
    }

    public void setTarIdResponsableTarea(Long tarIdResponsableTarea) {
        this.tarIdResponsableTarea = tarIdResponsableTarea;
    }

    @Basic
    @Column(name = "tar_id_tipo_tarea")
    public Long getTarIdTipoTarea() {
        return tarIdTipoTarea;
    }

    public void setTarIdTipoTarea(Long tarIdTipoTarea) {
        this.tarIdTipoTarea = tarIdTipoTarea;
    }

    @Basic
    @Column(name = "tar_tipo_tarea")
    public String getTarTipoTarea() {
        return tarTipoTarea;
    }

    public void setTarTipoTarea(String tarTipoTarea) {
        this.tarTipoTarea = tarTipoTarea;
    }

    @Basic
    @Column(name = "tar_id_tipo_indicador_tarea")
    public Long getTarIdTipoIndicadorTarea() {
        return tarIdTipoIndicadorTarea;
    }

    public void setTarIdTipoIndicadorTarea(Long tarIdTipoIndicadorTarea) {
        this.tarIdTipoIndicadorTarea = tarIdTipoIndicadorTarea;
    }

    @Basic
    @Column(name = "tar_tipo_indicador_tarea")
    public String getTarTipoIndicadorTarea() {
        return tarTipoIndicadorTarea;
    }

    public void setTarTipoIndicadorTarea(String tarTipoIndicadorTarea) {
        this.tarTipoIndicadorTarea = tarTipoIndicadorTarea;
    }

    @Basic
    @Column(name = "tar_id_tipo_formato_tarea")
    public Long getTarIdTipoFormatoTarea() {
        return tarIdTipoFormatoTarea;
    }

    public void setTarIdTipoFormatoTarea(Long tarIdTipoFormatoTarea) {
        this.tarIdTipoFormatoTarea = tarIdTipoFormatoTarea;
    }

    @Basic
    @Column(name = "tar_tipo_formato")
    public String getTarTipoFormato() {
        return tarTipoFormato;
    }

    public void setTarTipoFormato(String tarTipoFormato) {
        this.tarTipoFormato = tarTipoFormato;
    }

    @Basic
    @Column(name = "tar_codigo_tarea")
    public String getTarCodigoTarea() {
        return tarCodigoTarea;
    }

    public void setTarCodigoTarea(String tarCodigoTarea) {
        this.tarCodigoTarea = tarCodigoTarea;
    }

    @Basic
    @Column(name = "tar_orden_tarea")
    public Integer getTarOrdenTarea() {
        return tarOrdenTarea;
    }

    public void setTarOrdenTarea(Integer tarOrdenTarea) {
        this.tarOrdenTarea = tarOrdenTarea;
    }

    @Basic
    @Column(name = "tar_descripcion_tarea")
    public String getTarDescripcionTarea() {
        return tarDescripcionTarea;
    }

    public void setTarDescripcionTarea(String tarDescripcionTarea) {
        this.tarDescripcionTarea = tarDescripcionTarea;
    }

    @Basic
    @Column(name = "tar_ponderacion_porcentual_tarea")
    public BigDecimal getTarPonderacionPorcentualTarea() {
        return tarPonderacionPorcentualTarea;
    }

    public void setTarPonderacionPorcentualTarea(BigDecimal tarPonderacionPorcentualTarea) {
        this.tarPonderacionPorcentualTarea = tarPonderacionPorcentualTarea;
    }

    @Basic
    @Column(name = "tar_unidad_medida_tarea")
    public String getTarUnidadMedidaTarea() {
        return tarUnidadMedidaTarea;
    }

    public void setTarUnidadMedidaTarea(String tarUnidadMedidaTarea) {
        this.tarUnidadMedidaTarea = tarUnidadMedidaTarea;
    }

    @Basic
    @Column(name = "tar_medio_verificacion")
    public String getTarMedioVerificacion() {
        return tarMedioVerificacion;
    }

    public void setTarMedioVerificacion(String tarMedioVerificacion) {
        this.tarMedioVerificacion = tarMedioVerificacion;
    }

    @Basic
    @Column(name = "tar_requiere_otra_area")
    public Boolean getTarRequiereOtraArea() {
        return tarRequiereOtraArea;
    }

    public void setTarRequiereOtraArea(Boolean tarRequiereOtraArea) {
        this.tarRequiereOtraArea = tarRequiereOtraArea;
    }

    @Basic
    @Column(name = "tar_avance_esperado")
    public BigDecimal getTarAvanceEsperado() {
        return tarAvanceEsperado;
    }

    public void setTarAvanceEsperado(BigDecimal tarAvanceEsperado) {
        this.tarAvanceEsperado = tarAvanceEsperado;
    }

    @Basic
    @Column(name = "tar_linea_base")
    public BigDecimal getTarLineaBase() {
        return tarLineaBase;
    }

    public void setTarLineaBase(BigDecimal tarLineaBase) {
        this.tarLineaBase = tarLineaBase;
    }

    @Basic
    @Column(name = "tar_area_responsable")
    public String getTarAreaResponsable() {
        return tarAreaResponsable;
    }

    public void setTarAreaResponsable(String tarAreaResponsable) {
        this.tarAreaResponsable = tarAreaResponsable;
    }

    @Basic
    @Column(name = "programacion_tarea_enero")
    public BigDecimal getProgramacionTareaEnero() {
        return programacionTareaEnero;
    }

    public void setProgramacionTareaEnero(BigDecimal programacionTareaEnero) {
        this.programacionTareaEnero = programacionTareaEnero;
    }

    @Basic
    @Column(name = "programacion_tarea_febrero")
    public BigDecimal getProgramacionTareaFebrero() {
        return programacionTareaFebrero;
    }

    public void setProgramacionTareaFebrero(BigDecimal programacionTareaFebrero) {
        this.programacionTareaFebrero = programacionTareaFebrero;
    }

    @Basic
    @Column(name = "programacion_tarea_marzo")
    public BigDecimal getProgramacionTareaMarzo() {
        return programacionTareaMarzo;
    }

    public void setProgramacionTareaMarzo(BigDecimal programacionTareaMarzo) {
        this.programacionTareaMarzo = programacionTareaMarzo;
    }

    @Basic
    @Column(name = "programacion_tarea_abril")
    public BigDecimal getProgramacionTareaAbril() {
        return programacionTareaAbril;
    }

    public void setProgramacionTareaAbril(BigDecimal programacionTareaAbril) {
        this.programacionTareaAbril = programacionTareaAbril;
    }

    @Basic
    @Column(name = "programacion_tarea_mayo")
    public BigDecimal getProgramacionTareaMayo() {
        return programacionTareaMayo;
    }

    public void setProgramacionTareaMayo(BigDecimal programacionTareaMayo) {
        this.programacionTareaMayo = programacionTareaMayo;
    }

    @Basic
    @Column(name = "programacion_tarea_junio")
    public BigDecimal getProgramacionTareaJunio() {
        return programacionTareaJunio;
    }

    public void setProgramacionTareaJunio(BigDecimal programacionTareaJunio) {
        this.programacionTareaJunio = programacionTareaJunio;
    }

    @Basic
    @Column(name = "programacion_tarea_julio")
    public BigDecimal getProgramacionTareaJulio() {
        return programacionTareaJulio;
    }

    public void setProgramacionTareaJulio(BigDecimal programacionTareaJulio) {
        this.programacionTareaJulio = programacionTareaJulio;
    }

    @Basic
    @Column(name = "programacion_tarea_agosto")
    public BigDecimal getProgramacionTareaAgosto() {
        return programacionTareaAgosto;
    }

    public void setProgramacionTareaAgosto(BigDecimal programacionTareaAgosto) {
        this.programacionTareaAgosto = programacionTareaAgosto;
    }

    @Basic
    @Column(name = "programacion_tarea_septiembre")
    public BigDecimal getProgramacionTareaSeptiembre() {
        return programacionTareaSeptiembre;
    }

    public void setProgramacionTareaSeptiembre(BigDecimal programacionTareaSeptiembre) {
        this.programacionTareaSeptiembre = programacionTareaSeptiembre;
    }

    @Basic
    @Column(name = "programacion_tarea_octubre")
    public BigDecimal getProgramacionTareaOctubre() {
        return programacionTareaOctubre;
    }

    public void setProgramacionTareaOctubre(BigDecimal programacionTareaOctubre) {
        this.programacionTareaOctubre = programacionTareaOctubre;
    }

    @Basic
    @Column(name = "programacion_tarea_noviembre")
    public BigDecimal getProgramacionTareaNoviembre() {
        return programacionTareaNoviembre;
    }

    public void setProgramacionTareaNoviembre(BigDecimal programacionTareaNoviembre) {
        this.programacionTareaNoviembre = programacionTareaNoviembre;
    }

    @Basic
    @Column(name = "programacion_tarea_diciembre")
    public BigDecimal getProgramacionTareaDiciembre() {
        return programacionTareaDiciembre;
    }

    public void setProgramacionTareaDiciembre(BigDecimal programacionTareaDiciembre) {
        this.programacionTareaDiciembre = programacionTareaDiciembre;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_enero")
    public BigDecimal getPonderacionTareaMensualEnero() {
        return ponderacionTareaMensualEnero;
    }

    public void setPonderacionTareaMensualEnero(BigDecimal ponderacionTareaMensualEnero) {
        this.ponderacionTareaMensualEnero = ponderacionTareaMensualEnero;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_febrero")
    public BigDecimal getPonderacionTareaMensualFebrero() {
        return ponderacionTareaMensualFebrero;
    }

    public void setPonderacionTareaMensualFebrero(BigDecimal ponderacionTareaMensualFebrero) {
        this.ponderacionTareaMensualFebrero = ponderacionTareaMensualFebrero;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_marzo")
    public BigDecimal getPonderacionTareaMensualMarzo() {
        return ponderacionTareaMensualMarzo;
    }

    public void setPonderacionTareaMensualMarzo(BigDecimal ponderacionTareaMensualMarzo) {
        this.ponderacionTareaMensualMarzo = ponderacionTareaMensualMarzo;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_abril")
    public BigDecimal getPonderacionTareaMensualAbril() {
        return ponderacionTareaMensualAbril;
    }

    public void setPonderacionTareaMensualAbril(BigDecimal ponderacionTareaMensualAbril) {
        this.ponderacionTareaMensualAbril = ponderacionTareaMensualAbril;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_mayo")
    public BigDecimal getPonderacionTareaMensualMayo() {
        return ponderacionTareaMensualMayo;
    }

    public void setPonderacionTareaMensualMayo(BigDecimal ponderacionTareaMensualMayo) {
        this.ponderacionTareaMensualMayo = ponderacionTareaMensualMayo;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_junio")
    public BigDecimal getPonderacionTareaMensualJunio() {
        return ponderacionTareaMensualJunio;
    }

    public void setPonderacionTareaMensualJunio(BigDecimal ponderacionTareaMensualJunio) {
        this.ponderacionTareaMensualJunio = ponderacionTareaMensualJunio;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_julio")
    public BigDecimal getPonderacionTareaMensualJulio() {
        return ponderacionTareaMensualJulio;
    }

    public void setPonderacionTareaMensualJulio(BigDecimal ponderacionTareaMensualJulio) {
        this.ponderacionTareaMensualJulio = ponderacionTareaMensualJulio;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_agosto")
    public BigDecimal getPonderacionTareaMensualAgosto() {
        return ponderacionTareaMensualAgosto;
    }

    public void setPonderacionTareaMensualAgosto(BigDecimal ponderacionTareaMensualAgosto) {
        this.ponderacionTareaMensualAgosto = ponderacionTareaMensualAgosto;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_septiembre")
    public BigDecimal getPonderacionTareaMensualSeptiembre() {
        return ponderacionTareaMensualSeptiembre;
    }

    public void setPonderacionTareaMensualSeptiembre(BigDecimal ponderacionTareaMensualSeptiembre) {
        this.ponderacionTareaMensualSeptiembre = ponderacionTareaMensualSeptiembre;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_octubre")
    public BigDecimal getPonderacionTareaMensualOctubre() {
        return ponderacionTareaMensualOctubre;
    }

    public void setPonderacionTareaMensualOctubre(BigDecimal ponderacionTareaMensualOctubre) {
        this.ponderacionTareaMensualOctubre = ponderacionTareaMensualOctubre;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_noviembre")
    public BigDecimal getPonderacionTareaMensualNoviembre() {
        return ponderacionTareaMensualNoviembre;
    }

    public void setPonderacionTareaMensualNoviembre(BigDecimal ponderacionTareaMensualNoviembre) {
        this.ponderacionTareaMensualNoviembre = ponderacionTareaMensualNoviembre;
    }

    @Basic
    @Column(name = "ponderacion_tarea_mensual_diciembre")
    public BigDecimal getPonderacionTareaMensualDiciembre() {
        return ponderacionTareaMensualDiciembre;
    }

    public void setPonderacionTareaMensualDiciembre(BigDecimal ponderacionTareaMensualDiciembre) {
        this.ponderacionTareaMensualDiciembre = ponderacionTareaMensualDiciembre;
    }

    @Basic
    @Column(name = "gasto_corriente_tarea")
    public BigDecimal getGastoCorrienteTarea() {
        return gastoCorrienteTarea;
    }

    public void setGastoCorrienteTarea(BigDecimal gastoCorrienteTarea) {
        this.gastoCorrienteTarea = gastoCorrienteTarea;
    }

    @Basic
    @Column(name = "inversion_tarea")
    public BigDecimal getInversionTarea() {
        return inversionTarea;
    }

    public void setInversionTarea(BigDecimal inversionTarea) {
        this.inversionTarea = inversionTarea;
    }

    @Basic
    @Column(name = "metaanual")
    public BigDecimal getMetaanual() {
        return metaanual;
    }

    public void setMetaanual(BigDecimal metaanual) {
        this.metaanual = metaanual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultaTareasConTotales that = (ConsultaTareasConTotales) o;
        return Objects.equals(poaGestion, that.poaGestion) && Objects.equals(poaDescripcion, that.poaDescripcion) && Objects.equals(areaIdArea, that.areaIdArea) && Objects.equals(areaCodigo, that.areaCodigo) && Objects.equals(areaSigla, that.areaSigla) && Objects.equals(areaDescripcion, that.areaDescripcion) && Objects.equals(areaSiglaGthc, that.areaSiglaGthc) && Objects.equals(operIdPoaArea, that.operIdPoaArea) && Objects.equals(operIdOperacion, that.operIdOperacion) && Objects.equals(operIdAccionCortoPlazo, that.operIdAccionCortoPlazo) && Objects.equals(operCodigoAccionCortoPlazo, that.operCodigoAccionCortoPlazo) && Objects.equals(operAccionCortoPlazo, that.operAccionCortoPlazo) && Objects.equals(operIdResponsableOperacion, that.operIdResponsableOperacion) && Objects.equals(operIdTipoIndicadorOperacion, that.operIdTipoIndicadorOperacion) && Objects.equals(operTipoIndicadorOperacion, that.operTipoIndicadorOperacion) && Objects.equals(operIdTipoValorOperacion, that.operIdTipoValorOperacion) && Objects.equals(operTipoValorOperacion, that.operTipoValorOperacion) && Objects.equals(operCodigoOperacion, that.operCodigoOperacion) && Objects.equals(operDescripcionOperacion, that.operDescripcionOperacion) && Objects.equals(operPonderacionOperacion, that.operPonderacionOperacion) && Objects.equals(operOrdenOperacion, that.operOrdenOperacion) && Objects.equals(tarIdTarea, that.tarIdTarea) && Objects.equals(tarIdResponsableTarea, that.tarIdResponsableTarea) && Objects.equals(tarIdTipoTarea, that.tarIdTipoTarea) && Objects.equals(tarTipoTarea, that.tarTipoTarea) && Objects.equals(tarIdTipoIndicadorTarea, that.tarIdTipoIndicadorTarea) && Objects.equals(tarTipoIndicadorTarea, that.tarTipoIndicadorTarea) && Objects.equals(tarIdTipoFormatoTarea, that.tarIdTipoFormatoTarea) && Objects.equals(tarTipoFormato, that.tarTipoFormato) && Objects.equals(tarCodigoTarea, that.tarCodigoTarea) && Objects.equals(tarOrdenTarea, that.tarOrdenTarea) && Objects.equals(tarDescripcionTarea, that.tarDescripcionTarea) && Objects.equals(tarPonderacionPorcentualTarea, that.tarPonderacionPorcentualTarea) && Objects.equals(tarUnidadMedidaTarea, that.tarUnidadMedidaTarea) && Objects.equals(tarMedioVerificacion, that.tarMedioVerificacion) && Objects.equals(tarRequiereOtraArea, that.tarRequiereOtraArea) && Objects.equals(tarAvanceEsperado, that.tarAvanceEsperado) && Objects.equals(tarLineaBase, that.tarLineaBase) && Objects.equals(tarAreaResponsable, that.tarAreaResponsable) && Objects.equals(programacionTareaEnero, that.programacionTareaEnero) && Objects.equals(programacionTareaFebrero, that.programacionTareaFebrero) && Objects.equals(programacionTareaMarzo, that.programacionTareaMarzo) && Objects.equals(programacionTareaAbril, that.programacionTareaAbril) && Objects.equals(programacionTareaMayo, that.programacionTareaMayo) && Objects.equals(programacionTareaJunio, that.programacionTareaJunio) && Objects.equals(programacionTareaJulio, that.programacionTareaJulio) && Objects.equals(programacionTareaAgosto, that.programacionTareaAgosto) && Objects.equals(programacionTareaSeptiembre, that.programacionTareaSeptiembre) && Objects.equals(programacionTareaOctubre, that.programacionTareaOctubre) && Objects.equals(programacionTareaNoviembre, that.programacionTareaNoviembre) && Objects.equals(programacionTareaDiciembre, that.programacionTareaDiciembre) && Objects.equals(ponderacionTareaMensualEnero, that.ponderacionTareaMensualEnero) && Objects.equals(ponderacionTareaMensualFebrero, that.ponderacionTareaMensualFebrero) && Objects.equals(ponderacionTareaMensualMarzo, that.ponderacionTareaMensualMarzo) && Objects.equals(ponderacionTareaMensualAbril, that.ponderacionTareaMensualAbril) && Objects.equals(ponderacionTareaMensualMayo, that.ponderacionTareaMensualMayo) && Objects.equals(ponderacionTareaMensualJunio, that.ponderacionTareaMensualJunio) && Objects.equals(ponderacionTareaMensualJulio, that.ponderacionTareaMensualJulio) && Objects.equals(ponderacionTareaMensualAgosto, that.ponderacionTareaMensualAgosto) && Objects.equals(ponderacionTareaMensualSeptiembre, that.ponderacionTareaMensualSeptiembre) && Objects.equals(ponderacionTareaMensualOctubre, that.ponderacionTareaMensualOctubre) && Objects.equals(ponderacionTareaMensualNoviembre, that.ponderacionTareaMensualNoviembre) && Objects.equals(ponderacionTareaMensualDiciembre, that.ponderacionTareaMensualDiciembre) && Objects.equals(gastoCorrienteTarea, that.gastoCorrienteTarea) && Objects.equals(inversionTarea, that.inversionTarea) && Objects.equals(metaanual, that.metaanual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(poaGestion, poaDescripcion, areaIdArea, areaCodigo, areaSigla, areaDescripcion, areaSiglaGthc, operIdPoaArea, operIdOperacion, operIdAccionCortoPlazo, operCodigoAccionCortoPlazo, operAccionCortoPlazo, operIdResponsableOperacion, operIdTipoIndicadorOperacion, operTipoIndicadorOperacion, operIdTipoValorOperacion, operTipoValorOperacion, operCodigoOperacion, operDescripcionOperacion, operPonderacionOperacion, operOrdenOperacion, tarIdTarea, tarIdResponsableTarea, tarIdTipoTarea, tarTipoTarea, tarIdTipoIndicadorTarea, tarTipoIndicadorTarea, tarIdTipoFormatoTarea, tarTipoFormato, tarCodigoTarea, tarOrdenTarea, tarDescripcionTarea, tarPonderacionPorcentualTarea, tarUnidadMedidaTarea, tarMedioVerificacion, tarRequiereOtraArea, tarAvanceEsperado, tarLineaBase, tarAreaResponsable, programacionTareaEnero, programacionTareaFebrero, programacionTareaMarzo, programacionTareaAbril, programacionTareaMayo, programacionTareaJunio, programacionTareaJulio, programacionTareaAgosto, programacionTareaSeptiembre, programacionTareaOctubre, programacionTareaNoviembre, programacionTareaDiciembre, ponderacionTareaMensualEnero, ponderacionTareaMensualFebrero, ponderacionTareaMensualMarzo, ponderacionTareaMensualAbril, ponderacionTareaMensualMayo, ponderacionTareaMensualJunio, ponderacionTareaMensualJulio, ponderacionTareaMensualAgosto, ponderacionTareaMensualSeptiembre, ponderacionTareaMensualOctubre, ponderacionTareaMensualNoviembre, ponderacionTareaMensualDiciembre, gastoCorrienteTarea, inversionTarea, metaanual);
    }
}
