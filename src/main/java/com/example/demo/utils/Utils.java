package com.example.demo.utils;

import java.util.List;
import java.util.Random;

public class Utils {

    private static final Random random = new Random();
    private static final List<String> firstNames = List.of("Nguyen", "Tran", "Le", "Pham", "Hoang", "Vu", "Phan", "Truong", "Bui", "Doan");
    private static final List<String> lastNames = List.of("Van", "Thi", "Minh", "Thanh", "Duc", "Huy", "Anh", "Tuan", "Linh", "Mai");

    public static String getFullName() {
        String firstName = firstNames.get(random.nextInt(firstNames.size()));
        String lastName = lastNames.get(random.nextInt(lastNames.size()));
        return firstName + " " + lastName;
    }
}
