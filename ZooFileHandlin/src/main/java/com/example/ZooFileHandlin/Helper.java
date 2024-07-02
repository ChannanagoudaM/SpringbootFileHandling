package com.example.ZooFileHandlin;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import org.apache.catalina.LifecycleState;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.print.attribute.standard.SheetCollate;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class Helper {

    public static String[] header={"Animal_Id",
            "Animal_Name",
            "TotalNoOfAnimals",
            "TypeOfAnimal"};

    public static String sheet_name="Z00SHEET";

    public static ByteArrayInputStream dateToExcel(List<Zoo>list)
    {
        Workbook workbook=new XSSFWorkbook();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
      try
      {

          Sheet sheet=workbook.createSheet(sheet_name);
          Row row =sheet.createRow(1);
          for(int i=0;i<header.length;i++)
          {
              Cell cell=row.createCell(i);
              cell.setCellValue(header[i]);
          }
          int rowindex=2 ;

          for(Zoo z:list)
          {
              Row indexrow=sheet.createRow(rowindex++);
              indexrow.createCell(0).setCellValue(z.getAnimal_id());
              indexrow.createCell(1).setCellValue(z.getAnimal_name());
              indexrow.createCell(2).setCellValue(z.getTotalNoOfAnimals());
              indexrow.createCell(3).setCellValue(z.getTypeofAnimal());
          }
//          for(int i=1;i<=100;i++)
//          {
//              sheet.autoSizeColumn(i);
//          }

          workbook.write(out);
          workbook.close();
          out.close();
      }
      catch (IOException i)
      {
          i.printStackTrace();
      }
        return new ByteArrayInputStream(out.toByteArray());
    }

    public static ByteArrayInputStream dataToPdf(List<Zoo> list)
    {
        Document document=new Document();
        ByteArrayOutputStream out=new ByteArrayOutputStream();
        try
        {
            PdfWriter.getInstance(document,out);
            document.open();
            Font fontHeader= FontFactory.getFont(FontFactory.TIMES_BOLD,22);
            Paragraph paragraph=new Paragraph("Zoo Details ",fontHeader);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);

            PdfPTable pdfTable=new PdfPTable(4);

            Stream.of("Animal_Id","Animal_Name","TotalNoOfAnimals","TypeOfAnimal")
                    .forEach(headerTitle->
                    {
                                PdfPCell header=new PdfPCell();
                                Font headfont=FontFactory.getFont(FontFactory.TIMES_BOLD);
                                header.setBackgroundColor(Color.CYAN);
                                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                                header.setBorderWidth(2);
                                header.setPhrase(new Phrase(headerTitle,headfont));
                                pdfTable.addCell(header);
                    });
                for(Zoo z:list)
                {
                    pdfTable.addCell(String.valueOf(z.getAnimal_id()));
                    pdfTable.addCell(z.getAnimal_name());
                    pdfTable.addCell(String.valueOf(z.getTotalNoOfAnimals()));
                    pdfTable.addCell(z.getTypeofAnimal());

                }
                document.add(pdfTable);
                document.close();
        }
        catch (DocumentException i)
        {
            i.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
