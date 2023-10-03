package edu.handong.happymanback.institution.repository;

import edu.handong.happymanback.institution.domain.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository <Institution,Long> {

}
