package MythicalMoney.Utility;

public class BasicUtility {
    public static String title (String text) {
        final String letters = "abcdefghijklmnopqrstuvwxyz";
        text = "1" + text.toLowerCase ();
        boolean convert = false;
        for (int index = 0; index < text.length () - 1; index++) {
            String character = text.substring (index, index + 1);
            if (letters.contains (character) == false) {
                convert = true;
            } else if (convert) {
                text = text.substring (0, index) + character.toUpperCase () + text.substring (index + 1);
                convert = false;
            }
        }
        return text.substring (1);
    }

    public static String indent (String string, int indent) {
        String indentString = "";
        for (indent = indent; indent > 0; indent--) {
            indentString += "> ";
        }
        string = indentString + string;
        string = string.replace ("\n", "\n" + indentString);
        return string;
    }

    public static String indent (String string) {
        return indent (string, 1);
    }
}