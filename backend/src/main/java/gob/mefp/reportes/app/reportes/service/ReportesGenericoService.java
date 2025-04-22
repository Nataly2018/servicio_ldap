/**
 * Project: reportes
 * Package: bo.gob.mefp.reportes
 * @author: Nataly Wendy Mamani Mamani
 * @version: 1.0
 **/
package gob.mefp.reportes.app.reportes.service;


import gob.mefp.reportes.exception.IntegridadMefpException;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Service
@Slf4j
public class ReportesGenericoService implements IReportesGenericoService {

    @Override
    public void generarPdf(JasperPrint jasperPrint, HttpServletResponse pHttpServletResponse, String nombreArchivo) throws IOException, JRException {
        nombreArchivo = nombreArchivo+".pdf";
        pHttpServletResponse.setContentType("application/pdf");
        pHttpServletResponse.setHeader("x-suggested-filename",
                java.net.URLEncoder.encode(nombreArchivo, "UTF-8"));
        pHttpServletResponse.setHeader("Content-disposition", "inline; filename=" + nombreArchivo);
        final OutputStream outStream = pHttpServletResponse.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    @Override
    public void generarPdf(List<JasperPrint> jasperPrints, HttpServletResponse pHttpServletResponse, String nombreArchivo) throws IOException, JRException {
        nombreArchivo = nombreArchivo + ".pdf";
        pHttpServletResponse.setContentType("application/pdf");
        pHttpServletResponse.setHeader("x-suggested-filename",
                java.net.URLEncoder.encode(nombreArchivo, "UTF-8"));
        pHttpServletResponse.setHeader("Content-disposition", "inline; filename=" + nombreArchivo);

        // Crea un JRPdfExporter
        JRPdfExporter exporter = new JRPdfExporter();

        // Configura la entrada y la salida del exportador
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));

        // Crea un OutputStream a partir del HttpServletResponse
        OutputStream outStream = pHttpServletResponse.getOutputStream();

        // Configura la salida del exportador para el OutputStream
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outStream));

        // Exporta los JasperPrints a un solo PDF
        try {
            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
            // Maneja cualquier excepción que pueda ocurrir durante la exportación
        } finally {
            // Cierra el OutputStream después de la exportación
            outStream.close();
        }
    }

    @Override
    public void generarExcel(JasperPrint jasperPrint, HttpServletResponse pHttpServletResponse, String nombreArchivo) throws IOException, JRException {
        nombreArchivo = nombreArchivo+".xlsx";

        pHttpServletResponse.setContentType("application/octet-stream");
        pHttpServletResponse.setHeader("x-suggested-filename",
                java.net.URLEncoder.encode(nombreArchivo, "UTF-8"));
        pHttpServletResponse.setHeader("Content-disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(nombreArchivo, "UTF-8"));
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        //configuration.setOnePagePerSheet(true);
        //configuration.setIgnoreGraphics(false);
        configuration.setWrapText(true);
        final OutputStream outStream = pHttpServletResponse.getOutputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Exporter exporter = new JRXlsxExporter();

        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        byteArrayOutputStream.writeTo(outStream);

    }
    @Override
    public void generarExcel(List<JasperPrint> jasperPrintList, HttpServletResponse pHttpServletResponse, String nombreArchivo) throws IOException, JRException {
        nombreArchivo = nombreArchivo + ".xlsx";

        pHttpServletResponse.setContentType("application/octet-stream");
        pHttpServletResponse.setHeader("x-suggested-filename",
                java.net.URLEncoder.encode(nombreArchivo, "UTF-8"));
        pHttpServletResponse.setHeader("Content-disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(nombreArchivo, "UTF-8"));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setWrapText(true);
        Exporter exporter = new JRXlsxExporter();

        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
        exporter.setConfiguration(configuration);

        for (JasperPrint jasperPrint : jasperPrintList) {
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.exportReport();
        }

        final OutputStream outStream = pHttpServletResponse.getOutputStream();
        byteArrayOutputStream.writeTo(outStream);
    }
    @Override
    public void descargarVariosExcel(List<JasperPrint> jasperPrintList, HttpServletResponse response, String nombreArchivo) throws IOException, JRException {
        nombreArchivo = nombreArchivo+".xlsx";
        response.setContentType("application/octet-stream");
        response.setHeader("x-suggested-filename",
                java.net.URLEncoder.encode(nombreArchivo, "UTF-8"));
        response.setHeader("Content-disposition",
                "attachment;filename=" + java.net.URLEncoder.encode(nombreArchivo, "UTF-8"));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try (OutputStream outputStream = response.getOutputStream()) {
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteArrayOutputStream));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            exporter.setConfiguration(configuration);
            exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
            exporter.exportReport();
            byteArrayOutputStream.writeTo(outputStream);
        } catch (Exception e) {
           throw new IntegridadMefpException("no se pudo exportear");
        }
    }


}
