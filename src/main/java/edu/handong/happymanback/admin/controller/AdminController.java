package edu.handong.happymanback.admin.controller;

import edu.handong.happymanback.admin.dto.AdminDto;
import edu.handong.happymanback.admin.dto.AdminForm;
import edu.handong.happymanback.admin.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/happyman/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> joinAdmin(@RequestBody AdminForm form) {
        String joinId = adminService.join(form);
        return ResponseEntity.created(
                URI.create("/api/happyman/admin/" + joinId)
        ).body(Map.of("id", joinId));
    }

    @PatchMapping("{id}")
    public ResponseEntity<Map<String, String>> modifyAdmin(@PathVariable("id") String Id, @RequestBody AdminForm form) {
        String modifyAdminId = adminService.modifyAdmin(Id, form);
        return ResponseEntity.ok().body(Map.of(" id", modifyAdminId));
    }

    @GetMapping
    public ResponseEntity<AdminDto> adminList(){
        return ResponseEntity.ok().body(adminService.adminList());
    }

    @GetMapping("{id}")
    public ResponseEntity<AdminDto> admin(@PathVariable("id") String Id){
        return ResponseEntity.ok().body(adminService.getAdmin(Id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deleteAdmin(@PathVariable("id") String id){
        return ResponseEntity.ok().body(Map.of("id", adminService.deleteAdmin(id)));
    }
}
