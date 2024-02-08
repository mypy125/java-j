package ru.geekbrains.junior.lesson1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
     Создайте абстрактный класс "Animal" с полями "name" и "age".
     Реализуйте два класса-наследника от "Animal" (например, "Dog" и "Cat") с уникальными полями и методами.
     Создайте массив объектов типа "Animal" и с использованием Reflection API выполните следующие действия:
     Выведите на экран информацию о каждом объекте.
     Вызовите метод "makeSound()" у каждого объекта, если такой метод присутствует.

     Дополнительная задача:

     Доработайте метод генерации запроса на удаление объекта из таблицы БД (DELETE FROM <Table> WHERE ID = '<id>')
     В классе QueryBuilder который мы разработали на семинаре.
 */
public class Program {
    public static void main(String[] args) {
        Animal[] animals = {new Dog("Buddy", 5, "Labrador"),
                new Cat("Whiskers", 3, true)};


        Arrays.stream(animals)
                .peek(animal -> System.out.println(animal.getName()+": "+animal.getAge()))
                .forEach(animal -> {
                    try{
                        Method makeSound = animal.getClass().getMethod("makeSound");
                        makeSound.invoke(animal);
                    } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }

                });

    }



}
