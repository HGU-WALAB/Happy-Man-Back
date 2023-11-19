package edu.handong.happymanback.user.service;

import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.user.dto.UserDto;
import edu.handong.happymanback.user.dto.UserForm;
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

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String modifyUser(String uniqueId, UserForm form) {
        Optional<User> userOptional = userRepository.findById(uniqueId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.update(form);
        } else {
            throw new IllegalArgumentException("User not found with id: " + uniqueId);
        }
    }

    public String deleteUser(String uniqueId) {
        userRepository.deleteById(uniqueId);
        return uniqueId;
    }

    public UserDto userList(){
        List<User> userList=userRepository.findAll();
        List<UserDto.Info> list=new ArrayList<>();
        for(User user:userList){
            list.add(UserDto.Info.builder()
                    .uniqueId(user.getUniqueId())
                    .name(user.getName())
                    .department(user.getDepartment())
                    .build());
        }
        return new UserDto(list,null);
    }

    public UserDto getUser(String uniqueId){
        Optional<User> userOptional = userRepository.findById(uniqueId);
        if (userOptional.isPresent()) {
            User user=userOptional.get();
            return new UserDto(null, UserDto.Info.builder()
                    .uniqueId(user.getUniqueId())
                    .name(user.getName())
                    .department(user.getDepartment())
                    .role(user.getRole())
                    .email(user.getEmail())
                    .build());
        } else {
            throw new IllegalArgumentException("User not found with id: " + uniqueId);
        }
    }
}