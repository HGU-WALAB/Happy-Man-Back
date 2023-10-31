package edu.handong.happymanback.user.controller;

import edu.handong.happymanback.user.dto.UserDto;
import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/happyman/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> joinUser(@RequestBody UserForm form) {
        String joinId = userService.join(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/user/" + joinId)
        ).body(Map.of("id", joinId));
    }

    @PatchMapping("{studentId}")
    public ResponseEntity<Map<String, String>> modifyUser(@PathVariable("studentId") String studentId, @RequestBody UserForm form) {
        String modifyUserId = userService.modifyUser(studentId, form);
        return ResponseEntity.ok().body(Map.of("student id", modifyUserId));
    }

    @GetMapping
    public ResponseEntity<UserDto> userList(){
        return ResponseEntity.ok().body(userService.userList());
    }

    @GetMapping("{studentId}")
    public ResponseEntity<UserDto> user(@PathVariable("studentId") String studentId){
        return ResponseEntity.ok().body(userService.getUser(studentId));
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("studentId") String studentId){
        return ResponseEntity.ok().body(Map.of("id", userService.deleteUser(studentId)));
    }
}
