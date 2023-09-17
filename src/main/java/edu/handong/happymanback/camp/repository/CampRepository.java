package edu.handong.happymanback.camp.repository;
import edu.handong.happymanback.camp.domain.Camp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampRepository extends JpaRepository<Camp,Long>{

}
