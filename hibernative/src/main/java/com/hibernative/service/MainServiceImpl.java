package com.hibernative.service;

import com.hibernative.dao.MainDao;
import com.hibernative.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MainServiceImpl implements MainService {

    @Autowired
    private MainDao dao;

    @Override
    public void playground() {
        Customer customer = dao.doJoin();
    }
}
