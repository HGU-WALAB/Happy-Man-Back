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

    @PatchMapping("{personalId}")
    public ResponseEntity<Map<String, String>> modifyUser(@PathVariable("personalId")String personalId,@RequestBody UserForm form) {
        String modifyPersonalId = userService.modifyUser(personalId,form);
        return ResponseEntity.ok().body(Map.of("personal id",modifyPersonalId));
    }

    @GetMapping
    public ResponseEntity<UserDto> userList(){
        return ResponseEntity.ok().body(userService.userList());
    }

    @GetMapping("{personalId}")
    public ResponseEntity<UserDto> user(@PathVariable("personalId")String personalId){
        return ResponseEntity.ok().body(userService.getUser(personalId));
    }

    @DeleteMapping("{personalId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("personalId")String personalId){
        return ResponseEntity.ok().body(Map.of("id",userService.deleteUser(personalId)));
    }

}
