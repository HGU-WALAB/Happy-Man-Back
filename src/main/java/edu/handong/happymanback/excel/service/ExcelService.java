package edu.handong.happymanback.excel.service;

import edu.handong.happymanback.excel.util.ExcelUtil;
import edu.handong.happymanback.participant.domain.Participant;
import edu.handong.happymanback.participant.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ExcelService {
    private final ParticipantRepository participantRepository;
    private final ExcelUtil excelUtil;
    @Autowired
    public ExcelService(ParticipantRepository participantRepository,ExcelUtil excelUtil) {
        this.participantRepository = participantRepository;
        this.excelUtil=excelUtil;
    }

    public ByteArrayInputStream excelDownload(Long eventId){
        List<Participant> participantList=participantRepository.findByEventId(eventId);
        return ExcelUtil.participantToExcel(participantList);
    }

    public List<Long> excelUpload(Long id,MultipartFile file) {
        try{
            participantRepository.deleteByEventId(id);
            return excelUtil.excelToParticipant(id,file.getInputStream());
        } catch (IOException e){
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }
}
