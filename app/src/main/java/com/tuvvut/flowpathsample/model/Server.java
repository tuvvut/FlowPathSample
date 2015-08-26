package com.tuvvut.flowpathsample.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wu on 2015/08/26
 */
public class Server {
    private static HashMap<String, String> userDb = new HashMap<>();

    public static boolean register(String userName, String password, String passwordAgain) {
        if (!password.equals(passwordAgain) || userDb.containsKey(userName)) {
            return false;
        } else {
            userDb.put(userName, password);
            return true;
        }
    }

    public static boolean login(String userName, String password) {
        if (userDb.containsKey(userName)) {
            String passwordFromDb = userDb.get(userName);
            return passwordFromDb.equals(password);
        }
        return false;
    }

    public static ArrayList<String> loadNews() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            arrayList.add("News " + i);
        }
        return arrayList;
    }
}
