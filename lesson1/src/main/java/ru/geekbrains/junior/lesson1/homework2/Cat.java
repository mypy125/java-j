package ru.geekbrains.junior.lesson1.homework2;

public class Cat extends Animal{
    boolean isStray;
    public Cat(String name, int age, boolean isStray){
        super(name,age);
        this.isStray = isStray;
    }

    public boolean isStray() {
        return isStray;
    }

    @Override
    public void makeSound() {
        System.out.println(isStray);
    }
}
