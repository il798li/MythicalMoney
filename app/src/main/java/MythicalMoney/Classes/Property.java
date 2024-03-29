package MythicalMoney.Classes;

import MythicalMoney.Classes.Obtainable.Tool;

public class Property {
    public int [] chances;
    public DisplayPlus display;
    public Tool tool;

    public Property (DisplayPlus display, Tool tool, int [] chances) {
        this.display = display;
        this.chances = chances;
        this.tool = tool;
    }

}