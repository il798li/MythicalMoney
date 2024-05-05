package MythicalMoney.Data.Helpers;

import org.json.JSONObject;

import MythicalMoney.Classes.Tool;

public class ToolSet {
    public Tool axe;
    public Tool hoe;
    public Tool pickaxe;
    public Tool weapon;

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
            toolSet.axe = Tool.get (axeName);
        }
        {
            final String hoeName = jsonObject.getString ("hoe");
            toolSet.hoe = Tool.get (hoeName);
        }
        {
            final String pickaxeName = jsonObject.getString ("pickaxe");
            toolSet.pickaxe = Tool.get (pickaxeName);
        }
        {
            final String weaponName = jsonObject.getString ("weapon");
            toolSet.weapon = Tool.get (weaponName);
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
