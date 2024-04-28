package MythicalMoney.Data.Helpers;

import org.json.JSONObject;

import MythicalMoney.Classes.Tool;

public class ToolSet {
    public Tool axe;
    public Tool hoe;
    public Tool pickaxe;
    public Tool weapon;
    
    public ToolSet (Tool axe, Tool hoe, Tool pickaxe, Tool weapon) {
        this.axe = axe;
        this.hoe = hoe;
        this.pickaxe = pickaxe;
        this.weapon = weapon;
    }

    public ToolSet () {
        this.axe = Tool.starterAxe;
        this.hoe = Tool.starterHoe;
        this.pickaxe = Tool.starterPickaxe;
        this.weapon = Tool.starterWeapon;
    }

    public static ToolSet fromJSON (JSONObject jsonObject) {
        final ToolSet toolSet = new ToolSet ();
        {
            final String axeName = jsonObject.getString("axe");
            final Tool axe = Tool.get (axeName);
            toolSet.axe = axe;
        }
        {
            final String hoeName = jsonObject.getString ("hoe");
            final Tool hoe = Tool.get (hoeName);
            toolSet.hoe = hoe;
        }
        {
            final String pickaxeName = jsonObject.getString ("pickaxe");
            final Tool pickaxe = Tool.get (pickaxeName);
            toolSet.pickaxe = pickaxe;
        }
        {
            final String weaponName = jsonObject.getString ("weapon");
            final Tool weapon = Tool.get (weaponName);
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
