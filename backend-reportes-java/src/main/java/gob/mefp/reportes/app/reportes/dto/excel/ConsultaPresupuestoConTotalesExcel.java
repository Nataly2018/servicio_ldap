/**
 * Project: nova
 * Package: bo.gob.ypfb.nova
 * El archivo "ConsultaPresupuestoConTotales.java" fue creado para el proyecto nova
 * por Y.P.F.B. Corporación, todos los Derechos reservados.
 *
 * @author: Roger Diego Flores Condori
 * @email : cons.rflores@ypfb.gob.bo
 * @date: 22/9/2023 09:48
 * @copyright: YPFB
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.dto.excel;

import gob.ypfb.nova.utils.excel.ExcelYpfbAnnotation;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.math.BigDecimal;
import java.util.Objects;


public class ConsultaPresupuestoConTotalesExcel {
    private Long idPoa;
    private Integer version;
    private String codigoVersion;
    private Boolean esActivo;
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
    private Long presIdPresupuesto;
    private Long presIdCatProgramatica;
    @ExcelYpfbAnnotation(posicionColumna = 17)
    private String presCategoriaProgramatica;
    private Long presIdUnidadEjecutora;
    @ExcelYpfbAnnotation(posicionColumna = 16)
    private String presCodigoUnidadEjecutora;

    private String presUnidadEjecutora;
    private Long presIdDireccionAdmin;
    private String presCodigoDireccionAdmin;
    @ExcelYpfbAnnotation(posicionColumna = 15)
    private String presDireccionAdmin;
    private Long presIdPartidaPresupuestaria;
    @ExcelYpfbAnnotation(posicionColumna = 18)
    private String presCodigoPartidaPresupuestaria;
    @ExcelYpfbAnnotation(posicionColumna = 19)
    private String presPartidaPresupuestaria;
    private Long presIdFuente;
    @ExcelYpfbAnnotation(posicionColumna = 20)
    private String presCodigoFuenteFinanciamiento;
    private String presFuenteFinanciamiento;
    private Long presIdOrganismoFinanciador;
    private String presCodigoOrganismoFinanciador;
    private String presOrganismoFinanciador;
    private Long presIdEntidadTransferencia;
    @ExcelYpfbAnnotation(posicionColumna = 21)
    private Integer presCodigoEntidadTransferencia;
    private String presEntidadTransferencia;
    private Long presIdTipo;
    @ExcelYpfbAnnotation(posicionColumna = 14)
    private String presTipoPresupuesto;
    @ExcelYpfbAnnotation(posicionColumna = 23)
    private String presUnidadMedida;
    @ExcelYpfbAnnotation(posicionColumna = 22)
    private String presDetalleGasto;

    private String presNivelSalarialConsultor;
    @ExcelYpfbAnnotation(posicionColumna = 25)
    private BigDecimal presDuracionMesesConsultoria;
    @ExcelYpfbAnnotation(posicionColumna = 26, cantidadDecimalesColumna = 2)
    private BigDecimal presPrecioUnitario;
    @ExcelYpfbAnnotation(posicionColumna = 27)
    private Integer presCantidadRequerida;
    @ExcelYpfbAnnotation(posicionColumna = 24)
    private BigDecimal presSalarioMensualConsultor;
    @ExcelYpfbAnnotation(posicionColumna = 28, cantidadDecimalesColumna = 2)
    private BigDecimal presTotalGestion;
    private String presGastoGestionesFuturas;
    @ExcelYpfbAnnotation(posicionColumna = 29)
    private BigDecimal presTotalGastoGestionesFuturas;
    @ExcelYpfbAnnotation(posicionColumna = 30, cantidadDecimalesColumna = 2)
    private BigDecimal presTotalGestiones;
    private String consObjetivoConsultoria;
    private String consActividadesConsultoria;
    private String consPerfilConsultor;
    private String consResultadosConsultoria;
    private String consAreasValidadorasConsultoria;
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
    private String tarTipoIndicadorTarea;
    private Long tarIdTipoFormatoTarea;
    private String tarTipoFormato;
    @ExcelYpfbAnnotation(posicionColumna = 10)
    private String tarCodigoTarea;
    private Integer tarOrdenTarea;
    @ExcelYpfbAnnotation(posicionColumna = 11)
    private String tarDescripcionTarea;
    private BigDecimal tarPonderacionPorcentualTarea;
    private String tarUnidadMedidaTarea;
    private String tarMedioVerificacion;
    private Boolean tarRequiereOtraArea;
    private BigDecimal tarAvanceEsperado;
    private BigDecimal tarLineaBase;
    @ExcelYpfbAnnotation(posicionColumna = 13)
    private String tarAreaResponsable;

    @Basic
    @Column(name = "id_poa")
    public Long getIdPoa() {
        return idPoa;
    }

    public void setIdPoa(Long idPoa) {
        this.idPoa = idPoa;
    }

    @Basic
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "codigo_version")
    public String getCodigoVersion() {
        return codigoVersion;
    }

    public void setCodigoVersion(String codigoVersion) {
        this.codigoVersion = codigoVersion;
    }

    @Basic
    @Column(name = "es_activo")
    public Boolean getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }

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
    @Column(name = "pres_id_presupuesto")
    public Long getPresIdPresupuesto() {
        return presIdPresupuesto;
    }

    public void setPresIdPresupuesto(Long presIdPresupuesto) {
        this.presIdPresupuesto = presIdPresupuesto;
    }

    @Basic
    @Column(name = "pres_id_cat_programatica")
    public Long getPresIdCatProgramatica() {
        return presIdCatProgramatica;
    }

    public void setPresIdCatProgramatica(Long presIdCatProgramatica) {
        this.presIdCatProgramatica = presIdCatProgramatica;
    }

    @Basic
    @Column(name = "pres_categoria_programatica")
    public String getPresCategoriaProgramatica() {
        return presCategoriaProgramatica;
    }

    public void setPresCategoriaProgramatica(String presCategoriaProgramatica) {
        this.presCategoriaProgramatica = presCategoriaProgramatica;
    }

    @Basic
    @Column(name = "pres_id_unidad_ejecutora")
    public Long getPresIdUnidadEjecutora() {
        return presIdUnidadEjecutora;
    }

    public void setPresIdUnidadEjecutora(Long presIdUnidadEjecutora) {
        this.presIdUnidadEjecutora = presIdUnidadEjecutora;
    }

    @Basic
    @Column(name = "pres_codigo_unidad_ejecutora")
    public String getPresCodigoUnidadEjecutora() {
        return presCodigoUnidadEjecutora;
    }

    public void setPresCodigoUnidadEjecutora(String presCodigoUnidadEjecutora) {
        this.presCodigoUnidadEjecutora = presCodigoUnidadEjecutora;
    }

    @Basic
    @Column(name = "pres_unidad_ejecutora")
    public String getPresUnidadEjecutora() {
        return presUnidadEjecutora;
    }

    public void setPresUnidadEjecutora(String presUnidadEjecutora) {
        this.presUnidadEjecutora = presUnidadEjecutora;
    }

    @Basic
    @Column(name = "pres_id_direccion_admin")
    public Long getPresIdDireccionAdmin() {
        return presIdDireccionAdmin;
    }

    public void setPresIdDireccionAdmin(Long presIdDireccionAdmin) {
        this.presIdDireccionAdmin = presIdDireccionAdmin;
    }

    @Basic
    @Column(name = "pres_codigo_direccion_admin")
    public String getPresCodigoDireccionAdmin() {
        return presCodigoDireccionAdmin;
    }

    public void setPresCodigoDireccionAdmin(String presCodigoDireccionAdmin) {
        this.presCodigoDireccionAdmin = presCodigoDireccionAdmin;
    }

    @Basic
    @Column(name = "pres_direccion_admin")
    public String getPresDireccionAdmin() {
        return presDireccionAdmin;
    }

    public void setPresDireccionAdmin(String presDireccionAdmin) {
        this.presDireccionAdmin = presDireccionAdmin;
    }

    @Basic
    @Column(name = "pres_id_partida_presupuestaria")
    public Long getPresIdPartidaPresupuestaria() {
        return presIdPartidaPresupuestaria;
    }

    public void setPresIdPartidaPresupuestaria(Long presIdPartidaPresupuestaria) {
        this.presIdPartidaPresupuestaria = presIdPartidaPresupuestaria;
    }

    @Basic
    @Column(name = "pres_codigo_partida_presupuestaria")
    public String getPresCodigoPartidaPresupuestaria() {
        return presCodigoPartidaPresupuestaria;
    }

    public void setPresCodigoPartidaPresupuestaria(String presCodigoPartidaPresupuestaria) {
        this.presCodigoPartidaPresupuestaria = presCodigoPartidaPresupuestaria;
    }

    @Basic
    @Column(name = "pres_partida_presupuestaria")
    public String getPresPartidaPresupuestaria() {
        return presPartidaPresupuestaria;
    }

    public void setPresPartidaPresupuestaria(String presPartidaPresupuestaria) {
        this.presPartidaPresupuestaria = presPartidaPresupuestaria;
    }

    @Basic
    @Column(name = "pres_id_fuente")
    public Long getPresIdFuente() {
        return presIdFuente;
    }

    public void setPresIdFuente(Long presIdFuente) {
        this.presIdFuente = presIdFuente;
    }

    @Basic
    @Column(name = "pres_codigo_fuente_financiamiento")
    public String getPresCodigoFuenteFinanciamiento() {
        return presCodigoFuenteFinanciamiento;
    }

    public void setPresCodigoFuenteFinanciamiento(String presCodigoFuenteFinanciamiento) {
        this.presCodigoFuenteFinanciamiento = presCodigoFuenteFinanciamiento;
    }

    @Basic
    @Column(name = "pres_fuente_financiamiento")
    public String getPresFuenteFinanciamiento() {
        return presFuenteFinanciamiento;
    }

    public void setPresFuenteFinanciamiento(String presFuenteFinanciamiento) {
        this.presFuenteFinanciamiento = presFuenteFinanciamiento;
    }

    @Basic
    @Column(name = "pres_id_organismo_financiador")
    public Long getPresIdOrganismoFinanciador() {
        return presIdOrganismoFinanciador;
    }

    public void setPresIdOrganismoFinanciador(Long presIdOrganismoFinanciador) {
        this.presIdOrganismoFinanciador = presIdOrganismoFinanciador;
    }

    @Basic
    @Column(name = "pres_codigo_organismo_financiador")
    public String getPresCodigoOrganismoFinanciador() {
        return presCodigoOrganismoFinanciador;
    }

    public void setPresCodigoOrganismoFinanciador(String presCodigoOrganismoFinanciador) {
        this.presCodigoOrganismoFinanciador = presCodigoOrganismoFinanciador;
    }

    @Basic
    @Column(name = "pres_organismo_financiador")
    public String getPresOrganismoFinanciador() {
        return presOrganismoFinanciador;
    }

    public void setPresOrganismoFinanciador(String presOrganismoFinanciador) {
        this.presOrganismoFinanciador = presOrganismoFinanciador;
    }

    @Basic
    @Column(name = "pres_id_entidad_transferencia")
    public Long getPresIdEntidadTransferencia() {
        return presIdEntidadTransferencia;
    }

    public void setPresIdEntidadTransferencia(Long presIdEntidadTransferencia) {
        this.presIdEntidadTransferencia = presIdEntidadTransferencia;
    }

    @Basic
    @Column(name = "pres_codigo_entidad_transferencia")
    public Integer getPresCodigoEntidadTransferencia() {
        return presCodigoEntidadTransferencia;
    }

    public void setPresCodigoEntidadTransferencia(Integer presCodigoEntidadTransferencia) {
        this.presCodigoEntidadTransferencia = presCodigoEntidadTransferencia;
    }

    @Basic
    @Column(name = "pres_entidad_transferencia")
    public String getPresEntidadTransferencia() {
        return presEntidadTransferencia;
    }

    public void setPresEntidadTransferencia(String presEntidadTransferencia) {
        this.presEntidadTransferencia = presEntidadTransferencia;
    }

    @Basic
    @Column(name = "pres_id_tipo")
    public Long getPresIdTipo() {
        return presIdTipo;
    }

    public void setPresIdTipo(Long presIdTipo) {
        this.presIdTipo = presIdTipo;
    }

    @Basic
    @Column(name = "pres_tipo_presupuesto")
    public String getPresTipoPresupuesto() {
        return presTipoPresupuesto;
    }

    public void setPresTipoPresupuesto(String presTipoPresupuesto) {
        this.presTipoPresupuesto = presTipoPresupuesto;
    }

    @Basic
    @Column(name = "pres_unidad_medida")
    public String getPresUnidadMedida() {
        return presUnidadMedida;
    }

    public void setPresUnidadMedida(String presUnidadMedida) {
        this.presUnidadMedida = presUnidadMedida;
    }

    @Basic
    @Column(name = "pres_detalle_gasto")
    public String getPresDetalleGasto() {
        return presDetalleGasto;
    }

    public void setPresDetalleGasto(String presDetalleGasto) {
        this.presDetalleGasto = presDetalleGasto;
    }

    @Basic
    @Column(name = "pres_nivel_salarial_consultor")
    public String getPresNivelSalarialConsultor() {
        return presNivelSalarialConsultor;
    }

    public void setPresNivelSalarialConsultor(String presNivelSalarialConsultor) {
        this.presNivelSalarialConsultor = presNivelSalarialConsultor;
    }

    @Basic
    @Column(name = "pres_duracion_meses_consultoria")
    public BigDecimal getPresDuracionMesesConsultoria() {
        return presDuracionMesesConsultoria;
    }

    public void setPresDuracionMesesConsultoria(BigDecimal presDuracionMesesConsultoria) {
        this.presDuracionMesesConsultoria = presDuracionMesesConsultoria;
    }

    @Basic
    @Column(name = "pres_precio_unitario")
    public BigDecimal getPresPrecioUnitario() {
        return presPrecioUnitario;
    }

    public void setPresPrecioUnitario(BigDecimal presPrecioUnitario) {
        this.presPrecioUnitario = presPrecioUnitario;
    }

    @Basic
    @Column(name = "pres_cantidad_requerida")
    public Integer getPresCantidadRequerida() {
        return presCantidadRequerida;
    }

    public void setPresCantidadRequerida(Integer presCantidadRequerida) {
        this.presCantidadRequerida = presCantidadRequerida;
    }

    @Basic
    @Column(name = "pres_salario_mensual_consultor")
    public BigDecimal getPresSalarioMensualConsultor() {
        return presSalarioMensualConsultor;
    }

    public void setPresSalarioMensualConsultor(BigDecimal presSalarioMensualConsultor) {
        this.presSalarioMensualConsultor = presSalarioMensualConsultor;
    }

    @Basic
    @Column(name = "pres_total_gestion")
    public BigDecimal getPresTotalGestion() {
        return presTotalGestion;
    }

    public void setPresTotalGestion(BigDecimal presTotalGestion) {
        this.presTotalGestion = presTotalGestion;
    }

    @Basic
    @Column(name = "pres_gasto_gestiones_futuras")
    public String getPresGastoGestionesFuturas() {
        return presGastoGestionesFuturas;
    }

    public void setPresGastoGestionesFuturas(String presGastoGestionesFuturas) {
        this.presGastoGestionesFuturas = presGastoGestionesFuturas;
    }

    @Basic
    @Column(name = "pres_total_gasto_gestiones_futuras")
    public BigDecimal getPresTotalGastoGestionesFuturas() {
        return presTotalGastoGestionesFuturas;
    }

    public void setPresTotalGastoGestionesFuturas(BigDecimal presTotalGastoGestionesFuturas) {
        this.presTotalGastoGestionesFuturas = presTotalGastoGestionesFuturas;
    }

    @Basic
    @Column(name = "pres_total_gestiones")
    public BigDecimal getPresTotalGestiones() {
        return presTotalGestiones;
    }

    public void setPresTotalGestiones(BigDecimal presTotalGestiones) {
        this.presTotalGestiones = presTotalGestiones;
    }

    @Basic
    @Column(name = "cons_objetivo_consultoria")
    public String getConsObjetivoConsultoria() {
        return consObjetivoConsultoria;
    }

    public void setConsObjetivoConsultoria(String consObjetivoConsultoria) {
        this.consObjetivoConsultoria = consObjetivoConsultoria;
    }

    @Basic
    @Column(name = "cons_actividades_consultoria")
    public String getConsActividadesConsultoria() {
        return consActividadesConsultoria;
    }

    public void setConsActividadesConsultoria(String consActividadesConsultoria) {
        this.consActividadesConsultoria = consActividadesConsultoria;
    }

    @Basic
    @Column(name = "cons_perfil_consultor")
    public String getConsPerfilConsultor() {
        return consPerfilConsultor;
    }

    public void setConsPerfilConsultor(String consPerfilConsultor) {
        this.consPerfilConsultor = consPerfilConsultor;
    }

    @Basic
    @Column(name = "cons_resultados_consultoria")
    public String getConsResultadosConsultoria() {
        return consResultadosConsultoria;
    }

    public void setConsResultadosConsultoria(String consResultadosConsultoria) {
        this.consResultadosConsultoria = consResultadosConsultoria;
    }

    @Basic
    @Column(name = "cons_areas_validadoras_consultoria")
    public String getConsAreasValidadorasConsultoria() {
        return consAreasValidadorasConsultoria;
    }

    public void setConsAreasValidadorasConsultoria(String consAreasValidadorasConsultoria) {
        this.consAreasValidadorasConsultoria = consAreasValidadorasConsultoria;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsultaPresupuestoConTotalesExcel that = (ConsultaPresupuestoConTotalesExcel) o;
        return Objects.equals(idPoa, that.idPoa) && Objects.equals(version, that.version) && Objects.equals(codigoVersion, that.codigoVersion) && Objects.equals(esActivo, that.esActivo) && Objects.equals(poaGestion, that.poaGestion) && Objects.equals(poaDescripcion, that.poaDescripcion) && Objects.equals(areaIdArea, that.areaIdArea) && Objects.equals(areaCodigo, that.areaCodigo) && Objects.equals(areaSigla, that.areaSigla) && Objects.equals(areaDescripcion, that.areaDescripcion) && Objects.equals(areaSiglaGthc, that.areaSiglaGthc) && Objects.equals(presIdPresupuesto, that.presIdPresupuesto) && Objects.equals(presIdCatProgramatica, that.presIdCatProgramatica) && Objects.equals(presCategoriaProgramatica, that.presCategoriaProgramatica) && Objects.equals(presIdUnidadEjecutora, that.presIdUnidadEjecutora) && Objects.equals(presCodigoUnidadEjecutora, that.presCodigoUnidadEjecutora) && Objects.equals(presUnidadEjecutora, that.presUnidadEjecutora) && Objects.equals(presIdDireccionAdmin, that.presIdDireccionAdmin) && Objects.equals(presCodigoDireccionAdmin, that.presCodigoDireccionAdmin) && Objects.equals(presDireccionAdmin, that.presDireccionAdmin) && Objects.equals(presIdPartidaPresupuestaria, that.presIdPartidaPresupuestaria) && Objects.equals(presCodigoPartidaPresupuestaria, that.presCodigoPartidaPresupuestaria) && Objects.equals(presPartidaPresupuestaria, that.presPartidaPresupuestaria) && Objects.equals(presIdFuente, that.presIdFuente) && Objects.equals(presCodigoFuenteFinanciamiento, that.presCodigoFuenteFinanciamiento) && Objects.equals(presFuenteFinanciamiento, that.presFuenteFinanciamiento) && Objects.equals(presIdOrganismoFinanciador, that.presIdOrganismoFinanciador) && Objects.equals(presCodigoOrganismoFinanciador, that.presCodigoOrganismoFinanciador) && Objects.equals(presOrganismoFinanciador, that.presOrganismoFinanciador) && Objects.equals(presIdEntidadTransferencia, that.presIdEntidadTransferencia) && Objects.equals(presCodigoEntidadTransferencia, that.presCodigoEntidadTransferencia) && Objects.equals(presEntidadTransferencia, that.presEntidadTransferencia) && Objects.equals(presIdTipo, that.presIdTipo) && Objects.equals(presTipoPresupuesto, that.presTipoPresupuesto) && Objects.equals(presUnidadMedida, that.presUnidadMedida) && Objects.equals(presDetalleGasto, that.presDetalleGasto) && Objects.equals(presNivelSalarialConsultor, that.presNivelSalarialConsultor) && Objects.equals(presDuracionMesesConsultoria, that.presDuracionMesesConsultoria) && Objects.equals(presPrecioUnitario, that.presPrecioUnitario) && Objects.equals(presCantidadRequerida, that.presCantidadRequerida) && Objects.equals(presSalarioMensualConsultor, that.presSalarioMensualConsultor) && Objects.equals(presTotalGestion, that.presTotalGestion) && Objects.equals(presGastoGestionesFuturas, that.presGastoGestionesFuturas) && Objects.equals(presTotalGastoGestionesFuturas, that.presTotalGastoGestionesFuturas) && Objects.equals(presTotalGestiones, that.presTotalGestiones) && Objects.equals(consObjetivoConsultoria, that.consObjetivoConsultoria) && Objects.equals(consActividadesConsultoria, that.consActividadesConsultoria) && Objects.equals(consPerfilConsultor, that.consPerfilConsultor) && Objects.equals(consResultadosConsultoria, that.consResultadosConsultoria) && Objects.equals(consAreasValidadorasConsultoria, that.consAreasValidadorasConsultoria) && Objects.equals(operIdPoaArea, that.operIdPoaArea) && Objects.equals(operIdOperacion, that.operIdOperacion) && Objects.equals(operIdAccionCortoPlazo, that.operIdAccionCortoPlazo) && Objects.equals(operCodigoAccionCortoPlazo, that.operCodigoAccionCortoPlazo) && Objects.equals(operAccionCortoPlazo, that.operAccionCortoPlazo) && Objects.equals(operIdResponsableOperacion, that.operIdResponsableOperacion) && Objects.equals(operIdTipoIndicadorOperacion, that.operIdTipoIndicadorOperacion) && Objects.equals(operTipoIndicadorOperacion, that.operTipoIndicadorOperacion) && Objects.equals(operIdTipoValorOperacion, that.operIdTipoValorOperacion) && Objects.equals(operTipoValorOperacion, that.operTipoValorOperacion) && Objects.equals(operCodigoOperacion, that.operCodigoOperacion) && Objects.equals(operDescripcionOperacion, that.operDescripcionOperacion) && Objects.equals(operPonderacionOperacion, that.operPonderacionOperacion) && Objects.equals(operOrdenOperacion, that.operOrdenOperacion) && Objects.equals(tarIdTarea, that.tarIdTarea) && Objects.equals(tarIdResponsableTarea, that.tarIdResponsableTarea) && Objects.equals(tarIdTipoTarea, that.tarIdTipoTarea) && Objects.equals(tarTipoTarea, that.tarTipoTarea) && Objects.equals(tarIdTipoIndicadorTarea, that.tarIdTipoIndicadorTarea) && Objects.equals(tarTipoIndicadorTarea, that.tarTipoIndicadorTarea) && Objects.equals(tarIdTipoFormatoTarea, that.tarIdTipoFormatoTarea) && Objects.equals(tarTipoFormato, that.tarTipoFormato) && Objects.equals(tarCodigoTarea, that.tarCodigoTarea) && Objects.equals(tarOrdenTarea, that.tarOrdenTarea) && Objects.equals(tarDescripcionTarea, that.tarDescripcionTarea) && Objects.equals(tarPonderacionPorcentualTarea, that.tarPonderacionPorcentualTarea) && Objects.equals(tarUnidadMedidaTarea, that.tarUnidadMedidaTarea) && Objects.equals(tarMedioVerificacion, that.tarMedioVerificacion) && Objects.equals(tarRequiereOtraArea, that.tarRequiereOtraArea) && Objects.equals(tarAvanceEsperado, that.tarAvanceEsperado) && Objects.equals(tarLineaBase, that.tarLineaBase) && Objects.equals(tarAreaResponsable, that.tarAreaResponsable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPoa, version, codigoVersion, esActivo, poaGestion, poaDescripcion, areaIdArea, areaCodigo, areaSigla, areaDescripcion, areaSiglaGthc, presIdPresupuesto, presIdCatProgramatica, presCategoriaProgramatica, presIdUnidadEjecutora, presCodigoUnidadEjecutora, presUnidadEjecutora, presIdDireccionAdmin, presCodigoDireccionAdmin, presDireccionAdmin, presIdPartidaPresupuestaria, presCodigoPartidaPresupuestaria, presPartidaPresupuestaria, presIdFuente, presCodigoFuenteFinanciamiento, presFuenteFinanciamiento, presIdOrganismoFinanciador, presCodigoOrganismoFinanciador, presOrganismoFinanciador, presIdEntidadTransferencia, presCodigoEntidadTransferencia, presEntidadTransferencia, presIdTipo, presTipoPresupuesto, presUnidadMedida, presDetalleGasto, presNivelSalarialConsultor, presDuracionMesesConsultoria, presPrecioUnitario, presCantidadRequerida, presSalarioMensualConsultor, presTotalGestion, presGastoGestionesFuturas, presTotalGastoGestionesFuturas, presTotalGestiones, consObjetivoConsultoria, consActividadesConsultoria, consPerfilConsultor, consResultadosConsultoria, consAreasValidadorasConsultoria, operIdPoaArea, operIdOperacion, operIdAccionCortoPlazo, operCodigoAccionCortoPlazo, operAccionCortoPlazo, operIdResponsableOperacion, operIdTipoIndicadorOperacion, operTipoIndicadorOperacion, operIdTipoValorOperacion, operTipoValorOperacion, operCodigoOperacion, operDescripcionOperacion, operPonderacionOperacion, operOrdenOperacion, tarIdTarea, tarIdResponsableTarea, tarIdTipoTarea, tarTipoTarea, tarIdTipoIndicadorTarea, tarTipoIndicadorTarea, tarIdTipoFormatoTarea, tarTipoFormato, tarCodigoTarea, tarOrdenTarea, tarDescripcionTarea, tarPonderacionPorcentualTarea, tarUnidadMedidaTarea, tarMedioVerificacion, tarRequiereOtraArea, tarAvanceEsperado, tarLineaBase, tarAreaResponsable);
    }
}
