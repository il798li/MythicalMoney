package MythicalMoney.Utility;

import java.util.ArrayList;

public class BasicUtility {

    public static String indent (String string, int indent) {
        StringBuilder indentString = new StringBuilder ();
        for (; indent > 0; indent--) {
            indentString.append ("    ");
        }
        string = indentString + string;
        string = string.replace ("\n", "\n" + indentString);
        return string;
    }

    public static String indent (String string) {
        return indent (string, 1);
    }

    public static String formatNumber (int number) {
        final StringBuilder stringBuilder = new StringBuilder ("" + number);
        stringBuilder.reverse ();
        final String rawNumber = stringBuilder.toString ();
        StringBuilder formattedNumber = new StringBuilder ();
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

    public static int[] toList (ArrayList <Integer> integerArrayList) {
        final int size = integerArrayList.size ();
        final int[] integerList = new int[size];
        for (int index = 0; index < size; index++) {
            integerList[index] = integerArrayList.get (index);
        }
        return integerList;
    }
}