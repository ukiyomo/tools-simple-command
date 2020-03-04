package com.ukiyomo.tools.simpleCommand;

import com.ukiyomo.tools.simpleCommand.converter.ParamConverter;
import com.ukiyomo.tools.simpleCommand.exception.ConverterException;
import com.ukiyomo.tools.simpleCommand.option.ParamOption;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandManager {

    private static Map<ParamOption<?>, ParamConverter<?>> converters = new ConcurrentHashMap<>();

    public static <T> void registerConverter(ParamOption<T> option, ParamConverter<T> converter) {
        if(!existsConverter(option)) {
            converters.put(option, converter);
            return;
        }
        throw new ConverterException("This param option already exist.");
    }

    public static boolean existsConverter(ParamOption<?> option) {
        return converters.containsKey(option);
    }

    public static <T> ParamConverter<T> getConverter(ParamOption<T> option) {
        if(existsConverter(option)) {
            return (ParamConverter<T>) converters.get(option);
        }
        return null;
    }



}
