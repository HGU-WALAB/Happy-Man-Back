package edu.handong.happymanback.certificateType.service;

import edu.handong.happymanback.certificateType.domain.CertificateType;
import edu.handong.happymanback.certificateType.dto.CertificateTypeForm;
import edu.handong.happymanback.certificateType.repository.CertificateTypeRepository;
import edu.handong.happymanback.institution.domain.Institution;
import edu.handong.happymanback.institution.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CertificateTypeService {

    private final CertificateTypeRepository certificateTypeRepository;
    private final InstitutionService institutionService;

    @Autowired
    public CertificateTypeService(
            CertificateTypeRepository certificateTypeRepository,
            InstitutionService institutionService) {
        this.certificateTypeRepository = certificateTypeRepository;
        this.institutionService = institutionService;
    }

    public Long createCertificateType(CertificateTypeForm form){
        Institution institution = institutionService.getInstitution(form.getInstitutionId());
        CertificateType certificateType = CertificateType.create(institution,form);
        CertificateType saved= certificateTypeRepository.save(certificateType);
        return saved.getId();
    }

    public Long deleteCertificateType(Long id){
        certificateTypeRepository.deleteById(id);
        return id;
    }

    public Long modifyCertificateType(Long id, CertificateTypeForm form){
        Optional<CertificateType> certificateTypeOptional = certificateTypeRepository.findById(id);

        if (certificateTypeOptional.isPresent()) {
            CertificateType certificateType = certificateTypeOptional.get();
            return certificateType.update(form);
        } else {
            throw new IllegalArgumentException("CertificateType not found with id: " + id);
        }

    }

    public List<CertificateType> getCertificateTypeListByInstitutionId(Long institutionId) {
        Institution institution = institutionService.getInstitution(institutionId);
        return certificateTypeRepository.findByInstitution(institution);
    }

    public CertificateType getCertificateType(Long id){
        Optional<CertificateType> CertificateTypeOptional = certificateTypeRepository.findById(id);
        if (CertificateTypeOptional.isPresent()) {
            return CertificateTypeOptional.get();
        } else {
            throw new IllegalArgumentException("CertificateType not found with id: " + id);
        }
    }

}
