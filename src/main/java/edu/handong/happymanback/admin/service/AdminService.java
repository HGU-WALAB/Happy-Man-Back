package edu.handong.happymanback.admin.service;

import edu.handong.happymanback.admin.domain.Admin;
import edu.handong.happymanback.admin.dto.AdminDto;
import edu.handong.happymanback.admin.dto.AdminForm;
import edu.handong.happymanback.admin.exception.AdminIdDuplicateException;
import edu.handong.happymanback.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository){
        this.adminRepository=adminRepository;
    }

    public String join(AdminForm form){
        adminRepository.findById(form.getId())
                .ifPresent(m->{
                    throw new AdminIdDuplicateException();
                });
        Admin admin = Admin.create(form);
        Admin saved = adminRepository.save(admin);
        return saved.getId();
    }

    public String modifyAdmin(String id, AdminForm form) {
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            return admin.update(form);
        } else {
            throw new IllegalArgumentException("Admin not found with id: " + id);
        }
    }

    public String deleteAdmin(String id) {
        adminRepository.deleteById(id);
        return id;
    }

    public AdminDto adminList(){
        List<Admin> adminList=adminRepository.findAll();
        List<AdminDto.Info> list=new ArrayList<>();
        for(Admin admin:adminList){
            list.add(AdminDto.Info.builder()
                    .id(admin.getId())
                    .name(admin.getName())
                    .build());
        }
        return new AdminDto(list,null);
    }

    public AdminDto getAdmin(String id){
        Optional<Admin> adminOptional = adminRepository.findById(id);
        if (adminOptional.isPresent()) {
            Admin admin=adminOptional.get();
            return new AdminDto(null, AdminDto.Info.builder()
                    .id(admin.getId())
                    .name(admin.getName())
                    .build());
        } else {
            throw new IllegalArgumentException("Admin not found with id: " + id);
        }
    }
}
