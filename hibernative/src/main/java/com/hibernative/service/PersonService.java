package com.hibernative.service;
import java.util.List;

import com.hibernative.entity.Customer;
import com.hibernative.entity.Product;

public interface PersonService {
    void add(Customer customer);
    void addProduct(Product product);
    List<Customer> listPersons();
}