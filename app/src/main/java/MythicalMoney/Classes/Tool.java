package MythicalMoney.Classes;

import MythicalMoney.Classes.Display.Display;
import MythicalMoney.Classes.Obtainable.ToolType;

public class Tool {
    public int [] chances;
    public Display display;
    public int cooldown;
    public ToolType toolType;
    
    private Tool (Display display, int cooldown, int [] chances, ToolType toolType) {
        this.display = display;
        this.cooldown = cooldown;
        this.chances = chances;
        this.toolType = toolType;
    }
    
    public static class Weapon extends Tool {
        public Weapon (Display display, int cooldown, int [] chances) {
            super (display, cooldown, chances, ToolType.Weapon);
        }
    }

    public static class Axe extends Tool {
        public Axe (Display display, int cooldown, int [] chances) {
            super (display, cooldown, chances, ToolType.Axe);
        }
    }

    public static class Hoe extends Tool {
        public Hoe (Display display, int cooldown, int [] chances) {
            super (display, cooldown, chances, ToolType.Hoe);
        }
    }

    public static class Pickaxe extends Tool {
        public Pickaxe (Display display, int cooldown, int [] chances) {
            super (display, cooldown, chances, ToolType.Pickaxe);
        }
    }
}
