package com.hibernative.dao;

import com.hibernative.entity.Customer;
import com.hibernative.entity.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MainDaoImpl implements MainDao {

    @PersistenceContext
    private EntityManager em;



//    public List<Customer> listPersons() {
//        CriteriaQuery<Customer> criteriaQuery = entityManager.getCriteriaBuilder().createQuery(Customer.class);
//        @SuppressWarnings("unused")
//        Root<Customer> root = criteriaQuery.from(Customer.class);
//
//
//
//        return entityManager.createQuery(criteriaQuery).getResultList();
//    }

    @Override
    public Customer doJoin() {

        CriteriaQuery<Customer> cq = em.getCriteriaBuilder().createQuery(Customer.class);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        Root<Customer> rootCustomer = cq.from(Customer.class);
//        Fetch<Customer,Product> fetchCustomerProduct = rootCustomer.fetch("products", JoinType.LEFT);
        Join<Customer,Product> joinCustomerProduct = rootCustomer.join("products", JoinType.INNER);

        cq.where(cb.equal(rootCustomer.get("firstName"), "Dagmara"));


        List<Customer> resultList = em.createQuery(cq).getResultList();



//        List<String> collect = resultList.stream().map(Customer::getLastName).collect(Collectors.toList());
//        collect.forEach(name -> System.out.println(name));



//        String description = resultList.get(0).getProducts().iterator().next().getDescription();
//        System.out.println(resultList);
        Customer customer = resultList.stream().findFirst().get();
//        customer.setLastName("skrzypczak");
//        Set<Product> customerProducts = customer.getProducts();
//        System.out.println(customerProducts.iterator().next());
            return customer;
    }
}
