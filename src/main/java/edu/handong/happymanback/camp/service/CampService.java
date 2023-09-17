package edu.handong.happymanback.camp.service;

import edu.handong.happymanback.camp.domain.Camp;
import edu.handong.happymanback.camp.dto.CampForm;
import edu.handong.happymanback.camp.repository.CampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CampService {
    public final CampRepository campRepository;

    @Autowired
    public CampService(CampRepository campRepository) {
        this.campRepository = campRepository;
    }

    public List<Camp> campList(){
        return campRepository.findAll();
    }

    public Camp getCamp(Long id){
        Optional<Camp> campOptional=campRepository.findById(id);
        if(campOptional.isPresent()){
            return campOptional.get();
        }else{
            throw new IllegalArgumentException("Camp not found with id: " + id);
        }
    }

    public Long createCamp(CampForm form){
        Camp camp=Camp.create(form);
        Camp saved=campRepository.save(camp);
        return saved.getId();
    }

    public Long deleteCamp(Long id){
        campRepository.deleteById(id);
        return id;
    }

    public Long modifyCamp(Long id,CampForm form){
        Optional<Camp> campOptional = campRepository.findById(id);

        if (campOptional.isPresent()) {
            Camp camp = campOptional.get();
            return camp.update(form);
        } else {
            throw new IllegalArgumentException("Camp not found with id: " + id);
        }

    }
}
