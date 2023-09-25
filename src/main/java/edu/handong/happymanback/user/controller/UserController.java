package edu.handong.happymanback.user.controller;

import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.user.dto.UserDto;
import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/api/happyman/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }
    @PostMapping
    public ResponseEntity<Map<String, String>> joinUser(@RequestBody UserForm form) {
        String joinId = userService.join(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/user/" + joinId)
        ).body(Map.of("id", joinId));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String, String>> modifyUser(@PathVariable("id")Long id,@RequestBody UserForm form) {
        String modifyId = userService.modifyUser(id,form);
        return ResponseEntity.ok().body(Map.of("personal id",modifyId));
    }

    @GetMapping
    public ResponseEntity<List<User>> userList(){
        return ResponseEntity.ok().body(userService.userList());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> user(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Long>> deleteUser(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(Map.of("id",userService.deleteUser(id)));
    }

}
