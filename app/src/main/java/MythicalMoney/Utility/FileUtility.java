package MythicalMoney.Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileUtility {
    public static String readFile (String path) {
        path = "app\\src\\main\\java\\MythicalMoney\\" + path;
        final File file;
        try {
            file = new File (path);
        } catch (NullPointerException nullPointerException) {
            return "";
        }

        final Scanner scanner;
        try {
            scanner = new Scanner (file);
        } catch (FileNotFoundException fileNotFoundException) {
            return "";
        }

        String content = "";
        while (scanner.hasNextLine ()) {
            content += scanner.nextLine ();
        }
        scanner.close ();

        content = content.substring (0, content.length ());
        return content;
    }
}