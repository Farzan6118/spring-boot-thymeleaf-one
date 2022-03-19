package com.project.one.controller;

import com.project.one.model.Person;
import com.project.one.repository.PersonRepository;
import com.project.one.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonService personService;

    @GetMapping("/")
    public String greetingForm(Model model) {
        model.addAttribute("person", new Person());
        return "form-one";
    }

    @GetMapping("/resu")
    public String resultsControllerGet(Model model) {
        model.addAttribute("result", personRepository.findAll());
        return "results";
    }

    @PostMapping("/")
    public String resultsControllerPost(@ModelAttribute @Valid Person person,
                                        BindingResult bindingResult,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            return "form-one";
        }
        model.addAttribute("person", person);
        personRepository.save(person);
        return "redirect:/resu";
    }

    @ResponseBody
    @GetMapping("person/cached/{id}")
    public Optional<Person> getPersonCached(@PathVariable Long id) {
        return personService.getPersonByIdNot(id);
    }

    @ResponseBody
    @PostMapping("person")
    public Person savePerson(@RequestBody Person person) {
        return personRepository.save(person);
    }
}
