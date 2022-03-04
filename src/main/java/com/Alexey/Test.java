package com.Alexey;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;

public class Test {

    public <T> T toObject(String fileName, Class<T> clazz) {
        T instance;
        try (final BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            instance = clazz.getDeclaredConstructor().newInstance();
            String line;
            while ((line = reader.readLine()) != null) {
                final String[] split = line.split(":");
                final String fieldName = split[0].trim();
                final String fieldValue = split[1].trim();
                for (Method method : clazz.getMethods()) {
                    final FileFieldName annotation = method.getAnnotation(FileFieldName.class);
                    if (annotation != null && annotation.value().equalsIgnoreCase(fieldName) || method.getName().equalsIgnoreCase("set" + fieldName)) {
                        Object val;
                        if (method.getParameterTypes()[0].getName().equals("int")) {
                            val = Integer.parseInt(fieldValue);
                        } else {
                            val = fieldValue;
                        }
                        method.invoke(instance, val);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static void main(String[] args) {
        Test test = new Test();

        Tickets tickets = test.toObject("tickets.json", Tickets.class);
        System.out.println("tickets: " + tickets);
    }
}
