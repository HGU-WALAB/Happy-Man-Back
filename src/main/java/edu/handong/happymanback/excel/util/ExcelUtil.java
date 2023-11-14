package edu.handong.happymanback.excel.util;
import edu.handong.happymanback.participant.domain.Participant;
import edu.handong.happymanback.participant.dto.ParticipantForm;
import edu.handong.happymanback.participant.service.ParticipantService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class ExcelUtil {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = { "학번", "학부", "이름", "상장 호수" };
    static String SHEET = "참가자 명단";

    private final ParticipantService participantService;

    public ExcelUtil(ParticipantService participantService){
        this.participantService=participantService;
    }

    public static boolean hasExcelFormat(MultipartFile file) {

        return TYPE.equals(file.getContentType());
    }
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
    public List<Long> excelToParticipant(Long id,InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Long> ids=new ArrayList<>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                ParticipantForm form=new ParticipantForm();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    if (currentCell != null) {
                        switch (cellIdx) {
                            case 0->{
                                DataFormatter dataFormatter = new DataFormatter();
                                String value = dataFormatter.formatCellValue(currentCell);
                                form.setStudentId(value);
                            }
                            case 3 -> form.setSerialNumber(currentCell.getStringCellValue());
                            default -> {
                            }
                        }
                    }
                    cellIdx++;
                }
                form.setEventId(id);
                ids.add(participantService.createParticipant(form));
            }
            workbook.close();

            return ids;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
