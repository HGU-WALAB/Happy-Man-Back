package edu.handong.happymanback.institution.service;

import edu.handong.happymanback.institution.domain.Institution;
import edu.handong.happymanback.institution.dto.InstitutionDto;
import edu.handong.happymanback.institution.dto.InstitutionForm;
import edu.handong.happymanback.institution.repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class InstitutionService {

    private final InstitutionRepository institutionRepository;

    @Autowired
    public InstitutionService(InstitutionRepository institutionRepository) {this.institutionRepository=institutionRepository;}

    public Long join(InstitutionForm form) {
        Institution institution = Institution.createInstitution(form);
        Institution saved = institutionRepository.save(institution);
        return saved.getId();
    }

    public Long modifyInstitution(Long id, InstitutionForm form) {
        Optional<Institution> institutionOptional = institutionRepository.findById(id);
        if (institutionOptional.isPresent()) {
            Institution institution = institutionOptional.get();
            return institution.update(form);
        } else {
            throw new IllegalArgumentException("Institution not found with id: " + id);
        }
    }

    public Long deleteInstitution(Long id) {
        institutionRepository.deleteById(id);
        return id;
    }

    public List<InstitutionDto> institutionList(){
        return institutionRepository.findAll()
                .stream()
                .map(InstitutionDto::new)
                .collect(Collectors.toList());
    }

    public InstitutionDto getInstitution(Long id){
        Optional<Institution> institutionOptional = institutionRepository.findById(id);
        if (institutionOptional.isPresent()) {
            return new InstitutionDto(institutionOptional.get());
        } else {
            throw new IllegalArgumentException("Institution not found with id: " + id);
        }
    }

}
