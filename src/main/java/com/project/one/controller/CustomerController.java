package com.project.one.controller;

import com.project.one.controller.dto.request.CustomerRequestDto;
import com.project.one.controller.dto.response.CustomerResponseDto;
import com.project.one.domain.constant.Gender;
import com.project.one.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", customerService.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("customer", new CustomerRequestDto());
        model.addAttribute("genders", Gender.values());
        return "add-user";
    }

    @PostMapping("/add")
    public String addUser(
            @Valid @ModelAttribute("customer") CustomerRequestDto requestDto,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("genders", Gender.values());
            return "add-user";
        }

        if (requestDto.getBirthDate() != null && requestDto.getBirthDate().isAfter(java.time.LocalDate.now())) {
            result.rejectValue("birthDate", "error.customer", "Birth date cannot be in the future");
        }

        if (customerService.existsByEmail(requestDto.getEmail())) {
            result.rejectValue("email", "error.customer", "This email is already taken");
        }

        if (requestDto.getNationalCode() ==  null || requestDto.getNationalCode().isBlank()) {
            result.rejectValue("nationalCode", "error.customer", "nationalCode is required");
        }

        if (result.hasErrors()) {
            model.addAttribute("genders", Gender.values());
            return "add-user";
        }

        customerService.save(requestDto);
        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        CustomerResponseDto responseDto = customerService.findById(id);
        model.addAttribute("customer", responseDto);
        model.addAttribute("genders", Gender.values());
        return "update-user";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id,
                             @Valid @ModelAttribute("customer") CustomerRequestDto requestDto,
                             BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genders", Gender.values());
            return "update-user";
        }
        customerService.update(id, requestDto);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        customerService.delete(id);
        return "redirect:/index";
    }
}
