package com.ukiyomo.tools.simpleCommand.converter;


public interface ParamConverter<T> {

    T execute(String param);

}
