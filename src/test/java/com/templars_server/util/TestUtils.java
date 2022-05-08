package com.templars_server.util;

import java.io.InputStream;
import java.util.Scanner;

public class TestUtils {

    public static String loadResourceAsString(Class<?> cl, String fileName) {
        InputStream stream = cl.getClassLoader().getResourceAsStream(fileName);
        if (stream == null) {
            return "";
        }

        Scanner scanner = new Scanner(stream);
        String contents = scanner.useDelimiter("\\A").next();
        scanner.close();
        return contents;
    }

}
