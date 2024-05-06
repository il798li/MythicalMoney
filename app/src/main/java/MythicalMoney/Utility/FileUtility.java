package MythicalMoney.Utility;

import MythicalMoney.Main;

import java.io.*;
import java.util.Scanner;

public class FileUtility {

    public static String readFile (String path) {
        File file = file (path);
        Scanner scanner;
        try {
            scanner = new Scanner (file);
        } catch (FileNotFoundException fileNotFoundException) {
            return "Exception: FileNotFoundException";
        }

        StringBuilder content = new StringBuilder ();
        while (scanner.hasNextLine ()) {
            content.append (scanner.nextLine ());
        }
        scanner.close ();
        return content.toString ();
    }

    public static void writeFile (String content, String path) {
        File file = file (path);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter (file);
            BufferedWriter bufferedWriter = new BufferedWriter (fileWriter);
            bufferedWriter.write (content);
            bufferedWriter.close ();
        } catch (IOException ioException) {
            Main.debug ("Could not write to file \"" + path + "\" due to an IOException.");
        }
    }

    public static File file (String path) {
        path = "app\\src\\main\\java\\MythicalMoney\\" + path;
        File file = null;
        try {
            file = new File (path);
        } catch (final NullPointerException nullPointerException) {
            Main.debug ("Could not open \"" + path + "\" due to a NullPointerException.");
        }
        return file;
    }
}
