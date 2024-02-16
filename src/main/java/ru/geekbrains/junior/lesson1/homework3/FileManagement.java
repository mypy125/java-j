package ru.geekbrains.junior.lesson1.homework3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.geekbrains.junior.lesson1.homework3.task2.ToDo;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileManagement {
    public static final String FILE_JSON = "student.json";
    public static final String FILE_XML = "student.xml";
    public static final String FILE_BIN = "student.bin";
    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final XmlMapper xmlMapper = new XmlMapper();

    public static void addNewStudent(List<Student> students) {
        saveToFile(FILE_BIN,students);
        saveToFile(FILE_JSON,students);
        saveToFile(FILE_XML,students);
    }

    public static void printStudents(List<Student> studentList) {
        System.out.println("список стусентов");
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            System.out.println(student);
        }
    }
    public static void printStudents2(List<Student> students) {
        System.out.println("список стусентов");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            String name = student.getName();
            int age = student.getAge();
            double gpa = student.getGPA();
            System.out.println((i + 1) + ". " + name +", "+ age + ", "+ gpa);
        }
    }

    public static void saveToFile(String fileName, List<Student> studentList) {

        try {
             if (fileName.endsWith(".json")) {
                 objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                 objectMapper.writeValue(new File(fileName), studentList);
             }
             else if (fileName.endsWith(".bin")){
                 try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                     oos.writeObject(studentList);
                 }
             }
             else if (fileName.endsWith(".xml")) {
                 xmlMapper.configure(SerializationFeature.INDENT_OUTPUT,true);
                 xmlMapper.writeValue(new File(fileName), studentList);
             }

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static List<Student> loadFromFile(String fileName) {
        List<Student> students = new ArrayList<>();
        File file = new File(fileName);

        if(file.exists()) {
            try {
                if(fileName.endsWith(".json")){
                    students = objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                }
                else if (fileName.endsWith(".bin")){
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                        students = (List<Student>) ois.readObject();
                    }
                }
                else if (fileName.endsWith(".xml")) {
                    students = xmlMapper.readValue(file, xmlMapper.getTypeFactory().constructCollectionType(List.class, Student.class));
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return students;
    }


}
