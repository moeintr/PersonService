package com.a.model.service;

import com.a.common.exception.RollbackException;
import com.a.model.domain.Person;
import com.a.model.repository.Crud;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Scope("singleton")
public class PersonService {
    private final Crud<Person, Long> personCrud;

    public PersonService(Crud<Person, Long> personCrud) {
        this.personCrud = personCrud;
    }
    @Transactional(rollbackFor = RollbackException.class)
    public Optional<Person> savePerson(Person person) {
        personCrud.insert(person);
        return Optional.ofNullable(personCrud.selectOne(Person.class, person.getPersonId()));
    }
}
