package edu.handong.happymanback.auth.service;

import edu.handong.happymanback.auth.dto.AuthForm;
import edu.handong.happymanback.user.domain.User;
import edu.handong.happymanback.user.dto.UserForm;
import edu.handong.happymanback.user.exception.StudentIdDuplicateException;
import edu.handong.happymanback.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String join(UserForm form) {
        userRepository.findById(form.getUniqueId())
                .ifPresent(user->{
                    throw new StudentIdDuplicateException();
                });
        User user = User.create(form);
        User saved = userRepository.save(user);
        return saved.getUniqueId();
    }

    public User getLoginUserByUniqueId(String loginId) {
        if(loginId == null) return null;

        Optional<User> optionalUser = userRepository.findByUniqueId(loginId);
        return optionalUser.orElse(null);
    }

    public User login(AuthForm form) {
        System.out.println("service uniqueId : " + form.getUniqueId());
        Optional<User> optionalUser = userRepository.findByUniqueId(form.getUniqueId());

        // loginId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            return null;
        }

        User user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if(!user.getPassword().equals(form.getPassword())) {
            return null;
        }

        return user;
    }
}
