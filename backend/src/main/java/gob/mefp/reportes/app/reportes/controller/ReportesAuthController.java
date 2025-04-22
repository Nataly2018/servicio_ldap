package gob.mefp.reportes.app.reportes.controller;
import gob.mefp.reportes.app.reportes.dto.response.ReporteGenericoJsonDtoReq;
import gob.mefp.reportes.app.reportes.service.IReporteAuthService;
import gob.mefp.reportes.app.reportes.service.IReporteGenericoService;
import gob.mefp.reportes.utils.excel.ExcelConstantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("reportes/api")
@CrossOrigin(origins = "*",
        methods = {RequestMethod.GET, RequestMethod.POST,
                RequestMethod.PUT, RequestMethod.DELETE})
//@Slf4j
public class ReportesAuthController {
    @Autowired
    IReporteGenericoService vIReporteGenericoService;
    @Autowired
    IReporteAuthService vIReporteAuthService;

    @PostMapping(value = "/dominio_reporte_v3")
    public void exportarReporte(HttpServletResponse response, @RequestBody ReporteGenericoJsonDtoReq pReporteGenericoJsonDtoReq) {
        vIReporteAuthService.exportarReporte(response, ExcelConstantes.TipoArchivoExportacion.PDF, pReporteGenericoJsonDtoReq
        );
    }

}
