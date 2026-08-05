/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.notfound404;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author aar1069
 */
@RestController
@Slf4j
public class MyRESTController {

    private static final Map<Integer, Person> PEOPLE
            = List.of(new Person(1, "Scott"), new Person(2, "Henrietta"))
                    .stream()
                    .collect(Collectors.toMap(Person::getId, Function.identity()));

    @GetMapping("/v1/person/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable int id) {
        log.info("getting person {}", id);

        Person p = PEOPLE.get(id);
        if (p == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(p);
        }
    }
}
