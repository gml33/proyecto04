package com.gml.gestionCursos.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import com.gml.gestionCursos.entities.Curso;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.FontFactoryImp;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletResponse;

public class CursoExporterPDF {


    private List<Curso> listaCursos;

    public CursoExporterPDF(List<Curso> listaCursos){
        this.listaCursos = listaCursos;
    }

    private void writeTableHeader(PdfPTable table){

        PdfPCell cell = new PdfPCell();

        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("ID"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Título"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Descripción"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Nivel"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Publicado"));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for(Curso curso:listaCursos){
            table.addCell(String.valueOf(curso.getId()));
            table.addCell(curso.getTitulo());
            table.addCell(curso.getDescripcion());
            table.addCell(String.valueOf(curso.getNivel()));
            table.addCell(String.valueOf(curso.isPublicado()));
        }
    }

    public void export(HttpServletResponse response) throws IOException{
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
        
        Paragraph p = new Paragraph("Lista de Cursos", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[]{1.3f, 3.5f, 3.5f, 2.0f, 1.5f});
        table.setSpacingBefore(10);
        
        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.close();
    }
}
