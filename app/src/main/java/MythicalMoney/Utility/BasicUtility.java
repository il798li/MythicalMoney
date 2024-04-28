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
        for (; indent > 0; indent--) {
            indentString += "    ";
        }
        string = indentString + string;
        string = string.replace ("\n", "\n" + indentString);
        return string;
    }

    public static String indent (String string) {
        return indent (string, 1);
    }

    public static String multiplyString (final String string, int multiplier) {
        String multipliedString = "";
        for (; multiplier > 0; multiplier--) {
            multipliedString += string;
        }
        return multipliedString;
    }

    public static int smaller (final int number1, final int number2) {
        if (number1 < number2) {
            return number1;
        }
        return number2;
    }

    public static String toString (boolean bool) {
        if (bool == true) {
            return "true";
        } else {
            return "false";
        }
    }

    public static String formatNumber (int number) {
        final StringBuilder stringBuilder = new StringBuilder("" + number);
        stringBuilder.reverse ();
        final String rawNumber = stringBuilder.toString ();
        StringBuilder formattedNumber = new StringBuilder();
        final int length = rawNumber.length ();
        int increment = 0;
        for (int index = 0; index < length; index++) {
            final String character = rawNumber.charAt (index) + "";
            if (increment == 3) {
                increment = 0;
                formattedNumber.append (",");
            } else {
                increment += 1;
            }
            formattedNumber.append (character);
        }
        formattedNumber.reverse ();
        return formattedNumber.toString ();
    }
}