package com.ukiyomo.tools.simpleCommand.converter;

public class BooleanParamConverter implements ParamConverter<Boolean> {

    @Override
    public Boolean execute(String param) {
        return BooleanParamConverter.parse(param);
    }

    public static Boolean parse(String str) {
        if(str.equalsIgnoreCase("真")
            || str.equalsIgnoreCase("T")
            || str.equalsIgnoreCase("1")
            || str.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        } else if(str.equalsIgnoreCase("假")
            || str.equalsIgnoreCase("F")
            || str.equalsIgnoreCase("0")
            || str.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }

        return null;
    }

}
