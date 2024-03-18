package MythicalMoney.Classes;

import MythicalMoney.Utility.BasicUtility;

public class Display {
    public String plural;
    public String single;

    public Display (final String single, final String plural) {
        this.single = single;
        this.plural = plural;
    }

    public Display (final String single) {
        this.single = BasicUtility.title (single);
        this.plural = single + "s";
    }

    public Display (final Display display) {
        this.single = display.single;
        this.plural = display.plural;
    }

    public String toString () {
        return "A single " + single + " or multiple " + plural;
    }
}

