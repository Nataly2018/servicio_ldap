package gob.mefp.reportes.app.reportes.query;

import gob.ypfb.nova.app.reportes.dto.query.*;

import java.util.List;

public interface IConsultaGeneralPoaQuery {
    List<QueryTareasPorAcpPoaAreaOperacion> listarTareasPorAcpPoaAreaOperacion(Long pIdVersionPoa, String pIdPoa, String pIdAcp);
    List<QueryTareasPoraAcpPoaArea> listarTareasPorAcpPoaArea(Long pIdVersionPoa, String pIdPoa, String pIdAcp);
    List<QueryTareasPorAcp> listarTareasPorAcp(Long pIdVersionPoa, String pIdAcp);
    List<QueryTareasPorArea> listarTareasPorArea(Long pIdVersionPoa, String pIdPoaArea);
    List<QueryOperacionesPorAcpPoaArea> listarOperacionesPorAcpPoaArea(Long pIdVersionPoa, String pIdPoaArea, String pIdAcp);
    List<QueryOperacionesPorAcp> listarOperacionesPorAcp(Long pIdVersionPoa, String pIdAcp);
    List<QueryOperacionesPorArea> listarOperacionesPorArea(Long pIdVersionPoa, String pIdPoaArea);
    List<QueryTareasPorAreaYTipo> listarCantidadTareasPorArea(Long pIdVersionPoa, String pIdPoaArea);
    List<QueryPresupuestoPorTipoTareaArea> obtenerPresupuestoPorTipoTarea(Long pIdVersionPoa, String pIdPoaArea);
}
