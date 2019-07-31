package hr.microblink.demo.controller;

import hr.microblink.demo.exception.ResourceNotFoundException;
import hr.microblink.demo.model.Person;
import hr.microblink.demo.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PersonController {

    @Autowired
    private PeopleRepository peopleRepository;

    public PersonController(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }


    @GetMapping("/people")
    public List<Person> getAllPeople(){
    return peopleRepository.findAll();
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable(value = "id") Long personId) throws ResourceNotFoundException {
        Person person = peopleRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found by id: "+personId));
        return ResponseEntity.ok().body(person);
    }
    @PostMapping("/people")
    public Person createPerson(@Valid @RequestBody Person person){

        return peopleRepository.save(person);
    }

    @PutMapping("/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable(value = "id") Long personId,
                                                   @Valid @RequestBody Person personDetails) throws ResourceNotFoundException {
        Person person = peopleRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found by id: " + personId));

        person.setFirstName(personDetails.getFirstName());
        person.setLastName(personDetails.getLastName());
        person.setGender(personDetails.getGender());
        person.setDateOfBirth(personDetails.getDateOfBirth());
        person.setIdNumber(personDetails.getIdNumber());
        person.setDocumentNumber(personDetails.getDocumentNumber());
        person.setNationality(personDetails.getNationality());
        person.setResidence(personDetails.getResidence());

        final Person updatedPerson = peopleRepository.save(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/people/{id}")
    public Map<String, Boolean> deletePerson(@PathVariable(value = "id") Long personId)
            throws ResourceNotFoundException {
        Person person = peopleRepository.findById(personId)
                .orElseThrow(() -> new ResourceNotFoundException("Person not found for id: " + personId));

        peopleRepository.delete(person);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
