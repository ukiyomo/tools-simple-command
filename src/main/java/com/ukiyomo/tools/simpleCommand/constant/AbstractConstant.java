package com.ukiyomo.tools.simpleCommand.constant;


import java.util.concurrent.atomic.AtomicLong;

public class AbstractConstant<T extends AbstractConstant<T>> implements Constant<T> {

    private static final AtomicLong uniqueIdGenerator = new AtomicLong();
    private final int id;
    private final String name;
    private final Class<?> clazz;
    protected final long uniquifier;

    protected AbstractConstant(int id, String name, Class<?> clazz) {
        this.id = id;
        this.name = name;
        this.clazz = clazz;
        this.uniquifier = uniqueIdGenerator.getAndIncrement();
    }

    public final String name() {
        return this.name;
    }

    public final int id() {
        return this.id;
    }

    public final Class<?> clazz() {return this.clazz;}

    public final String toString() {
        return this.name();
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final int compareTo(T o) {
        if (this == o) {
            return 0;
        } else {

            int returnCode = this.hashCode() - o.hashCode();
            if (returnCode != 0) {
                return returnCode;
            } else if (this.uniquifier < o.uniquifier) {
                return -1;
            } else if (this.uniquifier > o.uniquifier) {
                return 1;
            } else {
                throw new Error("failed to compare two different constants");
            }
        }
    }
}
