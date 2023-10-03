package edu.handong.happymanback.user.domain;

import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.utils.BaseTime;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="users")
public class User extends BaseTime {

    @Id
    @Column(name="personal_id")
    private String personalId;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    private Authority authority=Authority.USER;

    @Column(nullable = false, length = 40)
    private String department;

    @Column(nullable = false, length = 30)
    private String email;

    public static User create(UserForm form) {
        User user = new User();
        user.setName(form.getName());
        user.setPersonalId(form.getPersonalId());
        user.setDepartment(form.getDepartment());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        return user;
    }

    public String update(UserForm form) {
        this.name = form.getName() != null ? form.getName() : name;
        this.department = form.getDepartment() != null ? form.getDepartment() : department;
        this.password = form.getPassword() != null ? form.getPassword() : password;
        this.authority = form.getAuthority() != null ? form.getAuthority() : authority;
        this.email = form.getEmail() != null ? form.getEmail() : email;
        return this.personalId;
    }

}
