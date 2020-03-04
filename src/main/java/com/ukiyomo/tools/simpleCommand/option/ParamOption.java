package com.ukiyomo.tools.simpleCommand.option;

import com.ukiyomo.tools.simpleCommand.CommandManager;
import com.ukiyomo.tools.simpleCommand.converter.DoubleParamConverter;
import com.ukiyomo.tools.simpleCommand.converter.IntegerParamConverter;
import com.ukiyomo.tools.simpleCommand.converter.StringParamConverter;
import pers.graceyu.constantPool.AbstractConstant;
import pers.graceyu.constantPool.ConstantPool;


public class ParamOption<T> extends AbstractConstant<ParamOption<T>> {

    private final static ConstantPool<ParamOption<Object>> constantPool = new ConstantPool<ParamOption<Object>>() {

        @Override
        public ParamOption<Object> newConstant(int i, String s, Class<Object> clazz) {
            return new ParamOption<>(i, s , clazz);
        }
    };

    // Common
    public final static ParamOption<Integer> INTEGER = valueOf("INTEGER", Integer.class);
    public final static ParamOption<String> STRING = valueOf("STRING", String.class);
    public final static ParamOption<Double> DOUBLE = valueOf("FLOAT", Double.class);

    static {
        CommandManager.registerConverter(ParamOption.DOUBLE, new DoubleParamConverter());
        CommandManager.registerConverter(ParamOption.INTEGER, new IntegerParamConverter());
        CommandManager.registerConverter(ParamOption.STRING, new StringParamConverter());
    }

    public static <T> ParamOption<T> valueOf(String name, Class<T> clazz) {
        return (ParamOption)constantPool.valueOf(name, (Class<Object>) clazz);
    }

    public static boolean existsByType(Class<?> clazz) {
        return null != getByType(clazz);
    }

    public static <T> ParamOption<T> getByType(Class<T> clazz) {
        for (ParamOption<Object> value : constantPool.values()) {
            if(value.clazz() == clazz) {
                return (ParamOption<T>) value;
            }
        }
        return null;
    }

    private ParamOption(int id, String name, Class<T> clazz) {
        super(id, name, clazz);
    }

}
