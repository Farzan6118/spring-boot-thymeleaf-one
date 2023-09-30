package com.project.one.controller;

import com.project.one.model.User;
import com.project.one.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model, User user) {
        model.addAttribute(user);
        return "add-user";
    }

    @PostMapping("/adduser")
    public String resultsControllerPost(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }
        userRepository.save(user);
        model.addAttribute(user);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("user", user);
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User updatedEntity) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid entity Id: " + id));
        user.setEmail(updatedEntity.getEmail());
        user.setGender(updatedEntity.getGender());
        user.setBirthDate(updatedEntity.getBirthDate());
        user.setLastName(updatedEntity.getLastName());
        user.setFirstName(updatedEntity.getFirstName());
        userRepository.save(user);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);
        return "redirect:/index";
    }

// ====================== RestController POSTMAN ======================

    @ResponseBody
    @PostMapping("get-all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @ResponseBody
    @PostMapping("add")
    public User savePerson(@RequestBody User user) {
        return userRepository.save(user);
    }

}
