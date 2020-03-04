package com.ukiyomo.tools.simpleCommand.converter;

public class IntegerParamConverter implements ParamConverter<Integer> {

    public Integer execute(String param) {
        return IntegerParamConverter.parse(param);
    }

    public static Integer parse(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException except) {
            return null;
        }
    }
}
