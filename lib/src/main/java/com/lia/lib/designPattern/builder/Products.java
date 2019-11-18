package com.lia.lib.designPattern.builder;

public class Products {
    private String partA;
    private String partB;

    private Products(Builder builder) {
        this.partA = builder.partA;
        this.partB = builder.partB;
    }

    public static class Builder {
        String partA;
        String partB;

        public Builder partA(String a) {
            this.partA = a;
            return this;
        }

        public Builder partB(String b) {
            this.partB = b;
            return this;
        }

        public Products build() {
            return new Products(this);
        }
    }

    public static void main(String[] args) {
        Products test = new Products.Builder().partA("a").partB("b").build();
    }
}

