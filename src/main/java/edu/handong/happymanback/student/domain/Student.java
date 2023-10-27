package edu.handong.happymanback.student.domain;

import edu.handong.happymanback.student.dto.StudentForm;
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
@Table(name="student")
public class Student extends BaseTime {

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

    public static Student create(StudentForm form) {
        Student Student = new Student();
        Student.setName(form.getName());
        Student.setStudentId(form.getStudentId());
        Student.setDepartment(form.getDepartment());
        Student.setPassword(form.getPassword());
        Student.setEmail(form.getEmail());
        return Student;
    }

    public String update(StudentForm form) {
        this.name = form.getName() != null ? form.getName() : name;
        this.department = form.getDepartment() != null ? form.getDepartment() : department;
        this.password = form.getPassword() != null ? form.getPassword() : password;
        this.email = form.getEmail() != null ? form.getEmail() : email;
        return this.studentId;
    }

}
