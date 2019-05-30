package com.example.minimalrest2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class MinimalRestController {

    @Autowired
    CustomerRepository repository;

    @Autowired
    ProductRepository productRepository;

    @RequestMapping(method = RequestMethod.GET)
    @Path("/skrzypczak")        //OK
    public Iterable<Customer> showAll(){
        return repository.findByLastName("Skrzypczak");
    }

    @GetMapping("/customer/all")          //OK
    public Iterable<Customer> getCustomersAll(){
        return repository.findAll();
    }

//    @GetMapping("/{id}")        //OK
//    public Customer getById(@PathVariable Long id) {
//        return repository.findById(id).orElseThrow(() -> new NotFoundException());
//    }

    @GetMapping("/customer/id/{id}")      //OK
    public Customer getCustomerById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/customer/name/{name}")  //  http://localhost:8080/user/name/Jan
    public Customer getById2(@PathVariable String name) {
        return repository.findByFirstName(name).stream().findFirst().get();
    }

    @GetMapping("lastname/{value}")     //  http://localhost:8080/user/lastname/Nowak
    public Iterable<Customer> getByLastName(@PathVariable String value) {
        return repository.findByLastName(value).stream().limit(5).collect(Collectors.toList());
    }

    @GetMapping("filter/{a}/{b}")     //    OK
    public String tryTwoParams(@PathVariable String a,@PathVariable String b) {
        return a+" "+b;
    }

    @GetMapping("filter2/{a}")     //   http://localhost:8080/user/filter2/a,s,d,f,g,h,4,f,e,fd,fd,2,r4,we,23
    public Iterable<String> tryTwoParams2(@PathVariable String... a) {
        return Arrays.asList(a);
    }

    @GetMapping("lastnames/{lastnames}")     //
    public Iterable<Customer> getByLastNames(@PathVariable String... lastnames) {
        List<Customer> result = new ArrayList<>();
        Arrays.asList(lastnames).stream().forEach(lastname -> result.add(repository.findByLastName(lastname).stream().findFirst().get()));
        return result;
    }

    @GetMapping("products")
    public Iterable<Product> getProducts() {
        Iterable<Product> all = productRepository.findAll();
        all.forEach(x -> x.setCustomer(null));
        return all;

    }
}
