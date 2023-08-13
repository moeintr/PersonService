package com.a.api;

import com.a.common.wrapper.ErrorHandler;
import com.a.model.domain.Person;
import com.a.model.service.PersonService;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/person")
@Scope("singleton")
public class PersonAPI {
    private final PersonService personService;

    public PersonAPI(PersonService personService) {
        this.personService = personService;
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map> onException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorHandler.getError(e));
    }
    @GetMapping("/savePerson.do")
    public ResponseEntity<Object> savePerson(@ModelAttribute Person person) {
        return new ResponseEntity<>(personService.savePerson(person), HttpStatus.OK);
    }
}
