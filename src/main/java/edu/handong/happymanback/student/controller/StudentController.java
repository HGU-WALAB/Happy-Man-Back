package edu.handong.happymanback.student.controller;

import edu.handong.happymanback.student.dto.StudentDto;
import edu.handong.happymanback.student.dto.StudentForm;
import edu.handong.happymanback.student.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/happyman/student")
public class StudentController {
    private final StudentService StudentService;

    public StudentController(StudentService StudentService){
        this.StudentService=StudentService;
    }
    @PostMapping
    public ResponseEntity<Map<String, String>> joinStudent(@RequestBody StudentForm form) {
        String joinId = StudentService.join(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/Student/" + joinId)
        ).body(Map.of("id", joinId));
    }

    @PatchMapping("{studentId}")
    public ResponseEntity<Map<String, String>> modifyStudent(@PathVariable("studentId")String studentId,@RequestBody StudentForm form) {
        String modifystudentId = StudentService.modifyStudent(studentId,form);
        return ResponseEntity.ok().body(Map.of("personal id",modifystudentId));
    }

    @GetMapping
    public ResponseEntity<StudentDto> StudentList(){
        return ResponseEntity.ok().body(StudentService.StudentList());
    }

    @GetMapping("{studentId}")
    public ResponseEntity<StudentDto> Student(@PathVariable("studentId")String studentId){
        return ResponseEntity.ok().body(StudentService.getStudent(studentId));
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable("studentId")String studentId){
        return ResponseEntity.ok().body(Map.of("id",StudentService.deleteStudent(studentId)));
    }

}
