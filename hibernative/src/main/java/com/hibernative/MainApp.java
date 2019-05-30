package com.hibernative;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.hibernative.entity.Product;
import com.hibernative.service.MainService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.hibernative.config.AppConfig;
import com.hibernative.entity.Customer;
import com.hibernative.service.PersonService;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author imssbora
 *
 */

public class MainApp {
	
	
	
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        PersonService personService = context.getBean(PersonService.class);
        MainService mainService = context.getBean(MainService.class);

        // Add Persons 	7
       
//        List<Customer> list = Stream.of(
//                new Customer("Jan", "Kowalski", "suni.bora@example.com"),
//                new Customer("Stefan", "Nowak", "suni.bora@example.com"),
//                new Customer("Justyna", "Wisniewska", "suni.bora@example.com"),
//                new Customer("Dagmara", "Mazur", "suni.bora@example.com"),
//                new Customer("Feliks", "Bora", "suni.bora@example.com"),
//                new Customer("Adam", "Pies", "suni.bora@example.com"),
//                new Customer("Jozef", "Kot", "suni.bora@example.com"),
//                new Customer("Piotr", "Lis", "suni.bora@example.com"),
//                new Customer("Stefan", "Kowalski", "suni.bora@example.com")
//        ).collect(Collectors.toList());
//
//        list.forEach(c -> c.setProducts(Stream.of(
//                new Product(c,"wiaderko","opis wiaderka", LocalDateTime.now()),
//                new Product(c,"miska","opis wiaderka", LocalDateTime.now()),
//                new Product(c,"gabka","opis wiaderka", LocalDateTime.now())
//        ).collect(Collectors.toSet())));
//
//        list.forEach(c -> personService.add(c));

        mainService.playground();

        // Get Persons
//        List<Customer> customers = personService.listPersons();
//        for (Customer customer : customers) {
//            System.out.println("Id = "+ customer.getId());
//            System.out.println("First Name = "+ customer.getFirstName());
//            System.out.println("Last Name = "+ customer.getLastName());
//            System.out.println("Email = "+ customer.getEmail());
//            System.out.println();
//        }
//
//
//
//        customers.get(0).setFirstName("lukasz");
                
        
        context.close();
        
    }
}