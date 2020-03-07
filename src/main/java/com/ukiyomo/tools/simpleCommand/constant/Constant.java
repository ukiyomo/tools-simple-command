package com.ukiyomo.tools.simpleCommand.constant;

public interface Constant<T extends Constant<T>> extends Comparable<T> {

    int id();

    String name();
}
