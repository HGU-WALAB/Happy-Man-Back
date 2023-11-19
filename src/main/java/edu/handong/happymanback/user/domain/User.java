package edu.handong.happymanback.user.domain;

import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.utils.BaseTime;
import edu.handong.happymanback.utils.enums.Role;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name="user")
public class User extends BaseTime {

    @Id
    @Column(name="unique_id",length = 30)
    private String uniqueId;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String department;

    @Column(nullable = false, length = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User create(UserForm form) {
        User user = new User();
        user.setName(form.getName());
        user.setUniqueId(form.getUniqueId());
        user.setDepartment(form.getDepartment());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        user.setRole(Role.USER);
        return user;
    }

    public String update(UserForm form) {
        this.name = form.getName() != null ? form.getName() : name;
        this.department = form.getDepartment() != null ? form.getDepartment() : department;
        this.password = form.getPassword() != null ? form.getPassword() : password;
        this.email = form.getEmail() != null ? form.getEmail() : email;
        this.role = form.getRole() !=null ? form.getRole() : role;
        return this.uniqueId;
    }

}
