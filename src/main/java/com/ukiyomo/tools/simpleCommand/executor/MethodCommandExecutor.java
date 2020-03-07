package com.ukiyomo.tools.simpleCommand.executor;

import com.ukiyomo.tools.simpleCommand.context.CommandContext;
import com.ukiyomo.tools.simpleCommand.CommandManager;
import com.ukiyomo.tools.simpleCommand.annotation.CommandMapping;
import com.ukiyomo.tools.simpleCommand.annotation.ParamMapping;
import com.ukiyomo.tools.simpleCommand.converter.ParamConverter;
import com.ukiyomo.tools.simpleCommand.option.ParamOption;
import com.ukiyomo.tools.simpleCommand.utils.Utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.*;
import java.util.stream.Collectors;

public class MethodCommandExecutor implements CommandExecutor {

    private Map<String, MethodSpace> methods = new HashMap<>();

    public MethodCommandExecutor() {
        this.init();
    }

    @Override
    public void init() {
        for (Method declaredMethod : this.getClass().getDeclaredMethods()) {
            CommandMapping commandMapping = this.getAnnotation(declaredMethod, CommandMapping.class);

            if (null != commandMapping) {
                ParamMapping paramMapping = this.getAnnotation(declaredMethod, ParamMapping.class);

                MethodSpace methodSpace = new MethodSpace();
                methodSpace.method = declaredMethod;
                methodSpace.methodParamTypes = declaredMethod.getParameterTypes();
                methodSpace.commandMapping = commandMapping;
                methodSpace.paramMapping = paramMapping;

                String[] strings = Utils.implodeArray(commandMapping.value());
                methodSpace.mappingValueArray = strings;

                for (int i = 0; i < strings.length; i++) {
                    if (this.isVar(strings[i])) {
                        methodSpace.mappingValueVarRef.add(i);
                    }
                }

                this.methods.put(commandMapping.value(), methodSpace);
            }
        }
    }

    private boolean isVar(String value) {
        return value.startsWith(":");
    }

    private <T extends Annotation> T getAnnotation(Method method, Class<T> clazz) {
        try {
            return method.getAnnotation(clazz);
        } catch (Exception ignore) {
            return null;
        }
    }

    @Override
    public void execute(String s, String[] params, CommandContext commandContext) {

        LinkedList<String> paramList = new LinkedList<>(Arrays.asList(params));
        paramList.addFirst(s);

        for (; paramList.size() > 0; paramList.removeLast()) {
            List<MethodSpace> collect = this.methods.values().stream().filter(v -> v.mappingValueArray.length == paramList.size()).collect(Collectors.toList());
            boolean outerKey = true;
            for (MethodSpace methodSpace : collect) {
                boolean innerKey = true;
                for (int i = 0; i < methodSpace.mappingValueArray.length; i++) {
                    if (methodSpace.mappingValueVarRef.contains(i)) {
                        continue;
                    }
                    if (!methodSpace.mappingValueArray[i].equals(paramList.get(i))) {
                        innerKey = false;
                        break;
                    }
                }
                if (innerKey) {
                    String[] realParams = new String[methodSpace.mappingValueVarRef.size()];
                    for (int i = 0; i < methodSpace.mappingValueVarRef.size(); i++) {
                        realParams[i] = params[methodSpace.mappingValueVarRef.get(i) - 1];
                    }

                    Class<?>[] parameterTypes = methodSpace.methodParamTypes;
                    Object[] obj = new Object[parameterTypes.length];

                    for (int i = 0; i < parameterTypes.length; i++) {
                        if (CommandContext.class.isAssignableFrom(parameterTypes[i])) {
                            obj[i] = commandContext;
                        } else {
                            ParamOption<?> byType = ParamOption.getByType(parameterTypes[i]);
                            if (null != byType) {
                                ParamConverter<?> converter = CommandManager.getConverter(byType);
                                if (null != converter) {
                                    obj[i] = converter.execute(realParams[0]);
                                    realParams = Utils.deleteFirst(realParams);
                                }
                            }
                        }
                    }

                    try {
                        methodSpace.method.invoke(this, obj);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    outerKey = false;
                }
            }
            if (!outerKey) {
                return;
            }
        }
    }

    private static class MethodSpace {
        public Method method;
        public Class<?>[] methodParamTypes;

        public CommandMapping commandMapping;
        public ParamMapping paramMapping;

        public String[] mappingValueArray;

        // mapping const value count (not variable)
        public List<Integer> mappingValueVarRef = new ArrayList<>();
    }
}
