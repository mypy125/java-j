package ru.geekbrains.junior.lesson1.homework5;

import java.util.HashMap;
import java.util.Map;

public class Program {

    public static void main(String[] args) {
        HashMap<String,String> names= new HashMap<>();

        names.put("gor","1");
        names.put("arkady","2");
        names.put("stas","3");
        names.put("gevorg","4");

        String name = "@gevorg";

        if(isPrivate(name)){
            System.out.println(searchClientFromClients(name, names));
        }

    }

    public static String searchClientFromClients(String string,HashMap<String,String> map){
        StringBuilder sb = new StringBuilder();
        char[] ch = string.substring(1).toCharArray();
        for(Character c : ch){
            sb.append(c);
            if(equalsForMap(sb.toString(), map)){
               return map.get(sb.toString());
            }

        }
        return sb.toString();
    }

    private static boolean equalsForMap(String s, HashMap<?,?> map){
        return map.containsKey(s);
    }
    private static boolean isPrivate(String msg){
        return msg.substring(0,1).equals("@");
    }
}
