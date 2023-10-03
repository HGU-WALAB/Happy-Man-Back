package edu.handong.happymanback.certificateType.domain;

import edu.handong.happymanback.certificateType.dto.CertificateTypeForm;
import edu.handong.happymanback.institution.domain.Institution;
import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="certificate_type")
public class CertificateType extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="certificate_type_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="institution_id")
    private Institution institution;

    @Column(length = 20, nullable = false)
    private String name;

    public static CertificateType create(Institution institution, CertificateTypeForm form){
        CertificateType certificateType = new CertificateType();
        certificateType.setInstitution(institution);
        certificateType.setName(form.getName());
        return certificateType;
    }

    public Long update(CertificateTypeForm form) {
        this.name = form.getName() != null ? form.getName() : name;
        return id;
    }
}
