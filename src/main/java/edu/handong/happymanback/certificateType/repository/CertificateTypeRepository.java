package edu.handong.happymanback.certificateType.repository;

import edu.handong.happymanback.certificateType.domain.CertificateType;
import edu.handong.happymanback.institution.domain.Institution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CertificateTypeRepository extends JpaRepository<CertificateType,Long> {
    List<CertificateType> findByInstitution(Institution institution);
}
