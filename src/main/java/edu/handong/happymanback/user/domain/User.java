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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 12,unique = true)
    private String personalId;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, length = 40)
    private String department;

    @Column(nullable = false, length = 10)
    private String role;

    public static User createUser(UserForm form) {
        User user = new User();
        user.setName(form.getName());
        user.setPersonalId(form.getPersonalId());
        user.setDepartment(form.getDepartment());
        user.setRole(form.getRole());
        user.setPassword(form.getPassword());
        return user;
    }

    public Long update(UserForm form) {
        this.name = form.getName() != null ? form.getName() : name;
        this.personalId = form.getPersonalId() != null ? form.getPersonalId() : personalId;
        this.department = form.getDepartment() != null ? form.getDepartment() : department;
        this.password = form.getPassword() != null ? form.getPassword() : password;
        this.role = form.getRole() != null ? form.getRole() : role;
        return id;
    }

}
