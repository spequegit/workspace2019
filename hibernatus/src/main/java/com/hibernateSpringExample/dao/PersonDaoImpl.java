package com.hibernateSpringExample.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.hibernateSpringExample.entity.Customer;
import com.hibernateSpringExample.entity.Product;

@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Customer customer) {
        entityManager.persist(customer);
    }

    public void addProduct(Product product) {
        entityManager.persist(product);
    }

    public List<Customer> listPersons() {
        CriteriaQuery<Customer> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Customer.class);
        @SuppressWarnings("unused")
        Root<Customer> root = criteriaQuery.from(Customer.class);
        
        
        
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
