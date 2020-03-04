package com.ukiyomo.tools.simpleCommand.converter;

public class DoubleParamConverter implements ParamConverter<Double> {

    public Double execute(String param) {
        return DoubleParamConverter.parse(param);
    }

    public static Double parse(String str) {
        if(null == str || "".equals(str)) return null;
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException except) {
            return null;
        }
    }

}
