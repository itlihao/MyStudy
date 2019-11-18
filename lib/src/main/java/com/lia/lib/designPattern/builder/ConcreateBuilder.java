package com.lia.lib.designPattern.builder;

public class ConcreateBuilder extends Builder {
    private Product product;

    public ConcreateBuilder() {
        product = new Product();
    }

    @Override
    public void buildA() {

    }

    @Override
    public void buildB() {

    }

    @Override
    public Product create() {
        return product;
    }
}
