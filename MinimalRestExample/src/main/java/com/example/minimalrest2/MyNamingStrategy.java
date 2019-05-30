package com.example.minimalrest2;

import org.hibernate.cfg.DefaultNamingStrategy;

public class MyNamingStrategy extends DefaultNamingStrategy {
    @Override
    public String classToTableName(String className) {
        return super.classToTableName(className);
    }
}
