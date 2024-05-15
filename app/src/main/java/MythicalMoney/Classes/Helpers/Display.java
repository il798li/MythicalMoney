package MythicalMoney.Classes.Helpers;

import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class Display {

    public final String plural;
    public final String single;

    public Display (final String single) {
        this (single, single + "s");
    }

    public Display (final String single, final String plural) {
        this.single = single;
        this.plural = plural;
    }

    public static Button button (Display display) {
        return Button.primary (display.plural, display.single);
    }

    public String toString () {
        String string = "Single: ";
        string += this.single;
        string += "\nPlural: ";
        string += this.plural;
        return string;
    }

    public Button button () {
        return button (this);
    }

    public String display (final int amount) {
        if (amount == 1) {
            return this.single;
        }
        return this.plural;
    }

    public static class DisplayPlus extends Display {

        public final String name;

        public DisplayPlus (final String single, final String plural, final String name) {
            super (single, plural);
            this.name = name.toLowerCase ();
        }

        public DisplayPlus (final String single) {
            super (single);
            this.name = single.toLowerCase ();
        }

        public String toString () {
            String string = super.toString ();
            string += "\nName: " + this.name;
            return string;
        }
    }
}

