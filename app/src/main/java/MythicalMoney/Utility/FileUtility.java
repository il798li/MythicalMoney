package MythicalMoney.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import MythicalMoney.Main;

public class FileUtility {
    public static String readFile (String path) {
        File file = file (path);
        Scanner scanner;
        try {
            scanner = new Scanner (file);
        } catch (FileNotFoundException fileNotFoundException) {
            return "Exception: FileNotFoundException";
        }

        String content = "";
        while (scanner.hasNextLine ()) {
            content += scanner.nextLine ();
        }
        scanner.close ();

        content = content.substring (0, content.length ());
        return content;
    }

    public static boolean writeFile (String content, String path) {
        File file = file (path);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter (file);
            BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
            bufferedWriter.write (content);
            bufferedWriter.close ();
            return true;
        } catch (IOException ioException) {
            Main.debug ("Could not write to file \"" + path + "\" due to an IOException.");
            return false;
        }
    }

    public static File file (String path) {
        path = "app\\src\\main\\java\\MythicalMoney\\" + path;
        File file = null;
        try {
            file = new File (path);
        } catch (NullPointerException nullPointerException) {
            Main.debug ("Could not open \"" + path + "\" due to a NullPointerException.");
        }
        return file;
    }
}
