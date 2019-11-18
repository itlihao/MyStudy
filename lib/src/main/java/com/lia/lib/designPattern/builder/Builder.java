package com.lia.lib.designPattern.builder;

public abstract class Builder {
    public abstract void buildA();

    public abstract void buildB();

    public abstract Product create();
}
