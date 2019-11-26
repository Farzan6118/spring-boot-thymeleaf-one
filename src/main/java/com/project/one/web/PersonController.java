package com.project.one.web;

import com.project.one.model.Person;
import com.project.one.repository.PersonRepository;
import com.project.one.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class PersonController {

    private PersonRepository personRepository;
    private PersonService personService;

    @Autowired
    public PersonController(PersonRepository personRepository, PersonService personService) {
        this.personRepository = personRepository;
        this.personService = personService;
    }

    @GetMapping(value = "/")
    public String greetingForm(Model model) {
        model.addAttribute("person", new Person());
        return "form-one";
    }

    @GetMapping(value = "/resu")
    public String resultsControllerGet(Model model) {
        model.addAttribute("result", personRepository.findAll());
        return "results";
    }

    @PostMapping(value = "/")
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
    @GetMapping(value = "person/cached/{id}")
    public Optional<Person> getPersonCached(@PathVariable Long id) {
        return personService.getPersonByIDNot(id);
    }

    @ResponseBody
    @PostMapping(value = "person")
    public Person savePerson(@RequestBody Person person) {
        return personRepository.save(person);
    }
}
