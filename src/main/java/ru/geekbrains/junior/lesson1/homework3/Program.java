package ru.geekbrains.junior.lesson1.homework3;


import ru.geekbrains.junior.lesson1.homework3.task2.ToDoV2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ru.geekbrains.junior.lesson1.homework3.FileManagement.*;




public class Program {
    /**
    Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
    Обеспечьте поддержку сериализации для этого класса.
    Создайте объект класса Student и инициализируйте его данными.
    Сериализуйте этот объект в файл.
    Десериализуйте объект обратно в программу из файла.
    Выведите все поля объекта, включая GPA, и ответьте на вопрос,
    почему значение GPA не было сохранено/восстановлено.

2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
     */

    public static void main(String[] args) throws IOException {
        List<Student> students;
        File f = new File(FILE_XML);
        if (f.exists() && !f.isDirectory()){
            students = loadFromFile(FILE_XML);
        }
        else{
            students = createStudent();
            saveToFile(FILE_JSON, students);
            saveToFile(FILE_BIN, students);
            saveToFile(FILE_XML, students);
        }

        printStudents(students);

    }

    static List<Student> createStudent(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("max",10,26.30));
        students.add(new Student("philip",30,45.20));
        students.add(new Student("serop",23,12.09));
        return students;
    }

}
