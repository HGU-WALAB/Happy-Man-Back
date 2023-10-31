package edu.handong.happymanback.admin.domain;

import edu.handong.happymanback.admin.dto.AdminForm;
import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="admin")
public class Admin extends BaseTime {
    @Id
    @Column(name="admin_id",length = 20)
    private String id;

    @Column(length = 20)
    private String name;

    @Column(length = 30)
    private String password;

    public static Admin create(AdminForm form){
        Admin admin=new Admin();
        admin.setId(form.getId());
        admin.setName(form.getName());
        admin.setPassword(form.getPassword());
        return admin;
    }

    public String update(AdminForm form){
        this.id= form.getId();
        this.name=form.getName();
        this.password=form.getPassword();
        return id;
    }
}
