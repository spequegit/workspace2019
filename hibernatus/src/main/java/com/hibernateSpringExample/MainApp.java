package com.hibernateSpringExample;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.hibernateSpringExample.config.AppConfiguration;
import com.hibernateSpringExample.entity.Customer;
import com.hibernateSpringExample.entity.Product;
import com.hibernateSpringExample.service.PersonService;

@Component
public class MainApp {
	
	
	
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);

        PersonService personService = context.getBean(PersonService.class);

        // Add Persons 	7
        Customer customer = new Customer("Lukasz", "Skrzypczak", "lukasz@skrzypczak.com");
        personService.add(customer);
        personService.add(new Customer("Stefan", "Kot", "stefan@kot.com"));
        personService.add(new Customer("Katarzyna", "Jar", "jar@op.com"));
        personService.add(new Customer("Paul", "Smith", "paul.smith@example.com"));
        personService.addProduct(new Product(customer,"wiaderko","opis wiaderka", LocalDateTime.now()));

        // Get Persons
        List<Customer> customers = personService.listPersons();
        for (Customer c : customers) {
            System.out.println(c.getLastName());
        }
        context.close();
        
    }
}