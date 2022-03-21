package com.project.one.service;

import com.project.one.model.User;
import com.project.one.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Cacheable("user")
    public Optional<User> getUserByIdNot(Long id) {
        return userRepository.findById(id);
    }
}
