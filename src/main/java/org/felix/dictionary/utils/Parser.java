package org.felix.dictionary.utils;
import org.felix.dictionary.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
    private static String path = "src/main/resources/guide.txt";

    public Parser(){
    }
    public Parser(String s)
    {
        path = s;
    }
    public static void menu()
    {
        System.out.println("Привет!Давай сыграем в игру!!!Введи число");
        System.out.println("0-выйти");
        System.out.println("1-распечатать список");
        System.out.println("2-сортировать список");
        System.out.println("3-найти город с наибольшим населением");
        System.out.println("4-найти количество городов по регионам");
        System.out.println("5-показать меню");
        System.out.println("6-получить новый путь из файла");
        System.out.println("7-создать таблицу");
        System.out.println("8-вставить значение в таблицу");
        System.out.println("9-обновить значение в таблице");
        System.out.println("10-удалить значение из таблицы");
        System.out.println("11-считать значение таблицы");
    }
    public static List<User> parse() throws FileNotFoundException {
        File file = new File(path);
        Scanner scan = new Scanner(file);
        List<User> users = new ArrayList<User>();
        while (scan.hasNextLine()) {
            String[] s1 = scan.nextLine().split(";");
            users.add(new User(s1));
            //     System.out.println(scan.nextLine());
        }
        return users;
    }
}
