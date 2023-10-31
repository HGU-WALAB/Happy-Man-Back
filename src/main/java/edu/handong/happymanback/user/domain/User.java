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
@Table(name="user")
public class User extends BaseTime {

    @Id
    @Column(name="student_id",length = 30)
    private String studentId;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String department;

    @Column(nullable = false, length = 30)
    private String email;

    public static User create(UserForm form) {
        User User = new User();
        User.setName(form.getName());
        User.setStudentId(form.getStudentId());
        User.setDepartment(form.getDepartment());
        User.setPassword(form.getPassword());
        User.setEmail(form.getEmail());
        return User;
    }

    public String update(UserForm form) {
        this.name = form.getName() != null ? form.getName() : name;
        this.department = form.getDepartment() != null ? form.getDepartment() : department;
        this.password = form.getPassword() != null ? form.getPassword() : password;
        this.email = form.getEmail() != null ? form.getEmail() : email;
        return this.studentId;
    }

}
