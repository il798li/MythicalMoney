package MythicalMoney.Classes.Display;

public class DisplayPlus extends Display {

    public String name;

    public DisplayPlus (final String single, final String plural, final String name) {
        super (single, plural);
        this.name = name.toLowerCase ();
    }

    public DisplayPlus (final String single, final String name) {
        super (single);
        this.name = name;
    }

    public DisplayPlus (final Display display, final String name) {
        super (display);
        this.name = name;
    }

    public DisplayPlus (final String single) {
        super (single);
        this.name = single.toLowerCase ();
    }

    public DisplayPlus (final Display display) {
        super (display);
        this.name = super.single.toLowerCase ();
    }

    public String toString () {
        String string = super.toString ();
        string += "\nName: " + this.name;
        return string;
    }
}
