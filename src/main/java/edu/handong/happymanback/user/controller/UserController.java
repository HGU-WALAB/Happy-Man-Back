package edu.handong.happymanback.user.controller;

import edu.handong.happymanback.user.dto.UserDto;
import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/happyman")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PatchMapping("admin/user/{uniqueId}")
    public ResponseEntity<Map<String, String>> modifyUser(@PathVariable("uniqueId") String uniqueId, @RequestBody UserForm form) {
        String modifyUserId = userService.modifyUser(uniqueId, form);
        return ResponseEntity.ok().body(Map.of("unique id", modifyUserId));
    }

    @GetMapping("admin/user")
    public ResponseEntity<UserDto> userList(){
        return ResponseEntity.ok().body(userService.userList());
    }

    @GetMapping("admin/user/{uniqueId}")
    public ResponseEntity<UserDto> user(@PathVariable("uniqueId") String uniqueId){
        return ResponseEntity.ok().body(userService.getUser(uniqueId));
    }

    @DeleteMapping("admin/user/{uniqueId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable("uniqueId") String uniqueId){
        return ResponseEntity.ok().body(Map.of("uniqueId", userService.deleteUser(uniqueId)));
    }
}