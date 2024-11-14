// user/service/UserService.java
package com.example.service;




import com.example.DTO.LoginRequest;
import com.example.DTO.SignupRequest;
import com.example.DTO.userinfo;
import com.example.qna_project.User;
import com.example.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.PasswordEncoder;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest signupRequest) {
        Optional<User> userOptional = userRepository.findByEmail(signupRequest.getEmail());


        String encryptedPassword = getEncryptedPassword(signupRequest.getEmail(),signupRequest.getPassword());

        User user = User.builder()
                .email(signupRequest.getEmail())
                .password(encryptedPassword)
                .username(signupRequest.getUsername())
                .build();

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public userinfo login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail()).get();

        return userinfo.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
    }

    private String getEncryptedPassword(String email, String password) {
        return passwordEncoder.encode(email, password);
    }


    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }
}


