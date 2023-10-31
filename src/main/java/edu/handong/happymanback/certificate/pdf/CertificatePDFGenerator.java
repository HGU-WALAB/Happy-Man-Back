package edu.handong.happymanback.certificate.pdf;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import edu.handong.happymanback.certificate.dto.CertificateDto;

public class CertificatePDFGenerator {

    public static ByteArrayInputStream customerPDFReport(List<CertificateDto> certificateDtoList) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();

            // 배경 이미지 설정
            Image backgroundImage = Image.getInstance("src/main/resources/static/img/background.png");
            backgroundImage.setAbsolutePosition(0, 0);
            backgroundImage.scaleToFit(document.getPageSize().getWidth(), document.getPageSize().getHeight());
            PdfContentByte under = writer.getDirectContentUnder();

            //직인 이미지 설정
            Image stampImage = Image.getInstance("src/main/resources/static/img/stamp.png");
            stampImage.setAbsolutePosition(document.getPageSize().getWidth() - 160, 80);
            stampImage.scaleToFit(100,100);

            //날짜 형식
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");

            // 폰트, 폰트 사이즈 설정

            BaseFont bfKorea = BaseFont.createFont("src/main/resources/static/fonts/goongseo.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font smallFont = new Font(bfKorea,17);
            Font bigFont = new Font(bfKorea, 45);
            Font midFont = new Font(bfKorea, 25);
            for (CertificateDto certificateDto:certificateDtoList
            ) {
                under.addImage(stampImage);
                under.addImage(backgroundImage);

                document.add(new Paragraph("\n\n"));
                //호수
                Paragraph certificateNum = new Paragraph(certificateDto.getCertificateNum(), smallFont);
                certificateNum.setIndentationLeft(20);
                document.add(certificateNum);

                document.add(new Paragraph("\n"));

                // 수료증 제목 추가
                Paragraph subject = new Paragraph("수 료 증", bigFont);
                subject.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(subject);

                document.add(new Paragraph("\n\n\n\n"));

                //학부 추가
                Paragraph department = new Paragraph("학     부 : " + certificateDto.getDepartment(), smallFont);
                department.setIndentationLeft(250);
                document.add(department);

                //학번 추가
                Paragraph studentId = new Paragraph("학     번 : " + certificateDto.getStudentId(), smallFont);
                studentId.setIndentationLeft(250);
                document.add(studentId);

                //이름 추가
                Paragraph name = new Paragraph("성     명 : " + certificateDto.getName(), smallFont);
                name.setIndentationLeft(250);
                document.add(name);

                document.add(new Paragraph("\n\n\n\n"));

                //본문 추가
                Paragraph content = new Paragraph("위 학생은 "+certificateDto.getInstitution()+ "에서 진행한 \""+ certificateDto.getEvent()+"\"에 참가하여 소정의 과정을 이수하였기에 이 증서를 수여합니다.", smallFont);
                content.setAlignment(Paragraph.ALIGN_LEFT);
                content.setIndentationLeft(45);
                content.setIndentationRight(45);
                document.add(content);

                //기간 추가
                Paragraph date = new Paragraph("■이수 기간: " + certificateDto.getStartDate().format(formatter) + " ~ "+ certificateDto.getEndDate().format(formatter), smallFont);
                date.setIndentationLeft(45);
                document.add(date);

                //캠프명 추가
                Paragraph camp = new Paragraph("■이수 과정: " + certificateDto.getEvent(), smallFont);
                camp.setIndentationLeft(45);
                document.add(camp);

                document.add(new Paragraph("\n\n\n\n\n"));

                //오늘 날짜 추가
                Paragraph nowDate = new Paragraph(certificateDto.getCertificateIssueDate().format(formatter) ,smallFont);
                nowDate.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(nowDate);

                document.add(new Paragraph("\n\n"));

                //책임자 추가
                Paragraph responsiblePerson = new Paragraph(certificateDto.getResponsiblePerson(),midFont);
                responsiblePerson.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(responsiblePerson);

                //새 페이지 추가
                document.newPage();
            }
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
