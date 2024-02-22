package ru.geekbrains.junior.lesson1.homework2.task2;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueryBuilder {

    /**
     * Построить запрос на добавление данных в БД
     *
     * @param obj
     * @return
     */
    public String buildInsertQuery(Object obj){
        Class<?> clazz = obj.getClass();
        if (!clazz.isAnnotationPresent(Table.class)) {
            return "";
        }
        StringBuilder query = new StringBuilder("INSERT INTO ");

        Table tableAnnotation = clazz.getAnnotation(Table.class);
        query.append(tableAnnotation.name()).append(" (");

        Field[] fields = clazz.getDeclaredFields();
        String columns = Stream.of(fields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> field.getAnnotation(Column.class).name())
                .collect(Collectors.joining(", "));
        query.append(columns).append(") VALUES (");

        String values = Stream.of(fields)
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> {
                    field.setAccessible(true);
                    try {
                        return "'" + field.get(obj) + "'";
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .collect(Collectors.joining(", "));
        query.append(values).append(")");

        return query.toString();

    }

    /**
     * Построить запрос на получение данных из БД
     *
     * @param clazz
     * @param primaryKey
     * @return
     */
    public String buildSelectQuery(Class<?> clazz, UUID primaryKey) {
        if(!clazz.isAnnotationPresent(Table.class)){
            return "";
        }
        String tableName = clazz.getAnnotation(Table.class).name();

        String querySelect = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> field.getAnnotation(Column.class).primaryKey())
                .map(field -> field.getAnnotation(Column.class).name()+ " = '" + primaryKey + "'")
                .collect(Collectors.joining(" AND ", "SELECT * FROM " + tableName + " WHERE ", ""));
        return querySelect;
    }

        /**
         * Построить запрос на удаление данных из бд
         *
         * @param obj
         * @return
         */

    public String buildUpdateQuery(Object obj) throws IllegalAccessException{
        Class<?> clazz = obj.getClass();
        if(!clazz.isAnnotationPresent(Table.class)){
            return "";
        }
        String tableName = clazz.getAnnotation(Table.class).name();

        String queryUpdate = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> !field.getAnnotation(Column.class).primaryKey())
                .map(field -> {
                    field.setAccessible(true);
                    try{
                        return field.getAnnotation(Column.class).name() + " = '" + field.get(obj) + "'";
                    }catch (IllegalAccessException e){
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.joining(", ", "UPDATE " + tableName + " SET ", ""));

        Field primaryKeyField = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> field.getAnnotation(Column.class).primaryKey())
                .findFirst()
                .orElse(null);
        if(primaryKeyField != null){
            primaryKeyField.setAccessible(true);
            try{
                queryUpdate += " WHERE " + primaryKeyField.getAnnotation(Column.class).name() + " = '" + primaryKeyField.get(obj)+ "'";
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }
        return queryUpdate;

    }

        /**
         * TODO: Доработать метод Delete в рамках выполнения домашней работы
         *
         * @return
         */

    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey) {
        if(!clazz.isAnnotationPresent(Table.class)){
            return "";
        }

        String tableName = clazz.getAnnotation(Table.class).name();

        String query = Arrays.stream(clazz.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Column.class))
                .filter(field -> field.getAnnotation(Column.class).primaryKey())
                .map(field -> field.getAnnotation(Column.class).name() + " = '" + primaryKey + "'")
                .collect(Collectors.joining(" AND ", "DELETE * FROM " + tableName + " WHERE ", ""));
        return query;
    }


}

