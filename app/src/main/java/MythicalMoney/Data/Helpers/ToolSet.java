package MythicalMoney.Data.Helpers;

import org.json.JSONObject;

import MythicalMoney.Classes.Tool;
import MythicalMoney.Classes.Obtainable.ToolType;
import MythicalMoney.Classes.Tool.Axe;
import MythicalMoney.Classes.Tool.Hoe;
import MythicalMoney.Classes.Tool.Pickaxe;
import MythicalMoney.Classes.Tool.Weapon;

public class ToolSet {
    public Tool axe;
    public Tool hoe;
    public Tool pickaxe;
    public Tool weapon;
    
    public ToolSet (Axe axe, Hoe hoe, Pickaxe pickaxe, Weapon weapon) {
        this.axe = axe;
        this.hoe = hoe;
        this.pickaxe = pickaxe;
        this.weapon = weapon;
    }

    public ToolSet () {
        return;
    }

    public static ToolSet fromJSON (JSONObject jsonObject) {
        final ToolSet toolSet = new ToolSet ();
        {
            final String axeName = jsonObject.getString("axe");
            final Tool axe = Axe.get (axeName);
            toolSet.axe = axe;
        }
        {
            final String hoeName = jsonObject.getString ("hoe");
            final Tool hoe = Hoe.get (hoeName);
            toolSet.hoe = hoe;
        }
        {
            final String pickaxeName = jsonObject.getString ("pickaxe");
            final Tool pickaxe = Pickaxe.get (pickaxeName);
            toolSet.pickaxe = pickaxe;
        }
        {
            final String weaponName = jsonObject.getString ("weapon");
            final Tool weapon = Weapon.get (weaponName);
            toolSet.weapon = weapon;
        }
        return toolSet;
    }

    

    public static JSONObject toJSON (final ToolSet toolSet) {
        JSONObject jsonObject = new JSONObject ();
        jsonObject.put ("axe", toolSet.axe.display.plural);
        jsonObject.put ("hoe", toolSet.hoe.display.plural);
        jsonObject.put ("pickaxe", toolSet.pickaxe.display.plural);
        jsonObject.put ("weapon", toolSet.weapon.display.plural);
        return jsonObject;
    }
}
