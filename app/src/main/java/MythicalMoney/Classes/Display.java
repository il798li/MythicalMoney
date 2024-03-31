package MythicalMoney.Classes;

import MythicalMoney.Utility.BasicUtility;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

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
        String string = "Single: ";
        string += single;
        string += "\nPlural: ";
        string += plural;
        
        return string;
    }

    public Button button () {
        return button (this);
    }

    public static Button button (Display display) {
        return Button.primary (display.plural, display.single);
    }
}

