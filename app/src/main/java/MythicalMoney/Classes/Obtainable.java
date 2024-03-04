package MythicalMoney.Classes;

import MythicalMoney.Enums;

public class Obtainable {
    public Display display;
    public Enums tool;

    public Obtainable (final Display display, final Enums tool) {
        this.display = display;
        this.tool = tool;
    }

    public Obtainable (final String single, final String plural, final Enums tool) {
        this.display = new Display (single, plural);
        this.tool = tool;
    }
}