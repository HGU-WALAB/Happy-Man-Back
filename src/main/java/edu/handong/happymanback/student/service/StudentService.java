package edu.handong.happymanback.student.service;

import edu.handong.happymanback.student.domain.Student;
import edu.handong.happymanback.student.dto.StudentDto;
import edu.handong.happymanback.student.dto.StudentForm;
import edu.handong.happymanback.student.exception.StudentIdDuplicateException;
import edu.handong.happymanback.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    private final StudentRepository StudentRepository;

    @Autowired
    public StudentService(StudentRepository StudentRepository) {
        this.StudentRepository = StudentRepository;
    }

    public String join(StudentForm form) {
        StudentRepository.findById(form.getStudentId())
                .ifPresent(m->{
                    throw new StudentIdDuplicateException();
                });
        Student student = Student.create(form);
        Student saved = StudentRepository.save(student);
        return saved.getStudentId();
    }

    public String modifyStudent(String studentId, StudentForm form) {
        Optional<Student> StudentOptional = StudentRepository.findById(studentId);
        if (StudentOptional.isPresent()) {
            Student Student = StudentOptional.get();
            return Student.update(form);
        } else {
            throw new IllegalArgumentException("Student not found with id: " + studentId);
        }
    }

    public String deleteStudent(String studentId) {
        StudentRepository.deleteById(studentId);
        return studentId;
    }

    public StudentDto StudentList(){
        List<Student> StudentList=StudentRepository.findAll();
        List<StudentDto.Info> list=new ArrayList<>();
        for(Student Student:StudentList){
            list.add(StudentDto.Info.builder()
                    .studentId(Student.getStudentId())
                    .name(Student.getName())
                    .department(Student.getDepartment())
                    .build());
        }
        return new StudentDto(list,null);
    }

    public StudentDto getStudent(String studentId){
        Optional<Student> StudentOptional = StudentRepository.findById(studentId);
        if (StudentOptional.isPresent()) {
            Student Student=StudentOptional.get();
            return new StudentDto(null, StudentDto.Info.builder()
                    .studentId(Student.getStudentId())
                    .name(Student.getName())
                    .department(Student.getDepartment())
                    .build());
        } else {
            throw new IllegalArgumentException("Student not found with id: " + studentId);
        }
    }
}