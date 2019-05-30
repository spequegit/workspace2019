package com.hibernateSpringExample.dao;
import java.util.List;

import com.hibernateSpringExample.entity.Customer;
import com.hibernateSpringExample.entity.Product;

public interface PersonDao {

    void add(Customer customer);

    void addProduct(Product product);

    List<Customer> listPersons();
}