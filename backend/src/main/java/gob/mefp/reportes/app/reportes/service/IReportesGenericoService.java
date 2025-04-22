/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/

package gob.mefp.reportes.app.reportes.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IReportesGenericoService {
    void generarPdf(JasperPrint jasperPrint, HttpServletResponse pHttpServletResponse, String nombreArchivo) throws IOException, JRException;

    void generarPdf(List<JasperPrint> jasperPrints, HttpServletResponse pHttpServletResponse, String nombreArchivo) throws IOException, JRException;

    void generarExcel(JasperPrint jasperPrint, HttpServletResponse pHttpServletResponse, String nombreArchivo) throws IOException, JRException;

    void generarExcel(List<JasperPrint> jasperPrintList, HttpServletResponse pHttpServletResponse, String nombreArchivo) throws IOException, JRException;

    void descargarVariosExcel(List<JasperPrint> jasperPrintList, HttpServletResponse response, String nombreArchivo) throws IOException, JRException;
}
