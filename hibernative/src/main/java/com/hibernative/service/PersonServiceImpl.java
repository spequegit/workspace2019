package com.hibernative.service;
import java.util.List;

import com.hibernative.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hibernative.dao.PersonDao;
import com.hibernative.entity.Customer;


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