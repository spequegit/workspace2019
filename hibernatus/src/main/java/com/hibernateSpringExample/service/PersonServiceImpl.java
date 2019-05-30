package com.hibernateSpringExample.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernateSpringExample.dao.PersonDao;
import com.hibernateSpringExample.entity.Customer;
import com.hibernateSpringExample.entity.Product;


/**
 * @author imssbora
 *
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao userDao;

    @Transactional
    public void add(Customer customer) {
        userDao.add(customer);
    }

    @Transactional
    public void addProduct(Product product) {
        userDao.addProduct(product);
    }

    @Transactional(readOnly = true)
    public List<Customer> listPersons() {
        return userDao.listPersons();
    }

}