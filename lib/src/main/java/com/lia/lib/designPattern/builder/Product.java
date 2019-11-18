package com.lia.lib.designPattern.builder;

public class Product {

    private String partA;

    private String partB;

    public Product() {
    }

    public Product(String partA, String partB) {
        this.partA = partA;
        this.partB = partB;
    }

    public String getPartA() {
        return partA;
    }

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public String getPartB() {
        return partB;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }
}