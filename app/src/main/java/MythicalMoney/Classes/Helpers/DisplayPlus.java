package MythicalMoney.Classes.Helpers;

public class DisplayPlus extends Display {

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
