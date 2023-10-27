package edu.handong.happymanback.user.service;

import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.user.dto.UserDto;
import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.user.exception.StudentIdDuplicateException;
import edu.handong.happymanback.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository UserRepository;

    @Autowired
    public UserService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    public String join(UserForm form) {
        UserRepository.findById(form.getStudentId())
                .ifPresent(m->{
                    throw new StudentIdDuplicateException();
                });
        User user = User.create(form);
        User saved = UserRepository.save(user);
        return saved.getStudentId();
    }

    public String modifyUser(String studentId, UserForm form) {
        Optional<User> UserOptional = UserRepository.findById(studentId);
        if (UserOptional.isPresent()) {
            User User = UserOptional.get();
            return User.update(form);
        } else {
            throw new IllegalArgumentException("User not found with id: " + studentId);
        }
    }

    public String deleteUser(String studentId) {
        UserRepository.deleteById(studentId);
        return studentId;
    }

    public UserDto UserList(){
        List<User> UserList=UserRepository.findAll();
        List<UserDto.Info> list=new ArrayList<>();
        for(User User:UserList){
            list.add(UserDto.Info.builder()
                    .studentId(User.getStudentId())
                    .name(User.getName())
                    .department(User.getDepartment())
                    .build());
        }
        return new UserDto(list,null);
    }

    public UserDto getUser(String studentId){
        Optional<User> UserOptional = UserRepository.findById(studentId);
        if (UserOptional.isPresent()) {
            User User=UserOptional.get();
            return new UserDto(null, UserDto.Info.builder()
                    .studentId(User.getStudentId())
                    .name(User.getName())
                    .department(User.getDepartment())
                    .build());
        } else {
            throw new IllegalArgumentException("User not found with id: " + studentId);
        }
    }
}