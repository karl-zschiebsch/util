package com.util.entity;

public interface Reference<T extends Entity> {
    public void update(T delta);
}
