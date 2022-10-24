package com.util.value;

public abstract class Property {
    String name;
    Object bean;

    public Property(String name, Object bean) {
        this.name = name;
        this.bean = bean;
    }

    String getName() {
        return name;
    }

    Object getBean() {
        return bean;
    }
}
