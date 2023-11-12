package edu.handong.happymanback.excel.util;
import edu.handong.happymanback.participant.domain.Participant;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {
    static String[] HEADERS = { "학번", "학부", "이름", "상장 호수" };
    static String SHEET = "Tutorials";
    public static ByteArrayInputStream participantToExcel(List<Participant> participantList) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            int rowIdx = 1;
            for (Participant participant : participantList) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(participant.getUser().getStudentId());
                row.createCell(1).setCellValue(participant.getUser().getDepartment());
                row.createCell(2).setCellValue(participant.getUser().getName());
                row.createCell(3).setCellValue(participant.getSerialNumber());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
