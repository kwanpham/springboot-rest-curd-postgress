package com.quandev.springbootrestcurdpostgress;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.quandev.springbootrestcurdpostgress.model.Tutorial;
import com.quandev.springbootrestcurdpostgress.model.TutorialEnum;
import com.quandev.springbootrestcurdpostgress.repository.TutorialRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest
class SpringbootRestCurdPostgressApplicationTests {

    @Autowired
    TutorialRepository tr;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        Faker faker = new Faker();

        for (int i = 0 ; i< 300 ; i++) {
            Tutorial tutorial = new Tutorial(faker.book().title() , faker.book().publisher(), faker.random().nextBoolean());
             tr.save(tutorial);
        }
    }

    @Test
    public void testPage() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(tr.findAll(PageRequest.of(0 ,20))));
    }

    @Test
    public void testDysnamic() {

        Page<Tutorial> tutorials = tr.findDysnamic(null , null , true , TutorialEnum.INACTIVE, PageRequest.of(0 ,10 , Sort.unsorted()));
        System.out.println(tutorials);
    }

}
