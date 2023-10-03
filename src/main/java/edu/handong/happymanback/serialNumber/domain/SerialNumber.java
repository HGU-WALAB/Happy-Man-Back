package edu.handong.happymanback.serialNumber.domain;

import edu.handong.happymanback.certificateType.domain.CertificateType;
import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="serial_number")
public class SerialNumber extends BaseTime {

    @Id
    @Column(name="serial_number_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialNumberId;

    @ManyToOne
    @JoinColumn(name="certificate_type_id")
    private CertificateType certificateType;

    private String year;

    private int curSerialNumber=1;
}
