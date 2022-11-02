package com.crud.minerals.service;

import com.crud.minerals.domain.User;
import com.crud.minerals.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(String login, String password, String email) {
        if (login == null && password == null) {
            return null;
        } else {
            if (userRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("duplicated login");
                return null;
            }
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);
            return userRepository.save(user);
        }
    }

    public User authenticate(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }
}
