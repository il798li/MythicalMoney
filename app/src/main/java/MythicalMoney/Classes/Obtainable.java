package MythicalMoney.Classes;

public class Obtainable {
    public Display display;
    public ToolType tool;

    public Obtainable (final Display display, final ToolType tool) {
        this.display = display;
        this.tool = tool;
    }

    public Obtainable (final String single, final String plural, final ToolType tool) {
        this.display = new Display (single, plural);
        this.tool = tool;
    }
}