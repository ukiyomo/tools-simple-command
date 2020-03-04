package com.ukiyomo.tools.simpleCommand.converter;

public class StringParamConverter implements ParamConverter<String> {

    public String execute(String param) {
        return param;
    }
}
