/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blazartech.notfound404;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

/**
 *
 * @author aar1069
 */
@Component
@Slf4j
public class MyConsumer implements CommandLineRunner {

    @Autowired
    private RestClient restClient;

    private Person makeCall(int id) {
        try {
            log.info("making call for ID {}", id);
            Person p = restClient.get()
                    .uri("/v1/person/{id}", id)
                    .retrieve()
                    .body(Person.class);
            return p;
        } catch (HttpClientErrorException.NotFound e) {
            log.error("got a not found, returning null");
            return null;
        }
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("making call");

        Person p1 = makeCall(1);
        log.info("got person {}", p1);

        Person p5 = makeCall(5);
        log.info("got person {}", p5);
    }

}
