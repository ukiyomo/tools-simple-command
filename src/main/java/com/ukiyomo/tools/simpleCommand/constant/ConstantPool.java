package com.ukiyomo.tools.simpleCommand.constant;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ConstantPool<T extends Constant<T>> {

    private ConcurrentHashMap<String, T> constants = new ConcurrentHashMap<>();
    private AtomicInteger nextId = new AtomicInteger(1);

    public T valueOf(String name, Class<Object> clazz) {
        checkNotNullAndNotEmpty(name);
        return this.getOrCreate(name, clazz);
    }

    public Collection<T> values() {
        return this.constants.values();
    }

    private T getOrCreate(String name, Class<Object> clazz) {
        T constant = this.constants.get(name);
        if(null == constant) {
            T tempConstant = this.newConstant(this.nextId(), name, clazz);
            constant = this.constants.putIfAbsent(name, tempConstant);
            if(null == constant) {
                return tempConstant;
            }
        }

        return constant;
    }

    abstract public T newConstant(int id, String name, Class<Object> clazz);

    public final int nextId() {
        return this.nextId.getAndIncrement();
    }

    private static String checkNotNullAndNotEmpty(String name) {
        if (null == name || name.isEmpty()) {
            throw new IllegalArgumentException("empty name");
        } else {
            return name;
        }
    }

}
