package edu.handong.happymanback.user.controller;

import edu.handong.happymanback.user.dto.UserDto;
import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/happyman/user")
public class UserController {
    private final UserService UserService;

    public UserController(UserService UserService){
        this.UserService=UserService;
    }
    @PostMapping
    public ResponseEntity<Map<String, String>> joinUser(@RequestBody UserForm form) {
        String joinId = UserService.join(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/User/" + joinId)
        ).body(Map.of("id", joinId));
    }

    @PatchMapping("{studentId}")
    public ResponseEntity<Map<String, String>> modifyUser(@PathVariable("studentId")String studentId,@RequestBody UserForm form) {
        String modifyUserId = UserService.modifyUser(studentId,form);
        return ResponseEntity.ok().body(Map.of("student id",modifyUserId));
    }

    @GetMapping
    public ResponseEntity<UserDto> UserList(){
        return ResponseEntity.ok().body(UserService.UserList());
    }

    @GetMapping("{studentId}")
    public ResponseEntity<UserDto> User(@PathVariable("studentId")String studentId){
        return ResponseEntity.ok().body(UserService.getUser(studentId));
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("studentId")String studentId){
        return ResponseEntity.ok().body(Map.of("id",UserService.deleteUser(studentId)));
    }

}
