package edu.handong.happymanback.user.service;

import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.user.exception.PersonalIdDuplicateException;
import edu.handong.happymanback.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public String join(UserForm form) {
        userRepository.findById(form.getPersonalId())
                .ifPresent(m->{
                    throw new PersonalIdDuplicateException();
                });
        User user = User.create(form);
        User saved = userRepository.save(user);
        return saved.getPersonalId();
    }

    public String modifyUser(String personalId, UserForm form) {
        Optional<User> userOptional = userRepository.findById(personalId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.update(form);
        } else {
            throw new IllegalArgumentException("User not found with id: " + personalId);
        }
    }

    public String deleteUser(String personalId) {
        userRepository.deleteById(personalId);
        return personalId;
    }

    public List<User> userList(){
        return userRepository.findAll();
    }

    public User getUser(String personalId){
        Optional<User> userOptional = userRepository.findById(personalId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new IllegalArgumentException("User not found with id: " + personalId);
        }
    }
}