package edu.handong.happymanback.excel.service;

import edu.handong.happymanback.excel.util.ExcelUtil;
import edu.handong.happymanback.participant.domain.Participant;
import edu.handong.happymanback.participant.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
@Transactional
public class ExcelService {
    private final ParticipantRepository participantRepository;

    @Autowired
    public ExcelService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public ByteArrayInputStream excelDownload(Long eventId){
        List<Participant> participantList=participantRepository.findByEventId(eventId);
        return ExcelUtil.participantToExcel(participantList);
    }
}
