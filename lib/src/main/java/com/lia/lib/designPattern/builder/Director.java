package com.lia.lib.designPattern.builder;

public class Director {
    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    public Product construct() {
        builder.buildA();
        builder.buildB();
        return builder.create();
    }
}
