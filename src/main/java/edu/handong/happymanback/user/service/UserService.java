package edu.handong.happymanback.user.service;

import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.user.dto.UserDto;
import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.user.exception.PersonalIdDuplicateException;
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

    public Long join(UserForm form) {
        userRepository.findByPersonalId(form.getPersonalId())
                .ifPresent(m->{
                    throw new PersonalIdDuplicateException();
                });
        User user = User.createUser(form);
        User saved = userRepository.save(user);
        return saved.getId();
    }

    public Long modifyUser(Long key, UserForm form) {
        Optional<User> user = userRepository.findById(key);
        return user.get().update(form);
    }

    public Long deleteUser(Long key) {
        userRepository.deleteById(key);
        return key;
    }

    public List<User> userList(){
        return userRepository.findAll();
    }

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }
}